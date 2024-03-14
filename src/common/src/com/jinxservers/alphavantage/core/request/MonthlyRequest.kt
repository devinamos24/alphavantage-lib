package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.CoreMonthly
import io.ktor.http.*

internal class MonthlyRequest(
    symbol: String,
): Request<CoreMonthly>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_MONTHLY")
            append("symbol", symbol)
        }
    }
)