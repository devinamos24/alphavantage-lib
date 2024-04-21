package com.jinxservers.alphavantage.alphaIntelligence.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.alphaIntelligence.response.MarketNews
import com.jinxservers.alphavantage.util.Sort
import com.jinxservers.alphavantage.util.Topic
import io.ktor.http.*

internal class MarketNewsRequest(
    tickers: List<String>? = null,
    topics: List<Topic>? = null,
    timeFrom: String? = null,
    timeTo: String? = null,
    sort: Sort? = null,
    limit: Int?
    ) : Request<MarketNews>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "NEWS_SENTIMENT")
            if (tickers != null) {
                append("tickers", tickers.joinToString(","))
            }
            if (topics != null) {
                append("topics", topics.joinToString(",") { it.value })
            }
            if (timeFrom != null) {
                append("time_from", timeFrom)
            }
            if (timeTo != null) {
                append("time_to", timeTo)
            }
            if (sort != null) {
                append("sort", sort.name)
            }
            if (limit != null) {
                append("limit", limit.toString())
            }
        }
    }
)