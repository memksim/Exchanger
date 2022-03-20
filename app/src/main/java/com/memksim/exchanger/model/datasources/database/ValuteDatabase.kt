package com.memksim.exchanger.model.datasources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.memksim.exchanger.model.ValuteForDb

@Database(entities = [ValuteForDb::class], version = 1, exportSchema = false)
abstract class ValuteDatabase: RoomDatabase() {

    abstract fun valutesDao(): ValuteDao

    companion object{
        private var INSTANCE: ValuteDatabase? = null

        fun getDataBase(context: Context): ValuteDatabase{
            val template = INSTANCE
            if(template != null) return template

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ValuteDatabase::class.java,
                    "valutesDatabase"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }

    }

}