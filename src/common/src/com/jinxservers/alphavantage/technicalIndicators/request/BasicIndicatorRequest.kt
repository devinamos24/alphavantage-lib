package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class BasicIndicatorRequest<T>(
    function: String,
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    timePeriod: Int,
) : Request<T>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", function)
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            require(timePeriod > 0) { "Time period parameter must be a positive integer" }
            append("time_period", timePeriod.toString())
        }
    }
)