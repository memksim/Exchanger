package com.memksim.exchanger.ui.dashboard

import android.util.Log
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

    private fun updateState(items: List<DashboardItemUiState>){
        _liveData.value = DashboardUiState(
            items
        )
    }

    fun updateData(){
        val currencyList = arrayListOf<DashboardItemUiState>()

        viewModelScope.launch {
            val scope = viewModelScope.async {
                remoteRepository.getPost()
            }

            val data = scope.await()
            for (item in data) {
                currencyList.add(
                    DashboardItemUiState(
                        charCode = item.charCode,
                        nominal = item.nominal,
                        name = item.name,
                        value = item.value,
                        previous = item.previous,
                        isBookmarked = false
                    )
                )
            }

            updateState(currencyList)
        }

    }

    /*private suspend fun getCurrency(): Deferred<List<CurrencyRequest>> {
        val scope = viewModelScope.async {
            remoteRepository.getPost()
        }

        return scope
    }*/
}