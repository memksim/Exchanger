package com.memksim.exchanger.data.repositories

import android.util.Log
import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.entities.ValuteRequest
import com.memksim.exchanger.data.remote.CurrencyApi
import com.memksim.exchanger.data.remote.ValuteClient

class ValuteRepository {

    private val api: CurrencyApi by lazy {
        ValuteClient.getClient().create(CurrencyApi::class.java)
    }

    suspend fun getPost(): List<Currency>{
        return parceToArray(api.getCurrency().body())
    }

    private fun parceToArray(request: ValuteRequest?): List<Currency>{
        val data = request!!

        val list = ArrayList<Currency>()

        list.add(data.valute._aud)
        list.add(data.valute._azn)
        list.add(data.valute._gbp)
        list.add(data.valute._amd)
        list.add(data.valute._byn)
        list.add(data.valute._bgn)
        list.add(data.valute._brl)
        list.add(data.valute._huf)
        list.add(data.valute._hkd)
        list.add(data.valute._dkk)
        list.add(data.valute._usd)
        list.add(data.valute._eur)
        list.add(data.valute._inr)
        list.add(data.valute._kzt)
        list.add(data.valute._cad)
        list.add(data.valute._kgs)
        list.add(data.valute._cny)
        list.add(data.valute._mdl)
        list.add(data.valute._nok)
        list.add(data.valute._pln)
        list.add(data.valute._ron)
        list.add(data.valute._xdr)
        list.add(data.valute._sgd)
        list.add(data.valute._tjs)
        list.add(data.valute._try)
        list.add(data.valute._tmt)
        list.add(data.valute._uzs)
        list.add(data.valute._uah)
        list.add(data.valute._czk)
        list.add(data.valute._sek)
        list.add(data.valute._chf)
        list.add(data.valute._zar)
        list.add(data.valute._krw)
        list.add(data.valute._jpy)

        return list
    }

}