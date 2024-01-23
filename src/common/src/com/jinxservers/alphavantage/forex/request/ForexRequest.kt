package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.forex.response.*
import com.jinxservers.alphavantage.util.OutputSize
import com.jinxservers.alphavantage.util.ShortInterval

public class ForexRequest internal constructor(
    private val alphaVantage: AlphaVantage
) {

    public suspend fun getCurrencyExchangeRate(fromCurrency: String, toCurrency: String): CurrencyExchangeRate {
        return alphaVantage.request(CurrencyExchangeRateRequest(fromCurrency, toCurrency))
    }

    public suspend fun getIntraday(fromSymbol: String, toSymbol: String, interval: ShortInterval, outputSize: OutputSize = OutputSize.COMPACT): ForexIntraday {
        return alphaVantage.request(IntradayRequest(fromSymbol, toSymbol, interval, outputSize))
    }

    public suspend fun getDaily(fromSymbol: String, toSymbol: String, outputSize: OutputSize = OutputSize.COMPACT): ForexDaily {
        return alphaVantage.request(DailyRequest(fromSymbol, toSymbol, outputSize))
    }

    public suspend fun getWeekly(fromSymbol: String, toSymbol: String): ForexWeekly {
        return alphaVantage.request(WeeklyRequest(fromSymbol, toSymbol))
    }

    public suspend fun getMonthly(fromSymbol: String, toSymbol: String): ForexMonthly {
        return alphaVantage.request(MonthlyRequest(fromSymbol, toSymbol))
    }
}

