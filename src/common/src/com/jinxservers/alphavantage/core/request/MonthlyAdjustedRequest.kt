package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.CoreMonthlyAdjusted
import io.ktor.http.*

internal class MonthlyAdjustedRequest(
    symbol: String,
): Request<CoreMonthlyAdjusted>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_MONTHLY_ADJUSTED")
            append("symbol", symbol)
        }
    }
)