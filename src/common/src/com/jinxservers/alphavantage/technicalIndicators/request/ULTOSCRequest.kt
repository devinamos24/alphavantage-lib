package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.ULTOSC
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class ULTOSCRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    timePeriod1: Int? = null,
    timePeriod2: Int? = null,
    timePeriod3: Int? = null
) : Request<ULTOSC>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "ULTOSC")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            timePeriod1?.let {
                require(it > 0) { "Time period 1 parameter must be a positive integer" }
                append("timeperiod1", it.toString())
            }
            timePeriod2?.let {
                require(it > 0) { "Time period 2 parameter must be a positive integer" }
                append("timeperiod2", it.toString())
            }
            timePeriod3?.let {
                require(it > 0) { "Time period 3 parameter must be a positive integer" }
                append("timeperiod3", it.toString())
            }
        }
    }
)