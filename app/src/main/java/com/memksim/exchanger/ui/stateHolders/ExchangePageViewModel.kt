package com.memksim.exchanger.ui.stateHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.ui.state.ExchangePageState
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangePageViewModel: ViewModel() {

    private val _data: MutableLiveData<ExchangePageState> by lazy {
        MutableLiveData()
    }
    var liveData: LiveData<ExchangePageState> = _data

    fun setState(valute: Valute){
        _data.value = ExchangePageState(
            valute = valute,
            sumInRub = valute.value,
            sumInValute = valute.nominal.toDouble()
        )
    }

    fun convertRubToValute(rubCount: Double){
        val valute = _data.value!!.valute

        val result = BigDecimal(rubCount / valute.value).setScale(3, RoundingMode.CEILING).toDouble()

        _data.value = ExchangePageState(
            valute = valute,
            sumInRub = rubCount,
            sumInValute = result
        )

    }

    fun convertValuteToRub(valuteCount: Double){
        val valute = _data.value!!.valute

        val oneValute = valuteCount/valute.nominal
        val result = BigDecimal(oneValute * valute.value).setScale(3, RoundingMode.CEILING).toDouble()

        _data.value = ExchangePageState(
            valute = valute,
            sumInRub = result,
            sumInValute = valuteCount
        )

    }



}