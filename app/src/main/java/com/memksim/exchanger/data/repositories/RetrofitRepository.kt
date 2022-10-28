package com.memksim.exchanger.data.repositories

import com.memksim.exchanger.data.entities.Currency
import com.memksim.exchanger.data.entities.Valute
import com.memksim.exchanger.data.entities.ValuteRequest
import com.memksim.exchanger.data.remote.ValuteApi
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val api: ValuteApi
) {

    suspend fun getPost(): List<Currency> {
        return parseToList(api.getCurrency().body())
    }

    suspend fun getPost(oldList: List<Currency>): List<Currency> {
        return Currency.compareWithOldList(
            oldList = oldList,
            newList = parseToList(api.getCurrency().body())
        )
    }

    private fun parseToList(request: ValuteRequest?): List<Currency> {
        val data = request!!

        return valuteListToCurrencyList(data.convertToValuteList())
    }


    private fun valuteListToCurrencyList(valuteList: List<Valute>): List<Currency> {
        val resultList = arrayListOf<Currency>()

        for (valute in valuteList) {
            resultList.add(
                Currency(
                    charCode = valute.charCode,
                    nominal = valute.adaptNominal(),
                    name = valute.name,
                    value = valute.adaptValue(),
                    previous = valute.previous,
                    isBookmarked = false,
                    isTrendingUp = valute.checkIsTrendingGrows()
                )
            )
        }

        return resultList
    }

}