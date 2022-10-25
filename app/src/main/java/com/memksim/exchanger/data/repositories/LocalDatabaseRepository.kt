package com.memksim.exchanger.data.repositories

import android.content.Context
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.local.CurrencyDatabase

class LocalDatabaseRepository(
    private val context: Context
) {
    private val api = CurrencyDatabase.getInstance(context).getDao()

    suspend fun checkIfTableIsEmpty(): Boolean{
        return api.getCount() == 0
    }

    suspend fun getBookmarked(): List<Currency>{
        return api.getBookmarkedList()
    }

    suspend fun getCurrencyList(): List<Currency>{
        return api.getCurrencyList()
    }

    suspend fun saveCurrency(currencyList: List<Currency>){
        api.saveCurrency(*currencyList.toTypedArray())
    }

    suspend fun updateCurrency(currencyList: List<Currency>){
        api.updateCurrency(*currencyList.toTypedArray())
    }
}