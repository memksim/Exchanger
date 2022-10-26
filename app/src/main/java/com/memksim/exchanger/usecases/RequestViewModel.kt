package com.memksim.exchanger.usecases

import android.app.Application
import androidx.lifecycle.*
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.entities.Valute
import com.memksim.exchanger.data.repositories.LocalDatabaseRepository
import com.memksim.exchanger.data.repositories.RetrofitRepository
import com.memksim.exchanger.ui.dashboard.DashboardItemUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class RequestViewModel(
    application: Application
) : AndroidViewModel(application) {

    protected val remoteRepository = RetrofitRepository()
    protected val localRepository = LocalDatabaseRepository(application)

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