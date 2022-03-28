package com.memksim.exchanger.model.repos

import android.app.Application
import android.util.Log
import com.memksim.exchanger.MAIN_TAG
import com.memksim.exchanger.model.Data
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.model.ValuteForDb
import com.memksim.exchanger.model.datasources.database.ValuteDatabase
import com.memksim.exchanger.model.datasources.network.Client
import com.memksim.exchanger.model.datasources.network.ValuteApi
import com.memksim.exchanger.ui.list.Callback
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ValuteRepository(private val applicationContext: Application) {
    private val disposableBag = CompositeDisposable()

    private val api = Client.getClient().create(ValuteApi::class.java)

    private val valuteDatabase = ValuteDatabase.getDataBase(applicationContext)
    private val valuteDao = valuteDatabase.valutesDao()

    private fun clearBag(){
        disposableBag.dispose()
        Log.d(MAIN_TAG, "clearBag: cleared - ${disposableBag.size()}")
    }

    private fun parceToArray(data: Data): List<Valute>{
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

        return list
    }
    private fun parceToArray(valuteList: List<ValuteForDb>): List<Valute>{
        val list = ArrayList<Valute>()

        for(item in valuteList){
            list.add(item.convertToValute())
        }

        return list
    }
    private fun parceToArrayForDb(valuteList: List<Valute>): List<ValuteForDb>{
        val list = ArrayList<ValuteForDb>()

        for(item in valuteList){
            list.add(
                ValuteForDb(
                item.charCode,
                item.id,
                item.numCode,
                item.nominal,
                item.name,
                item.value,
                item.previous,
                )
            )
        }

        return list
    }

    fun getValuteFromNet(callback: Callback){
        disposableBag.add(api.getValute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Log.d(TAG, "Loaded!")
                callback.notifyDataIsUpdated(parceToArray(it))
            },{
                Log.e(MAIN_TAG, "$it")
            }))
    }



    fun getValuteFromDb(callback: Callback){
        disposableBag.add(valuteDao.getDataList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.notifyDataIsDelivered(parceToArray(it))
                //Log.d(TAG, "getValuteFromDb: loaded!")
            },{
                Log.e(MAIN_TAG, "getValuteFromDb: $it", it)
            }))
    }

    fun saveToDb(list: List<Valute>){
        disposableBag.add(Single
            .create<Array<ValuteForDb>> {
                parceToArrayForDb(list).toTypedArray()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                valuteDao.saveData(*it)
                //Log.d(TAG, "saveToDb: Saved! ${it.size}")
            },{
                Log.e(MAIN_TAG, "saveToDb: $it")
            }))
    }

}