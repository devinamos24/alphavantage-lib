package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.technicalIndicators.response.BBANDS
import com.jinxservers.alphavantage.util.MAType
import com.jinxservers.alphavantage.util.SeriesType
import com.jinxservers.alphavantage.util.ShortLongInterval
import io.ktor.http.*


internal class BBANDSRequest(
    symbol: String,
    interval: ShortLongInterval,
    month: String? = null,
    timePeriod: Int,
    seriesType: SeriesType,
    nbDevUp: Int? = null,
    nbDevDn: Int? = null,
    maType: MAType? = null
) : Request<BBANDS>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "BBANDS")
            append("symbol", symbol)
            append("interval", interval.value())
            month?.let {
                require(interval.value() in monthCompatibleIntervals) { "Month parameter requires interval to be in: $monthCompatibleIntervals" }
                append("month", it)
            }
            require(timePeriod > 0) { "Time period parameter must be a positive integer" }
            append("time_period", timePeriod.toString())
            append("series_type", seriesType.name.lowercase())
            nbDevUp?.let {
                require(it > 0) { "nbDevUp parameter must be a positive integer" }
                append("nbdevup", it.toString())
            }
            nbDevDn?.let {
                require(it > 0) { "nbDevDn parameter must be a positive integer" }
                append("nbdevdn", it.toString())
            }
            maType?.let { append("matype", it.value) }
        }
    }
)