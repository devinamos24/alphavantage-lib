package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.MAMA
import com.jinxservers.alphavantage.util.SeriesType
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class MAMARequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    seriesType: SeriesType,
    fastLimit: Double? = null,
    slowLimit: Double? = null
) : Request<MAMA>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "MAMA")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            append("series_type", seriesType.name.lowercase())
            fastLimit?.let {
                require(it > 0) { "Fast limit parameter must be a positive float" }
                append("fastlimit", it.toString())
            }
            slowLimit?.let {
                require(it > 0) { "Slow limit parameter must be a positive float" }
                append("slowlimit", it.toString())
            }
        }
    }
)