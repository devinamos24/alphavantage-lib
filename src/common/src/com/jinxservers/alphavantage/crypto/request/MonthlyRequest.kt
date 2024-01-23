package com.jinxservers.alphavantage.crypto.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.crypto.response.CryptoMonthly
import io.ktor.http.*

internal class MonthlyRequest(
    symbol: String,
    market: String,
) : Request<CryptoMonthly>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "DIGITAL_CURRENCY_MONTHLY")
            append("from_symbol", symbol)
            append("to_symbol", market)
        }
    }
)