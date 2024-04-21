package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.CoreIntraday
import com.jinxservers.alphavantage.util.OutputSize
import com.jinxservers.alphavantage.util.ShortInterval
import io.ktor.http.*

//TODO: Add input validation to month parameter
internal class IntradayRequest(
    symbol: String,
    interval: ShortInterval,
    adjusted: Boolean? = null,
    extendedHours: Boolean? = null,
    month: String? = null,
    outputSize: OutputSize? = null,
) : Request<CoreIntraday>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_INTRADAY")
            append("symbol", symbol)
            append("interval", interval.value())
            if (adjusted != null) {
                append("adjusted", adjusted.toString())
            }
            if (extendedHours != null) {
                append("extended_hours", extendedHours.toString())
            }
            if (month != null) {
                append("month", month)
            }
            if (outputSize != null) {
                append("outputsize", outputSize.size)
            }
        }
    }
)