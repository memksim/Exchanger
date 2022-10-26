package com.memksim.exchanger.ui.dashboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.entities.Valute
import com.memksim.exchanger.data.repositories.LocalDatabaseRepository
import com.memksim.exchanger.data.repositories.RetrofitRepository
import com.memksim.exchanger.usecases.RequestViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DashboardViewModel(
    application: Application
) : RequestViewModel(application) {

    private val _liveData: MutableLiveData<DashboardUiState> = liveData {
        emit(
            DashboardUiState(
                parseCurrencyListToUiItemList(localRepository.getCurrencyList())
            )
        )
    } as MutableLiveData<DashboardUiState>
    val liveData: LiveData<DashboardUiState> = _liveData

    private fun updateState(items: List<DashboardItemUiState>) {
        _liveData.value = DashboardUiState(
            items
        )
    }

    fun refreshData(){
        viewModelScope.launch {
            val scope = viewModelScope.async{
                localRepository.getCurrencyList()
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

            updateState(parseCurrencyListToUiItemList(data))
            saveCurrencyLocal(data)
        }
    }

    private fun parseCurrencyListToUiItemList(currencyList: List<Currency>): List<DashboardItemUiState> {
        val itemList = mutableListOf<DashboardItemUiState>()

        for (i in currencyList.indices) {
            itemList.add(parseCurrencyToUiItemState(currencyList[i]))
        }

        return itemList
    }

    private fun parseCurrencyToUiItemState(
        c: Currency
    ): DashboardItemUiState {
        return DashboardItemUiState(
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
            }
        }
    }

    private fun parseUiItemToCurrency(item: DashboardItemUiState): Currency{
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

    private fun parseUiItemListToCurrencyList(itemList: List<DashboardItemUiState>): List<Currency>{
        val resultList = arrayListOf<Currency>()
        for(i in itemList){
            resultList.add(
                parseUiItemToCurrency(i)
            )
        }
        return resultList
    }

}
