package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.forex.response.ForexIntraday
import com.jinxservers.alphavantage.util.OutputSize
import com.jinxservers.alphavantage.util.ShortInterval
import io.ktor.http.*

internal class IntradayRequest(
    fromSymbol: String,
    toSymbol: String,
    interval: ShortInterval? = null,
    outputSize: OutputSize? = null
) : Request<ForexIntraday>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "FX_INTRADAY")
            append("from_symbol", fromSymbol)
            append("to_symbol", toSymbol)
            if (interval != null) {
                append("interval", interval.value())
            }
            if (outputSize != null) {
                append("outputsize", outputSize.size)
            }
        }
    }
)