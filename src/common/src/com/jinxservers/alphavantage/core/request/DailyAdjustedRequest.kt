package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.CoreDailyAdjusted
import com.jinxservers.alphavantage.util.OutputSize
import io.ktor.http.*

internal class DailyAdjustedRequest(
    symbol: String,
    outputSize: OutputSize?,
): Request<CoreDailyAdjusted>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_DAILY_ADJUSTED")
            append("symbol", symbol)
            if (outputSize != null) {
                append("outputsize", outputSize.size)
            }
        }
    }
)