package com.memksim.exchanger.ui.inner_exchange

import com.memksim.exchanger.model.Valute

data class InnerExchangePageState(
    val valute: Valute,
    val sumInRub: Double,
    val sumInValute: Double
)
