package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.STOCH
import com.jinxservers.alphavantage.util.MAType
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class STOCHRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    fastKPeriod: Int? = null,
    slowKPeriod: Int? = null,
    slowDPeriod: Int? = null,
    slowKMAType: MAType? = null,
    slowDMAType: MAType? = null,
) : Request<STOCH>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "STOCH")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            fastKPeriod?.let {
                require(it > 0) { "Fastk period parameter must be a positive integer" }
                append("fastkperiod", it.toString())
            }
            slowKPeriod?.let {
                require(it > 0) { "Slowk period parameter must be a positive integer" }
                append("slowkperiod", it.toString())
            }
            slowDPeriod?.let {
                require(it > 0) { "Slowd period parameter must be a positive integer" }
                append("slowdperiod", it.toString())
            }
            slowKMAType?.let { append("slowkmatype", it.value) }
            slowDMAType?.let { append("slowdmatype", it.value) }
        }
    }
)