package com.projeto_mentoria.por_service.repository

import com.projeto_mentoria.por_service.model.Port
import com.projeto_mentoria.por_service.utils.JsonStreamProcessor
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class PortRepository(private val jsonStreamProcessor: JsonStreamProcessor) {

    private val ports = ConcurrentHashMap<String, Port>()

    fun saveOrUpdate(port: Port) {
        ports[port.id] = port
        savePortsToJson()
    }

    fun findById(id: String): Port? {
        return ports[id]
    }

    fun findAll(): List<Port> {
        return ports.values.toList()
    }

    private fun savePortsToJson() {
        val portsMap = ports.mapValues { (_, port) ->
            mapOf(
                "name" to port.name,
                "city" to port.city,
                "country" to port.country,
                "coordinates" to port.coordinates
            )
        }
        jsonStreamProcessor.savePortsToJson(portsMap)
    }
}