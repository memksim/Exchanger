package com.memksim.exchanger.data.repositories

import android.content.Context
import android.util.Log
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.local.CurrencyDatabase

class LocalDatabaseRepository(
    private val context: Context
) {
    private val api = CurrencyDatabase.getInstance(context).getDao()

    suspend fun checkIfTableIsEmpty(): Boolean {
        return api.getCount() == 0
    }

    suspend fun getBookmarkedList(): List<Currency> {
        return api.getBookmarkedList()
    }

    suspend fun getCurrencyList(): List<Currency> {
        return api.getCurrencyList()
    }

    suspend fun saveCurrency(vararg c: Currency) {
        api.saveCurrency(*c)
    }

    suspend fun updateCurrency(vararg c: Currency) {
        api.updateCurrency(*c)
    }
}