package com.jinxservers.alphavantage.economic.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.economic.response.EconomicObject
import com.jinxservers.alphavantage.util.CpiInterval
import io.ktor.http.*

internal class CPIRequest(
    interval: CpiInterval?
) : Request<EconomicObject>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "CPI")
            if (interval != null) {
                append("interval", interval.value())
            }
        }
    }
)