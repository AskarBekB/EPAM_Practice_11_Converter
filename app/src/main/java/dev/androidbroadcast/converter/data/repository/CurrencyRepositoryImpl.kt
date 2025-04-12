package dev.androidbroadcast.converter.data.repository

import dev.androidbroadcast.converter.data.mapper.CurrencyDataMapper
import dev.androidbroadcast.converter.data.model.CurrencyEntity
import dev.androidbroadcast.library.retrofit.RetrofitClient
import dev.androidbroadcast.converter.domain.model.Currency
import dev.androidbroadcast.converter.domain.repository.CurrencyRepository
import kotlin.random.Random

class CurrencyRepositoryImpl : CurrencyRepository {
    private val accessKey = "75538a3fb92a6d4b08ace879d12dc451"

    override suspend fun getCurrencies(): List<Currency> {
        // Запрос последних курсов с удалённого сервера
        val response = RetrofitClient.apiService.getLatestRates(accessKey = accessKey)
        return response.rates.map { (code, rate) ->
            // Симуляция изменения курса (в реальном случае – получать данные отдельно)
            val change = Random.nextDouble(-0.05, 0.05) * rate
            val entity = CurrencyEntity(
                code = code,
                rate = rate,
                change = change
            )
            CurrencyDataMapper.map(entity)
        }
    }

    override suspend fun convert(amount: Double, from: String, to: String): Double {
        // Простейшая логика конвертации: получаем курсы и пересчитываем сумму
        val currencies = getCurrencies().associateBy { it.name }
        val fromRate = currencies[from]?.rate ?: 1.0
        val toRate = currencies[to]?.rate ?: 1.0
        return (amount / fromRate) * toRate
    }
}
