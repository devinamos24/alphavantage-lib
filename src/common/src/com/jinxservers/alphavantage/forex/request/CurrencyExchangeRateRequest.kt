package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.forex.response.CurrencyExchangeRate
import io.ktor.http.*

internal class CurrencyExchangeRateRequest(
    fromCurrency: String,
    toCurrency: String,
) : Request<CurrencyExchangeRate>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "CURRENCY_EXCHANGE_RATE")
            append("from_currency", fromCurrency)
            append("to_currency", toCurrency)
        }
    },
)