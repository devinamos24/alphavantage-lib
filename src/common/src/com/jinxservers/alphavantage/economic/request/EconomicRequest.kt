package com.jinxservers.alphavantage.economic.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.economic.response.EconomicObject
import com.jinxservers.alphavantage.util.CpiInterval
import com.jinxservers.alphavantage.util.GdpInterval
import com.jinxservers.alphavantage.util.LongInterval
import com.jinxservers.alphavantage.util.Maturity

public class EconomicRequest internal constructor(
    private val alphaVantage: AlphaVantage
) {

    public suspend fun getRealGdp(interval: GdpInterval = GdpInterval.ANNUAL): EconomicObject {
        return alphaVantage.request(RealGDPRequest(interval))
    }

    public suspend fun getRealGDPPerCapita(): EconomicObject {
        return alphaVantage.request(EconomicObjectRequest("REAL_GDP_PER_CAPITA"))
    }

    public suspend fun getTreasuryYield(interval: LongInterval = LongInterval.MONTHLY, maturity: Maturity = Maturity.TEN_YEAR): EconomicObject {
        return alphaVantage.request(TreasuryYieldRequest(interval, maturity))
    }

    public suspend fun getFederalFundsRate(interval: LongInterval = LongInterval.MONTHLY): EconomicObject {
        return alphaVantage.request(FederalFundsRateRequest(interval))
    }

    public suspend fun getCPI(interval: CpiInterval = CpiInterval.MONTHLY): EconomicObject {
        return alphaVantage.request(CPIRequest(interval))
    }

    public suspend fun getInflation(): EconomicObject {
        return alphaVantage.request(EconomicObjectRequest("INFLATION"))
    }

    public suspend fun getRetailSales(): EconomicObject {
        return alphaVantage.request(EconomicObjectRequest("RETAIL_SALES"))
    }

    public suspend fun getDurables(): EconomicObject {
        return alphaVantage.request(EconomicObjectRequest("DURABLES"))
    }

    public suspend fun getUnemploymentRate(): EconomicObject {
        return alphaVantage.request(EconomicObjectRequest("UNEMPLOYMENT"))
    }

    public suspend fun getNonfarmPayroll(): EconomicObject {
        return alphaVantage.request(EconomicObjectRequest("NONFARM_PAYROLL"))
    }

}