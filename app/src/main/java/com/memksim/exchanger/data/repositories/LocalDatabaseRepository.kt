package com.memksim.exchanger.data.repositories

import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.local.CurrencyDao
import javax.inject.Inject

class LocalDatabaseRepository @Inject constructor(
    private val api: CurrencyDao
) {

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