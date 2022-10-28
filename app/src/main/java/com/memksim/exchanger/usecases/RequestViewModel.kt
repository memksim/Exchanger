package com.memksim.exchanger.usecases

import androidx.lifecycle.*
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.repositories.LocalDatabaseRepository
import kotlinx.coroutines.launch

abstract class RequestViewModel(
    private val localRepository: LocalDatabaseRepository
) : ViewModel() {

    @Volatile
    protected var isTableEmpty: Boolean = true

    init {
        viewModelScope.launch {
            isTableEmpty = localRepository.checkIfTableIsEmpty()
        }
    }

    abstract fun loadNetworkData()

    protected fun saveCurrencyLocal(currencyList: List<Currency>) {
        viewModelScope.launch {
            if (isTableEmpty) {
                localRepository.saveCurrency(*currencyList.toTypedArray())
                isTableEmpty = false
            } else {
                updateCurrencyLocal(currencyList)
            }
        }
    }

    private fun updateCurrencyLocal(currencyList: List<Currency>) {
        viewModelScope.launch {
            localRepository.updateCurrency(*currencyList.toTypedArray())
        }
    }

}