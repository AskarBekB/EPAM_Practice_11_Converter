package dev.androidbroadcast.converter.domain.model

data class Currency(
    val name: String,
    val rate: Double,
    val change: Double
)