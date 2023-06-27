package com.academy.bangkit.jetskincare.ui

import java.text.NumberFormat
import java.util.Locale

fun String.withCurrencyFormat(): String {
    val rupiahExchangeRate = 15003.62
    var priceOnDollar = this.toDouble() / rupiahExchangeRate

    var mCurrencyFormat = NumberFormat.getCurrencyInstance()
    val deviceLocale = Locale.getDefault().country
    if (deviceLocale.equals("ID")) {
        priceOnDollar *= rupiahExchangeRate
    } else {
        mCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
    }

    return mCurrencyFormat.format(priceOnDollar)
}