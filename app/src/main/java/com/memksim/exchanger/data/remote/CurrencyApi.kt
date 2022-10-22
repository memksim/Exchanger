package com.memksim.exchanger.data.remote

import com.memksim.exchanger.data.entities.ValuteRequest
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrency(): Response<ValuteRequest>

}