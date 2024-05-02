package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.MACD
import com.jinxservers.alphavantage.util.SeriesType
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class MACDRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    seriesType: SeriesType,
    fastPeriod: Int? = null,
    slowPeriod: Int? = null,
    signalPeriod: Int? = null
) : Request<MACD>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "MACD")
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
        }
    }
)