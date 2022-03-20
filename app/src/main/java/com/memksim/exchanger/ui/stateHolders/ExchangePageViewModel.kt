package com.memksim.exchanger.ui.stateHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.ui.state.ExchangePageState

class ExchangePageViewModel: ViewModel() {

    private val _data: MutableLiveData<ExchangePageState> by lazy {
        MutableLiveData()
    }
    var liveData: LiveData<ExchangePageState> = _data

    fun setState(valute: Valute){
        _data.value = ExchangePageState(
            valute,
            valute.nominal.toDouble(),
            valute.value
        )
    }

    fun convertValuteToRub(rubCount: Int){

    }



}