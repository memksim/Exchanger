package com.memksim.exchanger.model.repos

import android.renderscript.Sampler
import android.util.Log
import com.memksim.exchanger.TAG
import com.memksim.exchanger.model.Data
import com.memksim.exchanger.model.Valute
import com.memksim.exchanger.model.datasources.network.Client
import com.memksim.exchanger.model.datasources.network.ValuteApi
import com.memksim.exchanger.ui.state.Callback
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.create

class ValuteRepository {
    private val disposableBag = CompositeDisposable()

    fun clearBag(){
        disposableBag.dispose()
        Log.d(TAG, "clearBag: cleared - ${disposableBag.size()}")
    }

    fun getValuteFromNet(callback: Callback){
        val api = Client.getClient().create(ValuteApi::class.java)

        disposableBag.add(api.getValute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val arrList: List<Valute> = parceToArray(it)
                callback.notifyDataSetChanged(arrList)
                Log.d(TAG, "$arrList")
            },{
                Log.e(TAG, "$it")
            }))
    }

    fun parceToArray(data: Data): List<Valute>{
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
}