package dev.androidbroadcast.converter.presentation.di

import dev.androidbroadcast.converter.data.repository.CurrencyRepositoryImpl
import dev.androidbroadcast.converter.domain.usecase.ConvertCurrencyUseCase
import dev.androidbroadcast.converter.presentation.viewmodel.CurrencyViewModel

object DependencyProvider {
    private val repository by lazy { CurrencyRepositoryImpl() }
    private val convertCurrencyUseCase by lazy { ConvertCurrencyUseCase(repository) }
    private val currencyViewModel by lazy { CurrencyViewModel(convertCurrencyUseCase) }

    fun provideCurrencyViewModel(): CurrencyViewModel = currencyViewModel
}