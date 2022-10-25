package com.memksim.exchanger.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.memksim.exchanger.data.entities.Currency

@Dao
interface CurrencyDao {

    @Query("SELECT count(*) FROM currency_table")
    suspend fun getCount(): Int

    @Query("SELECT * FROM currency_table")
    suspend fun getCurrencyList(): List<Currency>

    @Query("SELECT * FROM currency_table WHERE isBookmarked = 1")
    suspend fun getBookmarkedList(): List<Currency>

    @Insert
    suspend fun saveCurrency(vararg currency: Currency)

    @Update
    suspend fun updateCurrency(vararg currency: Currency)

}