package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.ADOSC
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class ADOSCRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    fastPeriod: Int? = null,
    slowPeriod: Int? = null,
) : Request<ADOSC>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "ADOSC")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            fastPeriod?.let {
                require(it > 0) { "Fast period parameter must be a positive integer" }
                append("fastperiod", it.toString())
            }
            slowPeriod?.let {
                require(it > 0) { "Slow period parameter must be a positive integer" }
                append("slowperiod", it.toString())
            }
        }
    }
)