package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.forex.response.ForexIntraday
import com.jinxservers.alphavantage.util.OutputSize
import com.jinxservers.alphavantage.util.ShortInterval
import io.ktor.http.*

internal class IntradayRequest(
    fromSymbol: String,
    toSymbol: String,
    interval: ShortInterval,
    outputSize: OutputSize = OutputSize.COMPACT
) : Request<ForexIntraday>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "FX_INTRADAY")
            append("from_symbol", fromSymbol)
            append("to_symbol", toSymbol)
            append("interval", interval.value())
            append("outputsize", outputSize.size)
        }
    }
)