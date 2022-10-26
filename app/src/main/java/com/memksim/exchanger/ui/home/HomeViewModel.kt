package com.memksim.exchanger.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.entities.Valute
import com.memksim.exchanger.ui.dashboard.DashboardItemUiState
import com.memksim.exchanger.ui.dashboard.DashboardUiState
import com.memksim.exchanger.usecases.RequestViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : RequestViewModel(application) {

    private val _liveData: MutableLiveData<HomePageUiState> = liveData {
        emit(
            HomePageUiState(
                parseCurrencyListToUiItemList(localRepository.getBookmarkedList())
            )
        )
    } as MutableLiveData<HomePageUiState>
    val liveData: LiveData<HomePageUiState> = _liveData

    private fun updateState(items: List<HomePageItemUiState>) {
        _liveData.value = HomePageUiState(
            items
        )
    }

    fun refreshData() {
        viewModelScope.launch {
            val scope = viewModelScope.async {
                localRepository.getBookmarkedList()
            }
            val data: List<Currency> = scope.await()
            updateState(parseCurrencyListToUiItemList(data))
        }
    }

    override fun loadNetworkData() {
        viewModelScope.launch {

            val scope = viewModelScope.async {
                if(isTableEmpty){
                    remoteRepository.getPost()
                }else{
                    remoteRepository.getPost(parseUiItemListToCurrencyList(_liveData.value!!.itemStateList))
                }
            }
            val data = scope.await()

            saveCurrencyLocal(data)
            refreshData()
        }
    }

    private fun parseCurrencyListToUiItemList(currencyList: List<Currency>): List<HomePageItemUiState> {
        val itemList = mutableListOf<HomePageItemUiState>()

        for (i in currencyList.indices) {
            itemList.add(parseCurrencyToUiItemState(currencyList[i]))
        }

        return itemList
    }

    private fun parseCurrencyToUiItemState(
        c: Currency
    ): HomePageItemUiState {
        return HomePageItemUiState(
            "",
            c.charCode,
            c.nominal,
            c.name,
            c.value,
            c.previous,
            c.isBookmarked,
            c.isTrendingUp
        ) {
            viewModelScope.launch {
                localRepository.updateCurrency(parseUiItemToCurrency(it))
                refreshData()
            }
        }
    }

    private fun parseUiItemListToCurrencyList(itemList: List<HomePageItemUiState>): List<Currency> {
        val resultList = arrayListOf<Currency>()
        for (i in itemList) {
            resultList.add(
                parseUiItemToCurrency(i)
            )
        }
        return resultList
    }

    private fun parseUiItemToCurrency(item: HomePageItemUiState): Currency {
        return Currency(
            item.charCode,
            item.nominal,
            item.name,
            item.value,
            item.previous,
            item.isBookmarked,
            item.isTrendingUp
        )
    }
}