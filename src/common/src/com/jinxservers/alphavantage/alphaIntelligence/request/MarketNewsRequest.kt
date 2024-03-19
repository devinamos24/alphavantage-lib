package com.jinxservers.alphavantage.alphaIntelligence.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.alphaIntelligence.response.MarketNews
import com.jinxservers.alphavantage.util.Sort
import com.jinxservers.alphavantage.util.Topic
import io.ktor.http.*

internal class MarketNewsRequest(
    tickers: List<String> = emptyList(),
    topics: List<Topic> = emptyList(),
    timeFrom: String = "",
    timeTo: String = "",
    sort: Sort = Sort.LATEST,
    limit: Int = 50
    ) : Request<MarketNews>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "NEWS_SENTIMENT")
            if (tickers.isNotEmpty()) {
                append("tickers", tickers.joinToString(","))
            }
            if (topics.isNotEmpty()) {
                append("topics", topics.joinToString(",") { it.value })
            }
            if (timeFrom.isNotBlank()) {
                append("time_from", timeFrom)
            }
            if (timeTo.isNotBlank()) {
                append("time_to", timeTo)
            }
            append("sort", sort.name)
            append("limit", limit.toString())
        }
    }
)