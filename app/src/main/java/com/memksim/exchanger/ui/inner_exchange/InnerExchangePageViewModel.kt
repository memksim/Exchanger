package com.memksim.exchanger.ui.inner_exchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.exchanger.model.Valute
import java.math.BigDecimal
import java.math.RoundingMode

class InnerExchangePageViewModel: ViewModel() {

    private val _data: MutableLiveData<InnerExchangePageState> by lazy {
        MutableLiveData()
    }
    var liveData: LiveData<InnerExchangePageState> = _data

    fun setState(valute: Valute){
        _data.value = InnerExchangePageState(
            valute = valute,
            sumInRub = valute.value,
            sumInValute = valute.nominal.toDouble()
        )
    }

    fun convertRubToValute(rubCount: Double){
        val valute = _data.value!!.valute

        val result = BigDecimal(rubCount / valute.value).setScale(3, RoundingMode.CEILING).toDouble()

        _data.value = InnerExchangePageState(
            valute = valute,
            sumInRub = rubCount,
            sumInValute = result
        )

    }

    fun convertValuteToRub(valuteCount: Double){
        val valute = _data.value!!.valute

        val oneValute = valuteCount/valute.nominal
        val result = BigDecimal(oneValute * valute.value).setScale(3, RoundingMode.CEILING).toDouble()

        _data.value = InnerExchangePageState(
            valute = valute,
            sumInRub = result,
            sumInValute = valuteCount
        )

    }



}