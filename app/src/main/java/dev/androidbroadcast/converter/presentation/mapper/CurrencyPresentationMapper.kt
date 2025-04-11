package dev.androidbroadcast.converter.presentation.mapper

import dev.androidbroadcast.converter.domain.model.Currency
import dev.androidbroadcast.converter.presentation.model.CurrencyUIModel

object CurrencyPresentationMapper {
    fun map(currency: Currency): CurrencyUIModel {
        return CurrencyUIModel(
            name = currency.name,
            rate = currency.rate,
            change = currency.change
        )
    }
}