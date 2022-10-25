package com.memksim.exchanger.ui.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.entities.Valute
import com.memksim.exchanger.data.repositories.LocalDatabaseRepository
import com.memksim.exchanger.data.repositories.RetrofitRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DashboardViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val remoteRepository = RetrofitRepository()
    private val localRepository = LocalDatabaseRepository(application)

    @Volatile
    private var isTableEmpty: Boolean = true

    private var _liveData: MutableLiveData<DashboardUiState> = liveData {
        emit(
            DashboardUiState(
                localRepository.getCurrencyList()
            )
        )
        isTableEmpty = localRepository.checkIfTableIsEmpty()
    } as MutableLiveData<DashboardUiState>


    val liveData: LiveData<DashboardUiState> = _liveData

    private fun updateState(items: List<Currency>) {
        _liveData.value = DashboardUiState(
            items
        )
        saveCurrency(items)
    }

    fun loadData() = viewModelScope.launch {

        val scope = viewModelScope.async {
            remoteRepository.getPost()
        }

        val data = scope.await()

        updateState(valuteListToCurrencyList(data))
    }

    private fun saveCurrency(currencyList: List<Currency>) {
        viewModelScope.launch {
            if (isTableEmpty) {
                localRepository.saveCurrency(currencyList)
                isTableEmpty = false
            } else {
                updateCurrency(currencyList)
            }
        }
    }

    private fun updateCurrency(currencyList: List<Currency>) {
        viewModelScope.launch {
            localRepository.updateCurrency(currencyList)
        }
    }

    private fun valuteListToCurrencyList(valuteList: List<Valute>): List<Currency> {
        val resultList = arrayListOf<Currency>()

        for (item in valuteList) {
            resultList.add(
                Currency(
                    charCode = item.charCode,
                    nominal = adaptNominal(item.nominal),
                    name = item.name,
                    value = adaptValue(item.value),
                    previous = item.previous,
                    isBookmarked = false,
                    isTrendingUp = checkIsTrendingGrows(item.previous, item.value)
                )
            )
        }

        return resultList
    }

    private fun adaptValue(d: Double): Double = (d * 100).toInt().toDouble() / 100

    private fun adaptNominal(n: Int): String {
        return if (n < 1000) {
            n.toString()
        } else {
            val str = n / 1000
            "${str}k"
        }
    }

    private fun checkIsTrendingGrows(prev: Double, now: Double): Boolean {
        return now < prev
    }

}