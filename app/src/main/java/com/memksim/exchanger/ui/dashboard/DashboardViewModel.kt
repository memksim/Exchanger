package com.memksim.exchanger.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memksim.exchanger.data.repositories.ValuteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val remoteRepository = ValuteRepository()

    private val _liveData = MutableLiveData<DashboardUiState>().apply {
        DashboardUiState(
            emptyList()
        )
    }
    val liveData: LiveData<DashboardUiState> = _liveData

    private fun updateState(items: List<DashboardItemUiState>) {
        _liveData.value = DashboardUiState(
            items
        )
    }

    fun updateData() = viewModelScope.launch {
        val currencyList = arrayListOf<DashboardItemUiState>()

        val scope = viewModelScope.async {
            remoteRepository.getPost()
        }

        val data = scope.await()
        for (item in data) {
            currencyList.add(
                DashboardItemUiState(
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



        updateState(currencyList)
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