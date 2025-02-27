package com.projeto_mentoria.por_service.utils

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.type.TypeReference
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.InputStream

class JsonStreamProcessor {

    private val objectMapper = ObjectMapper()
    private val jsonFactory = JsonFactory()
    private val resource = ClassPathResource("ports.json")
    private val jsonFile = File(resource.file.toURI())

    fun processLargeJson(callback: (Map<String, Any>) -> Unit) {
        val inputStream: InputStream = resource.inputStream
        val jsonParser = jsonFactory.createParser(inputStream)

        if (jsonParser.nextToken() != JsonToken.START_OBJECT) {
            throw IllegalStateException("Expected content to be an object")
        }

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            val portId = jsonParser.currentName
            jsonParser.nextToken()
            val portMap = objectMapper.readValue<Map<String, Any>>(
                jsonParser,
                object : TypeReference<Map<String, Any>>() {}
            )
            callback(portMap + ("id" to portId))
        }
    }

    fun savePortsToJson(ports: Map<String, Any>) {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, ports)
    }
}