package com.jinxservers.alphavantage.economic.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.economic.response.EconomicObject
import com.jinxservers.alphavantage.util.LongInterval
import com.jinxservers.alphavantage.util.Maturity
import io.ktor.http.*

internal class TreasuryYieldRequest(
    interval: LongInterval = LongInterval.MONTHLY,
    maturity: Maturity = Maturity.TEN_YEAR
) : Request<EconomicObject>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TREASURY_YIELD")
            append("interval", interval.value())
            append("maturity", maturity.value)
        }
    }
)