package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.AlphaVantageDemo
import com.jinxservers.alphavantage.forex.response.*
import com.jinxservers.alphavantage.util.OutputSize
import com.jinxservers.alphavantage.util.ShortInterval

public class DemoForexRequest internal constructor(
    private val alphaVantage: AlphaVantageDemo
) {

    public suspend fun getCurrencyExchangeRateUSDToJPY() : CurrencyExchangeRate {
        return alphaVantage.request(CurrencyExchangeRateRequest("USD", "JPY"))
    }

    public suspend fun getCurrencyExchangeRateBTCToCNY() : CurrencyExchangeRate {
        return alphaVantage.request(CurrencyExchangeRateRequest("BTC", "CNY"))
    }

    public suspend fun getIntradayEURToUSDCompact(): ForexIntraday {
        return alphaVantage.request(IntradayRequest("EUR", "USD", ShortInterval.FIVE_MINUTES))
    }

    public suspend fun getIntradayEURToUSDFull(): ForexIntraday {
        return alphaVantage.request(IntradayRequest("EUR", "USD", ShortInterval.FIVE_MINUTES, OutputSize.FULL))
    }

    public suspend fun getDailyEURToUSDCompact(): ForexDaily {
        return alphaVantage.request(DailyRequest("EUR", "USD"))
    }

    public suspend fun getDailyEURToUSDFull(): ForexDaily {
        return alphaVantage.request(DailyRequest("EUR", "USD", OutputSize.FULL))
    }

    public suspend fun getWeeklyEURToUSD(): ForexWeekly {
        return alphaVantage.request(WeeklyRequest("EUR", "USD"))
    }

    public suspend fun getMonthlyEURToUSD(): ForexMonthly {
        return alphaVantage.request(MonthlyRequest("EUR", "USD"))
    }
}