package dev.androidbroadcast.converter.data.model


data class CurrencyEntity(
    val code: String,
    val rate: Double,
    val change: Double
)