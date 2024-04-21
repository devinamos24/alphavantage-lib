package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.CoreDaily
import com.jinxservers.alphavantage.util.OutputSize
import io.ktor.http.*

internal class DailyRequest(
    symbol: String,
    outputSize: OutputSize? = null,
): Request<CoreDaily>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TIME_SERIES_DAILY")
            append("symbol", symbol)
            if (outputSize != null) {
                append("outputsize", outputSize.size)
            }
        }
    }
)