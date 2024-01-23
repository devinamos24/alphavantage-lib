package com.jinxservers.alphavantage.crypto.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.crypto.response.CryptoDaily
import com.jinxservers.alphavantage.forex.response.ForexDaily
import io.ktor.http.*

internal class DailyRequest(
    symbol: String,
    market: String,
) : Request<CryptoDaily>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "DIGITAL_CURRENCY_DAILY")
            append("from_symbol", symbol)
            append("to_symbol", market)
        }
    }
)