package com.memksim.exchanger.ui.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.model.repos.ValuteRepository

class ListPageViewModel: ViewModel() {

    private val repository = ValuteRepository()

    private val _data: MutableLiveData<List<Valute>> by lazy {
        MutableLiveData<List<Valute>>()
    }
    var liveData: LiveData<List<Valute>> = _data

    fun getValuteFromNet(){
        repository.getValuteFromNet()
    }


}