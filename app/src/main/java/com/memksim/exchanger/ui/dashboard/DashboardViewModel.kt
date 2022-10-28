package com.memksim.exchanger.ui.dashboard

import androidx.lifecycle.*
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.repositories.LocalDatabaseRepository
import com.memksim.exchanger.data.repositories.RetrofitRepository
import com.memksim.exchanger.usecases.RequestViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val remoteRepository: RetrofitRepository,
    private val localRepository: LocalDatabaseRepository
) : RequestViewModel(localRepository) {

    private val _liveData: MutableLiveData<DashboardUiState> = liveData {
        emit(
            DashboardUiState(
                DashboardItemUiState
                    .convertCurrencyListToItemList(
                        localRepository.getCurrencyList()
                    ) {
                        onBookmark(it)
                    }
            )
        )
    } as MutableLiveData<DashboardUiState>
    val liveData: LiveData<DashboardUiState> = _liveData

    private fun updateState(items: List<DashboardItemUiState>) {
        _liveData.value = DashboardUiState(
            items
        )
    }

    private fun onBookmark(item: DashboardItemUiState) {
        viewModelScope.launch {
            localRepository.updateCurrency(item.convertToCurrency())
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            val scope = viewModelScope.async {
                localRepository.getCurrencyList()
            }
            val data: List<Currency> = scope.await()
            updateState(
                DashboardItemUiState
                    .convertCurrencyListToItemList(data) {
                        onBookmark(it)
                    }
            )
        }
    }

    override fun loadNetworkData() {
        viewModelScope.launch {

            val scope = viewModelScope.async {
                if (isTableEmpty) {
                    remoteRepository.getPost()
                } else {
                    remoteRepository.getPost(
                        DashboardItemUiState
                            .convertListToCurrencyList(_liveData.value!!.itemStateList)
                    )
                }
            }
            val data = scope.await()

            updateState(
                DashboardItemUiState
                    .convertCurrencyListToItemList(data){
                        onBookmark(it)
                    }
            )
            saveCurrencyLocal(data)
        }
    }

}
