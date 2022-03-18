package com.memksim.exchanger.ui.state

import com.memksim.exchanger.model.Valute

interface Callback {

    fun notifyDataSetChanged(data: List<Valute>)

}