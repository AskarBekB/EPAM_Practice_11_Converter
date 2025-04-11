package dev.androidbroadcast.converter.data.mapper

import dev.androidbroadcast.converter.data.model.CurrencyEntity
import dev.androidbroadcast.converter.domain.model.Currency

object CurrencyDataMapper {
    fun map(entity: CurrencyEntity): Currency {
        return Currency(
            name = entity.code,
            rate = entity.rate,
            change = entity.change
        )
    }
}