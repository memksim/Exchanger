package com.memksim.exchanger.model

import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("Valute") val valute: ValuteList
){

}




