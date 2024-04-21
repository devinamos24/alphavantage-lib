package com.jinxservers.alphavantage.economic.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.economic.response.EconomicObject
import com.jinxservers.alphavantage.util.GdpInterval
import io.ktor.http.*


internal class RealGDPRequest(
    interval: GdpInterval? = null
) : Request<EconomicObject>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "REAL_GDP")
            if (interval != null) {
                append("interval", interval.value())
            }
        }
    }
)