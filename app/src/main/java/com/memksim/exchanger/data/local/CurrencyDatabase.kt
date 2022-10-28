package com.memksim.exchanger.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.memksim.exchanger.data.entities.Currency

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun getDao(): CurrencyDao

}