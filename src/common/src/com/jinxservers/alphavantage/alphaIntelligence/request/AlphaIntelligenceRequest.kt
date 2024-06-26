package com.jinxservers.alphavantage.alphaIntelligence.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.alphaIntelligence.response.MarketNews
import com.jinxservers.alphavantage.alphaIntelligence.response.TopTickers
import com.jinxservers.alphavantage.util.Sort
import com.jinxservers.alphavantage.util.Topic

public class AlphaIntelligenceRequest(
    private val alphaVantage: AlphaVantage
) {

    public suspend fun getMarketNews(tickers: List<String>? = null, topics: List<Topic>? = null, timeFrom: String? = null, timeTo: String? = null, sort: Sort? = null, limit: Int? = null): MarketNews {
        return alphaVantage.request(MarketNewsRequest(tickers, topics, timeFrom, timeTo, sort, limit))
    }

    public suspend fun getTopTickers(): TopTickers {
        return alphaVantage.request(TopTickersRequest())
    }

}