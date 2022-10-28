package com.memksim.exchanger.ui.home

import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.ui.dashboard.DashboardItemUiState
import com.memksim.exchanger.usecases.ItemState
import com.memksim.exchanger.usecases.UiState

data class HomePageUiState(
    val itemStateList: List<HomePageItemUiState>
) : UiState

data class HomePageItemUiState(
    val lastUpdated: String,
    val charCode: String,
    val nominal: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val isBookmarked: Boolean,
    val isTrendingUp: Boolean,
    val onRemoveBookmark: (HomePageItemUiState) -> Unit
) : ItemState{
    companion object{
        fun convertListToCurrencyList(itemList: List<HomePageItemUiState>): List<Currency>{
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
            onRemoveBookmark: (HomePageItemUiState) -> Unit
        ): HomePageItemUiState {
            return HomePageItemUiState(
                "",
                c.charCode,
                c.nominal,
                c.name,
                c.value,
                c.previous,
                c.isBookmarked,
                c.isTrendingUp,
                onRemoveBookmark
            )
        }

        fun convertCurrencyListToItemList(
            currencyList: List<Currency>,
            onRemoveBookmark: (HomePageItemUiState) -> Unit
        ): List<HomePageItemUiState> {
            val itemList = mutableListOf<HomePageItemUiState>()

            currencyList.forEach {
                itemList.add(convertCurrencyToItem(it, onRemoveBookmark))
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