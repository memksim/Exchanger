package com.memksim.exchanger.data.entities

import com.google.gson.annotations.SerializedName

data class ValuteRequest(
    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("Valute") val valute: ValuteList
)

data class ValuteList(
    @SerializedName("AUD") val _aud: Currency,
    @SerializedName("AZN") val _azn: Currency,
    @SerializedName("GBP") val _gbp: Currency,
    @SerializedName("AMD") val _amd: Currency,
    @SerializedName("BYN") val _byn: Currency,
    @SerializedName("BGN") val _bgn: Currency,
    @SerializedName("BRL") val _brl: Currency,
    @SerializedName("HUF") val _huf: Currency,
    @SerializedName("HKD") val _hkd: Currency,
    @SerializedName("DKK") val _dkk: Currency,
    @SerializedName("USD") val _usd: Currency,
    @SerializedName("EUR") val _eur: Currency,
    @SerializedName("INR") val _inr: Currency,
    @SerializedName("KZT") val _kzt: Currency,
    @SerializedName("CAD") val _cad: Currency,
    @SerializedName("KGS") val _kgs: Currency,
    @SerializedName("CNY") val _cny: Currency,
    @SerializedName("MDL") val _mdl: Currency,
    @SerializedName("NOK") val _nok: Currency,
    @SerializedName("PLN") val _pln: Currency,
    @SerializedName("RON") val _ron: Currency,
    @SerializedName("XDR") val _xdr: Currency,
    @SerializedName("SGD") val _sgd: Currency,
    @SerializedName("TJS") val _tjs: Currency,
    @SerializedName("TRY") val _try: Currency,
    @SerializedName("TMT") val _tmt: Currency,
    @SerializedName("UZS") val _uzs: Currency,
    @SerializedName("UAH") val _uah: Currency,
    @SerializedName("CZK") val _czk: Currency,
    @SerializedName("SEK") val _sek: Currency,
    @SerializedName("CHF") val _chf: Currency,
    @SerializedName("ZAR") val _zar: Currency,
    @SerializedName("KRW") val _krw: Currency,
    @SerializedName("JPY") val _jpy: Currency

)
