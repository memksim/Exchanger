package com.memksim.exchanger.ui.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.memksim.exchanger.MAIN_TAG
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.model.repos.ValuteRepository

interface Callback {

    fun notifyDataIsUpdated(data: List<Valute>)
    fun notifyDataIsDelivered(data: List<Valute>)

}

class ListPageViewModel(application: Application):
    AndroidViewModel(application), Callback {

    private val repository = ValuteRepository(application)

    private val _data: MutableLiveData<List<Valute>> by lazy {
        MutableLiveData<List<Valute>>()
    }
    var liveData: LiveData<List<Valute>> = _data

    fun getValuteFromNet(){
        repository.getValuteFromNet(this)

    }

    fun getValuteFromDb(){
        repository.getValuteFromDb(this)
    }

    private fun saveToDb(){
        repository.saveToDb(_data.value!!)
    }

    override fun notifyDataIsUpdated(data: List<Valute>) {
        _data.value = data
        saveToDb()
    }

    override fun notifyDataIsDelivered(data: List<Valute>) {
        _data.value = data
        Log.d(MAIN_TAG, "notifyDataIsDelivered: $data")
    }

}