package com.memksim.exchanger.data.entities

import com.google.gson.annotations.SerializedName

data class ValuteRequest(
    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("Valute") val valute: ValuteList
)

data class ValuteList(
    @SerializedName("AUD") val _aud: Valute,
    @SerializedName("AZN") val _azn: Valute,
    @SerializedName("GBP") val _gbp: Valute,
    @SerializedName("AMD") val _amd: Valute,
    @SerializedName("BYN") val _byn: Valute,
    @SerializedName("BGN") val _bgn: Valute,
    @SerializedName("BRL") val _brl: Valute,
    @SerializedName("HUF") val _huf: Valute,
    @SerializedName("HKD") val _hkd: Valute,
    @SerializedName("DKK") val _dkk: Valute,
    @SerializedName("USD") val _usd: Valute,
    @SerializedName("EUR") val _eur: Valute,
    @SerializedName("INR") val _inr: Valute,
    @SerializedName("KZT") val _kzt: Valute,
    @SerializedName("CAD") val _cad: Valute,
    @SerializedName("KGS") val _kgs: Valute,
    @SerializedName("CNY") val _cny: Valute,
    @SerializedName("MDL") val _mdl: Valute,
    @SerializedName("NOK") val _nok: Valute,
    @SerializedName("PLN") val _pln: Valute,
    @SerializedName("RON") val _ron: Valute,
    @SerializedName("XDR") val _xdr: Valute,
    @SerializedName("SGD") val _sgd: Valute,
    @SerializedName("TJS") val _tjs: Valute,
    @SerializedName("TRY") val _try: Valute,
    @SerializedName("TMT") val _tmt: Valute,
    @SerializedName("UZS") val _uzs: Valute,
    @SerializedName("UAH") val _uah: Valute,
    @SerializedName("CZK") val _czk: Valute,
    @SerializedName("SEK") val _sek: Valute,
    @SerializedName("CHF") val _chf: Valute,
    @SerializedName("ZAR") val _zar: Valute,
    @SerializedName("KRW") val _krw: Valute,
    @SerializedName("JPY") val _jpy: Valute

)
