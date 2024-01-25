package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.response.GlobalMarketStatus
import io.ktor.http.*

internal class GlobalMarketStatusRequest : Request<GlobalMarketStatus>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "MARKET_STATUS")
        }
    }
)