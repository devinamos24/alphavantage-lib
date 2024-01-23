package com.jinxservers.alphavantage.crypto.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.crypto.response.CryptoWeekly
import com.jinxservers.alphavantage.forex.response.ForexDaily
import io.ktor.http.*

internal class WeeklyRequest(
    symbol: String,
    market: String,
) : Request<CryptoWeekly>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "DIGITAL_CURRENCY_WEEKLY")
            append("from_symbol", symbol)
            append("to_symbol", market)
        }
    }
)