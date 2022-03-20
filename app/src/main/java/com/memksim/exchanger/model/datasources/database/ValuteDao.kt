package com.memksim.exchanger.model.datasources.database

import androidx.room.*
import com.memksim.exchanger.model.ValuteForDb
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface ValuteDao {

    @Insert
    fun saveData(vararg valute: ValuteForDb)

    @Update
    fun updateData(vararg valute: ValuteForDb)

    @Query("SELECT * FROM valutes")
    fun getDataList(): Flowable<List<ValuteForDb>>

    @Query("SELECT count(*) FROM valutes")
    fun getCount(): Int

    @Delete()
    fun clearData(vararg valute: ValuteForDb)

}