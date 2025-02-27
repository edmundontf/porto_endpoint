package com.projeto_mentoria.por_service.controller

import com.projeto_mentoria.por_service.model.Port
import com.projeto_mentoria.por_service.service.PortService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ports")
class PortController(private val portService: PortService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveOrUpdatePort(@RequestBody portData: Map<String, Any>) {
        val (id, portMap) = portData.entries.first()
        val port = Port(
            id = id,
            name = portMap["name"] as String,
            city = portMap["city"] as String,
            country = portMap["country"] as String,
            coordinates = portMap["coordinates"] as List<Double>
        )
        portService.saveOrUpdatePort(port)
    }

    @GetMapping("/{id}")
    fun getPortById(@PathVariable id: String): Port? {
        return portService.getPortById(id)
    }

    @GetMapping
    fun getAllPorts(): List<Port> {
        return portService.getAllPorts()
    }
}