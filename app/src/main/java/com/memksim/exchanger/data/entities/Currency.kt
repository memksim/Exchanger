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
)