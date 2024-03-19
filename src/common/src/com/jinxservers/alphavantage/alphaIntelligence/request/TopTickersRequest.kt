package com.jinxservers.alphavantage.alphaIntelligence.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.alphaIntelligence.response.TopTickers
import io.ktor.http.*

internal class TopTickersRequest : Request<TopTickers> (
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TOP_GAINERS_LOSERS")
        }
    }
)