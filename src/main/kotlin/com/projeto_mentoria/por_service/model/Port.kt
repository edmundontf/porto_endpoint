package com.projeto_mentoria.por_service.model


data class Port(
    val id: String,
    val name: String,
    val city: String,
    val country: String,
    val coordinates: List<Double>
)

