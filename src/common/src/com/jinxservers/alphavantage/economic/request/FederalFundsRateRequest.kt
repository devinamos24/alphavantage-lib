package com.jinxservers.alphavantage.economic.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.economic.response.EconomicObject
import com.jinxservers.alphavantage.util.LongInterval
import io.ktor.http.*

internal class FederalFundsRateRequest(
    interval: LongInterval? = null
) : Request<EconomicObject>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "FEDERAL_FUNDS_RATE")
            if (interval != null) {
                append("interval", interval.value())
            }
        }
    }
)