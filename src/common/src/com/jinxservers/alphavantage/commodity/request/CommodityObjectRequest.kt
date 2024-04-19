package com.jinxservers.alphavantage.commodity.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.commodity.response.CommodityObject
import com.jinxservers.alphavantage.util.Interval
import io.ktor.http.*

internal class CommodityObjectRequest(
    function: String,
    interval: Interval?,
) : Request<CommodityObject>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", function)
            if (interval != null) {
                append("interval", interval.value())
            }
        }
    }
)