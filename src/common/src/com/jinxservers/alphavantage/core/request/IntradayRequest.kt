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
    adjusted: Boolean = true,
    extendedHours: Boolean = true,
    month: String = "",
    outputSize: OutputSize = OutputSize.COMPACT,
) : Request<CoreIntraday>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_INTRADAY")
            append("symbol", symbol)
            append("interval", interval.value())
            append("adjusted", adjusted.toString())
            append("extended_hours", extendedHours.toString())
            if (month.isNotBlank()) {
                append("month", month)
            }
            append("outputsize", outputSize.size)
        }
    }
)