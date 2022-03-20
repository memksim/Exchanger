package com.memksim.exchanger.ui.state

import com.memksim.exchanger.model.Valute

data class ExchangePageState(
    val valute: Valute,
    val sumInRub: Double,
    val sumInValute: Double
)
