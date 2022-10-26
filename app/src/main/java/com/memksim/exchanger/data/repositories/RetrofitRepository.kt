package com.memksim.exchanger.data.repositories

import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.entities.Valute
import com.memksim.exchanger.data.entities.ValuteRequest
import com.memksim.exchanger.data.remote.ValuteApi
import com.memksim.exchanger.data.remote.ValuteClient

class RetrofitRepository {

    private val api: ValuteApi by lazy {
        ValuteClient.getClient().create(ValuteApi::class.java)
    }

    suspend fun getPost(): List<Currency> {
        return parseToList(api.getCurrency().body())
    }

    suspend fun getPost(oldList: List<Currency>): List<Currency> {
        return compareWithOldList(
            oldList = oldList,
            newList = parseToList(api.getCurrency().body())
        )
    }

    private fun parseToList(request: ValuteRequest?): List<Currency> {
        val data = request!!

        val list = ArrayList<Valute>()

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

        return valuteListToCurrencyList(list)
    }

    private fun compareWithOldList(
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
        /*for((i, item) in newList.withIndex()){
            resultList.add(
                Currency(
                    charCode = item.charCode,
                    nominal = item.nominal,
                    name = item.name,
                    value = item.value,
                    previous = oldList[i].value,
                    isBookmarked = oldList[i].isBookmarked,
                    isTrendingUp = item.value < item.previous
                )
            )
        }*/
        return resultList
    }

    private fun valuteListToCurrencyList(valuteList: List<Valute>): List<Currency> {
        val resultList = arrayListOf<Currency>()

        for (i in valuteList.indices) {
            resultList.add(
                Currency(
                    charCode = valuteList[i].charCode,
                    nominal = adaptNominal(valuteList[i].nominal),
                    name = valuteList[i].name,
                    value = adaptValue(valuteList[i].value),
                    previous = valuteList[i].previous,
                    isBookmarked = false,
                    isTrendingUp = checkIsTrendingGrows(valuteList[i].previous, valuteList[i].value)
                )
            )
        }

        return resultList
    }

    private fun adaptValue(d: Double): Double = (d * 100).toInt().toDouble() / 100

    private fun adaptNominal(n: Int): String {
        return if (n < 1000) {
            n.toString()
        } else {
            val str = n / 1000
            "${str}k"
        }
    }

    private fun checkIsTrendingGrows(prev: Double, now: Double): Boolean {
        return now < prev
    }

}