package com.jinxservers.alphavantage.commodity.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.commodity.response.CommodityObject
import com.jinxservers.alphavantage.forex.request.CurrencyExchangeRateRequest
import com.jinxservers.alphavantage.forex.response.CurrencyExchangeRate
import com.jinxservers.alphavantage.util.CommodityInterval
import com.jinxservers.alphavantage.util.LongInterval
import com.jinxservers.alphavantage.util.ShortInterval
import io.ktor.http.*



public class CommodityRequest internal constructor(
    private val alphaVantage: AlphaVantage
) {

    public suspend fun getCrudeOilWTI(interval: LongInterval = LongInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("WTI", interval))
    }

    public suspend fun getCrudeOilBrent(interval: LongInterval = LongInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("BRENT", interval))
    }

    public suspend fun getNaturalGas(interval: LongInterval = LongInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("NATURAL_GAS", interval))
    }

    public suspend fun getCopper(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("COPPER", interval))
    }

    public suspend fun getAluminum(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("ALUMINUM", interval))
    }

    public suspend fun getWheat(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("WHEAT", interval))
    }

    public suspend fun getCorn(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("CORN", interval))
    }

    public suspend fun getCotton(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("COTTON", interval))
    }

    public suspend fun getSugar(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("SUGAR", interval))
    }

    public suspend fun getCoffee(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("COFFEE", interval))
    }

    public suspend fun getAll(interval: CommodityInterval = CommodityInterval.MONTHLY): CommodityObject {
        return alphaVantage.request(CommodityObjectRequest("ALL_COMMODITIES", interval))
    }
}