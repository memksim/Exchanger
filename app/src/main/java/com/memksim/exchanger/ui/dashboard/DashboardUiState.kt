package com.memksim.exchanger.ui.dashboard

import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.usecases.ItemState
import com.memksim.exchanger.usecases.UiState

data class DashboardUiState(
    val itemStateList: List<DashboardItemUiState>
) : UiState

data class DashboardItemUiState(
    val charCode: String,
    val nominal: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val isBookmarked: Boolean,
    val isTrendingUp: Boolean,
    val onSaveBookmark: (DashboardItemUiState) -> Unit
) : ItemState{

    companion object{
        fun convertListToCurrencyList(itemList: List<DashboardItemUiState>): List<Currency>{
            val resultList = arrayListOf<Currency>()

            itemList.forEach {
                resultList.add(
                    it.convertToCurrency()
                )
            }

            return resultList
        }

        fun convertCurrencyToItem(
            c: Currency,
            onSaveBookmark: (DashboardItemUiState) -> Unit
        ): DashboardItemUiState {
            return DashboardItemUiState(
                c.charCode,
                c.nominal,
                c.name,
                c.value,
                c.previous,
                c.isBookmarked,
                c.isTrendingUp,
                onSaveBookmark
            )
        }

        fun convertCurrencyListToItemList(
            currencyList: List<Currency>,
            onSaveBookmark: (DashboardItemUiState) -> Unit
        ): List<DashboardItemUiState> {
            val itemList = mutableListOf<DashboardItemUiState>()

            currencyList.forEach {
                itemList.add(convertCurrencyToItem(it, onSaveBookmark))
            }

            return itemList
        }
    }

    fun convertToCurrency(): Currency {
        return Currency(
            this.charCode,
            this.nominal,
            this.name,
            this.value,
            this.previous,
            this.isBookmarked,
            this.isTrendingUp
        )
    }

}
