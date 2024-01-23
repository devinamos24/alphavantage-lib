package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.forex.response.ForexWeekly
import io.ktor.http.*

internal class WeeklyRequest(
    fromSymbol: String,
    toSymbol: String,
) : Request<ForexWeekly>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "FX_WEEKLY")
            append("from_symbol", fromSymbol)
            append("to_symbol", toSymbol)
        }
    }
)