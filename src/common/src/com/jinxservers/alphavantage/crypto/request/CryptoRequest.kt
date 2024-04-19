package com.jinxservers.alphavantage.crypto.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.crypto.response.CryptoDaily
import com.jinxservers.alphavantage.crypto.response.CryptoIntraday
import com.jinxservers.alphavantage.crypto.response.CryptoMonthly
import com.jinxservers.alphavantage.crypto.response.CryptoWeekly
import com.jinxservers.alphavantage.forex.request.CurrencyExchangeRateRequest
import com.jinxservers.alphavantage.forex.response.CurrencyExchangeRate
import com.jinxservers.alphavantage.util.ShortInterval

public class CryptoRequest internal constructor(
    private val alphaVantage: AlphaVantage
) {

    public suspend fun getCurrencyExchangeRate(fromCurrency: String, toCurrency: String): CurrencyExchangeRate {
        return alphaVantage.request(CurrencyExchangeRateRequest(fromCurrency, toCurrency))
    }

    public suspend fun getIntraday(fromSymbol: String, toSymbol: String, interval: ShortInterval, outputSize: String? = null): CryptoIntraday {
        return alphaVantage.request(IntradayRequest(fromSymbol, toSymbol, interval, outputSize))
    }

    public suspend fun getDaily(fromSymbol: String, toSymbol: String): CryptoDaily {
        return alphaVantage.request(DailyRequest(fromSymbol, toSymbol))
    }

    public suspend fun getWeekly(fromSymbol: String, toSymbol: String): CryptoWeekly {
        return alphaVantage.request(WeeklyRequest(fromSymbol, toSymbol))
    }

    public suspend fun getMonthly(fromSymbol: String, toSymbol: String): CryptoMonthly {
        return alphaVantage.request(MonthlyRequest(fromSymbol, toSymbol))
    }
}