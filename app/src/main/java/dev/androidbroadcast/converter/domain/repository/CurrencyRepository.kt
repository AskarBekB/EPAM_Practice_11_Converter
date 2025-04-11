package dev.androidbroadcast.converter.domain.repository

import dev.androidbroadcast.converter.domain.model.Currency

interface CurrencyRepository {
    suspend fun getCurrencies(): List<Currency>
    suspend fun convert(amount: Double, from: String, to: String): Double
}