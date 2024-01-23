package com.jinxservers.alphavantage.economic.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.economic.response.EconomicObject
import io.ktor.http.*

internal class EconomicObjectRequest(
    function: String,
) : Request<EconomicObject>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", function)
        }
    }
)