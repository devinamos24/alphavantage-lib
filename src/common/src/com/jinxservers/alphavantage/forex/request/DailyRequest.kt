package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.forex.response.ForexDaily
import com.jinxservers.alphavantage.util.OutputSize
import io.ktor.http.*

internal class DailyRequest(
    fromSymbol: String,
    toSymbol: String,
    outputSize: OutputSize?
) : Request<ForexDaily>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "FX_DAILY")
            append("from_symbol", fromSymbol)
            append("to_symbol", toSymbol)

            if (outputSize != null) {
                append("outputsize", outputSize.size)
            }
        }
    }
)