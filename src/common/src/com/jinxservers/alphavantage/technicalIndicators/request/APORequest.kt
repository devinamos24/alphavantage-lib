package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.APO
import com.jinxservers.alphavantage.util.MAType
import com.jinxservers.alphavantage.util.SeriesType
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class APORequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    seriesType: SeriesType,
    fastPeriod: Int? = null,
    slowPeriod: Int? = null,
    maType: MAType? = null,
) : Request<APO>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "APO")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            append("series_type", seriesType.name)
            fastPeriod?.let {
                require(it > 0) { "Fast period parameter must be a positive integer" }
                append("fastperiod", it.toString())
            }
            slowPeriod?.let {
                require(it > 0) { "Slow period parameter must be a positive integer" }
                append("slowperiod", it.toString())
            }
            maType?.let { append("matype", it.value) }
        }
    }
)