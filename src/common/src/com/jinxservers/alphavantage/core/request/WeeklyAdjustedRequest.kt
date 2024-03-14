package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.CoreWeeklyAdjusted
import io.ktor.http.*

internal class WeeklyAdjustedRequest(
    symbol: String,
): Request<CoreWeeklyAdjusted>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_WEEKLY_ADJUSTED")
            append("symbol", symbol)
        }
    }
)