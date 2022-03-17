package com.memksim.exchanger.model.datasources.network

import com.memksim.exchanger.model.Data
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ValuteApi {

    @GET("daily_json.js")
    fun getValute(): Single<Data>

}