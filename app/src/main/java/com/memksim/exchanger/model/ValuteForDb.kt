package com.memksim.exchanger.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "valutes")
open class ValuteForDb(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_charCode")
    val charCode: String,
    @ColumnInfo(name = "_id")
    val id: String,
    @ColumnInfo(name = "_numCode")
    val numCode: String,
    @ColumnInfo(name = "_nominal")
    val nominal: Int,
    @ColumnInfo(name = "_name")
    val name: String,
    @ColumnInfo(name = "_value")
    val value: Double,
    @ColumnInfo(name = "_previousValue")
    val previousValue: Double
){

    fun convertToValute(): Valute{
        return Valute(
            id,
            numCode,
            charCode,
            nominal,
            name,
            value,
            previousValue
        )
    }

    override fun toString(): String {
        return "ValuteForDb(charCode='$charCode', id='$id', numCode='$numCode', nominal=$nominal, name='$name', value=$value, previousValue=$previousValue)"
    }


}