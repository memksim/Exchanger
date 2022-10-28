package com.memksim.exchanger.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class Currency(
    @PrimaryKey(autoGenerate = false)
    val charCode: String,
    val nominal: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val isBookmarked: Boolean,
    val isTrendingUp: Boolean
){
    companion object{
        fun compareWithOldList(
            oldList: List<Currency>,
            newList: List<Currency>
        ): List<Currency> {
            val resultList = arrayListOf<Currency>()
            newList.forEach { newC ->
                oldList.forEach { oldC ->
                    if (newC.charCode == oldC.charCode) {
                        resultList.add(
                            Currency(
                                charCode = newC.charCode,
                                nominal = newC.nominal,
                                name = newC.name,
                                value = newC.value,
                                previous = oldC.value,
                                isBookmarked = oldC.isBookmarked,
                                isTrendingUp = newC.value < newC.previous
                            )
                        )
                    }

                }
            }

            return resultList
        }
    }

}