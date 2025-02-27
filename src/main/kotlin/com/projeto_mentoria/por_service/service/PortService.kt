package com.projeto_mentoria.por_service.service

import com.projeto_mentoria.por_service.model.Port
import com.projeto_mentoria.por_service.repository.PortRepository
import com.projeto_mentoria.por_service.utils.JsonStreamProcessor
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class PortService(private val portRepository: PortRepository) {

    @PostConstruct
    fun init() {
        val jsonStreamProcessor = JsonStreamProcessor()
        jsonStreamProcessor.processLargeJson { portMap ->
            val port = Port(
                id = portMap["id"] as String,
                name = portMap["name"] as String,
                city = portMap["city"] as String,
                country = portMap["country"] as String,
                coordinates = portMap["coordinates"] as List<Double>
            )
            portRepository.saveOrUpdate(port)
        }
    }

    fun saveOrUpdatePort(port: Port) {
        portRepository.saveOrUpdate(port)
    }

    fun getPortById(id: String): Port? {
        return portRepository.findById(id)
    }

    fun getAllPorts(): List<Port> {
        return portRepository.findAll()
    }
}