package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.SAR
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class SARRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    acceleration: Double? = null,
    maximum: Double? = null
) : Request<SAR>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "SAR")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            acceleration?.let {
                require(it > 0) { "Acceleration parameter must be a positive Float" }
                append("acceleration", it.toString())
            }
            maximum?.let {
                require(it > 0) { "Maximum parameter must be a positive Float" }
                append("maximum", it.toString())
            }
        }
    }
)