package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.STOCHF
import com.jinxservers.alphavantage.util.MAType
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class STOCHFRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    fastKPeriod: Int? = null,
    fastDPeriod: Int? = null,
    fastDMAType: MAType? = null,
) : Request<STOCHF>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "STOCHF")
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
            fastDPeriod?.let {
                require(it > 0) { "Fastd period parameter must be a positive integer" }
                append("fastdperiod", it.toString())
            }
            fastDMAType?.let { append("fastdmatype", it.value) }
        }
    }
)