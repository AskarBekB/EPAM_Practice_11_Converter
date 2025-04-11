package dev.androidbroadcast.converter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.androidbroadcast.converter.domain.usecase.ConvertCurrencyUseCase
import dev.androidbroadcast.converter.presentation.model.CurrencyUIModel
import dev.androidbroadcast.converter.presentation.mapper.CurrencyPresentationMapper
import kotlinx.coroutines.launch

class CurrencyViewModel(private val useCase: ConvertCurrencyUseCase): ViewModel() {
    private val _currencies = MutableLiveData<List<CurrencyUIModel>>()
    val currencies: LiveData<List<CurrencyUIModel>> = _currencies

    fun loadCurrencies() {
        viewModelScope.launch {
            val domainCurrencies = useCase.getCurrencies()
            val uiModels = domainCurrencies.map { CurrencyPresentationMapper.map(it) }
            _currencies.postValue(uiModels)
        }
    }

//    fun convertAmount(amount: Double, from: String, to: String, onResult: (Double) -> Unit) {
//        viewModelScope.launch {
//            val result = useCase.execute(amount, from, to)
//            onResult(result)
//        }
//    }
}