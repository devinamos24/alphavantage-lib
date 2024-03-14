package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.CoreWeekly
import io.ktor.http.*

internal class WeeklyRequest(
    symbol: String,
): Request<CoreWeekly>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_WEEKLY")
            append("symbol", symbol)
        }
    }
)