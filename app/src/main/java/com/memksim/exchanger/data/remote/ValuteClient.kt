package com.memksim.exchanger.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ValuteClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!
    }

}