package com.memksim.exchanger.model.repos

import android.util.Log
import com.memksim.exchanger.TAG
import com.memksim.exchanger.model.datasources.network.Client
import com.memksim.exchanger.model.datasources.network.ValuteApi
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

    fun getValuteFromNet(){
        val api = Client.getClient().create(ValuteApi::class.java)

        disposableBag.add(api.getValute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "$it")
            },{
                Log.e(TAG, "$it")
            }))
    }
}