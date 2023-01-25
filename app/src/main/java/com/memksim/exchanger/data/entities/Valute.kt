package com.memksim.exchanger.data.entities

import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Previous")
    val previous: Double
){
    fun adaptValue(): Double = (value * 100).toInt().toDouble() / 100

    fun adaptNominal(): String {
        return if (nominal < 1000) {
            nominal.toString()
        } else {
            val str = nominal / 1000
            "${str}k"
        }
    }

    fun checkIsTrendingGrows(): Boolean {
        return value < previous
    }
}

data class ValuteRequest(
    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("Valute") val valute: Valutes
){
    fun convertToValuteList(): List<Valute>{
        val list = ArrayList<Valute>()

        list.add(this.valute._aud)
        list.add(this.valute._azn)
        list.add(this.valute._gbp)
        list.add(this.valute._amd)
        list.add(this.valute._byn)
        list.add(this.valute._bgn)
        list.add(this.valute._brl)
        list.add(this.valute._huf)
        list.add(this.valute._hkd)
        list.add(this.valute._dkk)
        list.add(this.valute._usd)
        list.add(this.valute._eur)
        list.add(this.valute._inr)
        list.add(this.valute._kzt)
        list.add(this.valute._cad)
        list.add(this.valute._kgs)
        list.add(this.valute._cny)
        list.add(this.valute._mdl)
        list.add(this.valute._nok)
        list.add(this.valute._pln)
        list.add(this.valute._ron)
        list.add(this.valute._xdr)
        list.add(this.valute._sgd)
        list.add(this.valute._tjs)
        list.add(this.valute._try)
        list.add(this.valute._tmt)
        list.add(this.valute._uzs)
        list.add(this.valute._uah)
        list.add(this.valute._czk)
        list.add(this.valute._sek)
        list.add(this.valute._chf)
        list.add(this.valute._zar)
        list.add(this.valute._krw)
        list.add(this.valute._jpy)

        return list
    }
}


data class Valutes(
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