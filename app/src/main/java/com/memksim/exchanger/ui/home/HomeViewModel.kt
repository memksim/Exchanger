package com.memksim.exchanger.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.repositories.LocalDatabaseRepository
import com.memksim.exchanger.data.repositories.RetrofitRepository
import com.memksim.exchanger.usecases.RequestViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteRepository: RetrofitRepository,
    private val localRepository: LocalDatabaseRepository
) : RequestViewModel(localRepository) {

    private val _liveData: MutableLiveData<HomePageUiState> = liveData {
        emit(
            HomePageUiState(
                HomePageItemUiState
                    .convertCurrencyListToItemList(
                        localRepository.getBookmarkedList()
                    ){
                        onRemoveBookmark(it)
                    }
            )
        )
    } as MutableLiveData<HomePageUiState>
    val liveData: LiveData<HomePageUiState> = _liveData

    private fun updateState(items: List<HomePageItemUiState>) {
        _liveData.value = HomePageUiState(
            items
        )
    }

    private fun onRemoveBookmark(item: HomePageItemUiState) {
        viewModelScope.launch {
            localRepository.updateCurrency(item.convertToCurrency())
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            val scope = viewModelScope.async {
                localRepository.getBookmarkedList()
            }
            val data: List<Currency> = scope.await()
            updateState(
                HomePageItemUiState
                    .convertCurrencyListToItemList(data) {
                        onRemoveBookmark(it)
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
                        HomePageItemUiState
                            .convertListToCurrencyList(_liveData.value!!.itemStateList)
                    )
                }
            }
            val data = scope.await()

            saveCurrencyLocal(data)
            refreshData()
        }
    }
}