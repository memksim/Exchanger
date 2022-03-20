package com.memksim.exchanger.ui.stateHolders

import com.memksim.exchanger.model.Valute

interface Callback {

    fun notifyDataIsUpdated(data: List<Valute>)
    fun notifyDataIsDelivered(data: List<Valute>)

}