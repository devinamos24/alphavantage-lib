package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.core.response.*
import com.jinxservers.alphavantage.util.OutputSize
import com.jinxservers.alphavantage.util.ShortInterval

public class CoreRequest internal constructor(
    private val alphaVantage: AlphaVantage
) {

    public suspend fun getIntraday(symbol: String, interval: ShortInterval, adjusted: Boolean? = null, extendedHours: Boolean? = null, month: String? = null, outputSize: OutputSize? = null): CoreIntraday {
        return alphaVantage.request(IntradayRequest(symbol, interval, adjusted, extendedHours, month, outputSize))
    }

    public suspend fun getDaily(symbol: String, outputSize: OutputSize? = null): CoreDaily {
        return alphaVantage.request(DailyRequest(symbol, outputSize))
    }

    public suspend fun getDailyAdjusted(symbol: String, outputSize: OutputSize? = null): CoreDailyAdjusted {
        return alphaVantage.request(DailyAdjustedRequest(symbol, outputSize))
    }

    public suspend fun getWeekly(symbol: String): CoreWeekly {
        return alphaVantage.request(WeeklyRequest(symbol))
    }

    public suspend fun getWeeklyAdjusted(symbol: String): CoreWeeklyAdjusted {
        return alphaVantage.request(WeeklyAdjustedRequest(symbol))
    }

    public suspend fun getMonthly(symbol: String): CoreMonthly {
        return alphaVantage.request(MonthlyRequest(symbol))
    }

    public suspend fun getMonthlyAdjusted(symbol: String): CoreMonthlyAdjusted {
        return alphaVantage.request(MonthlyAdjustedRequest(symbol))
    }

    public suspend fun getQuoteEndpoint(symbol: String): QuoteEndpoint {
        return alphaVantage.request(QuoteEndpointRequest(symbol))
    }

    public suspend fun getTickerSearch(keywords: String): List<TickerSearchUnit> {
        return alphaVantage.request(TickerSearchRequest(keywords))
    }

    public suspend fun getGlobalMarketStatus(): GlobalMarketStatus {
        return alphaVantage.request(GlobalMarketStatusRequest())
    }
}