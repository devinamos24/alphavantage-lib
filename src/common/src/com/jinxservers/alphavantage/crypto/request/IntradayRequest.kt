package com.jinxservers.alphavantage.crypto.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.crypto.response.CryptoIntraday
import com.jinxservers.alphavantage.forex.response.ForexIntraday
import com.jinxservers.alphavantage.util.ShortInterval
import io.ktor.http.*

internal class IntradayRequest(
    symbol: String,
    market: String,
    interval: ShortInterval,
    outputSize: String = "compact"
) : Request<CryptoIntraday>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "CRYPTO_INTRADAY")
            append("symbol", symbol)
            append("market", market)
            append("interval", interval.value())
            append("outputsize", outputSize)
        }
    }
)