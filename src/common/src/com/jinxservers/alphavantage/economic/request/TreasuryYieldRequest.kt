package com.jinxservers.alphavantage.economic.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.economic.response.EconomicObject
import com.jinxservers.alphavantage.util.LongInterval
import com.jinxservers.alphavantage.util.Maturity
import io.ktor.http.*

internal class TreasuryYieldRequest(
    interval: LongInterval?,
    maturity: Maturity?
) : Request<EconomicObject>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "TREASURY_YIELD")
            if (interval != null) {
                append("interval", interval.value())
            }
            if (maturity != null) {
                append("maturity", maturity.value)
            }
        }
    }
)