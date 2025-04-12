package dev.androidbroadcast.converter.domain.usecase

import dev.androidbroadcast.converter.domain.model.Currency
import dev.androidbroadcast.converter.domain.repository.CurrencyRepository

class ConvertCurrencyUseCase(
    private val repository: CurrencyRepository
) {
    suspend fun getCurrencies(): List<Currency> {
        return repository.getCurrencies()
    }

    suspend fun execute(amount: Double, from: String, to: String): Double {
        return repository.convert(amount, from ,to)
    }
}