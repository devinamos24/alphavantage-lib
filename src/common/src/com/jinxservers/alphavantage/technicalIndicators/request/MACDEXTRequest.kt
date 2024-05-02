package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.MACDEXT
import com.jinxservers.alphavantage.util.MAType
import com.jinxservers.alphavantage.util.SeriesType
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class MACDEXTRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    seriesType: SeriesType,
    fastPeriod: Int? = null,
    slowPeriod: Int? = null,
    signalPeriod: Int? = null,
    fastMAType: MAType? = null,
    slowMAType: MAType? = null,
    signalMAType: MAType? = null
) : Request<MACDEXT>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "MACDEXT")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            append("series_type", seriesType.name.lowercase())
            fastPeriod?.let {
                require(it > 0) { "Fast period parameter must be a positive integer" }
                append("fastperiod", it.toString())
            }
            slowPeriod?.let {
                require(it > 0) { "Slow period parameter must be a positive integer" }
                append("slowperiod", it.toString())
            }
            signalPeriod?.let {
                require(it > 0) { "Signal period parameter must be a positive integer" }
                append("signalperiod", it.toString())
            }
            fastMAType?.let { append("fastmatype", it.value) }
            slowMAType?.let { append("slowmatype", it.value) }
            signalMAType?.let { append("signalmatype", it.value) }
        }
    }
)