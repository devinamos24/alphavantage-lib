package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.QuoteEndpoint
import io.ktor.http.*

internal class QuoteEndpointRequest(
    symbol: String,
): Request<QuoteEndpoint>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "GLOBAL_QUOTE")
            append("symbol", symbol)
        }
    }
)