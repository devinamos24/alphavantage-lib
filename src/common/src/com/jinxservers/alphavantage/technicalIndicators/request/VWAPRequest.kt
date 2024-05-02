package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.VWAP
import com.jinxservers.alphavantage.util.ShortInterval
import io.ktor.http.*


internal class VWAPRequest(
    symbol: String,
    interval: ShortInterval,
    month: String? = null,
) : Request<VWAP>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "VWAP")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                append("month", it)
            }
        }
    }
)