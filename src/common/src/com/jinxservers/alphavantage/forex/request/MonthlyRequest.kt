package com.jinxservers.alphavantage.forex.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.forex.response.ForexMonthly
import com.jinxservers.alphavantage.forex.response.ForexWeekly
import io.ktor.http.*

internal class MonthlyRequest(
    fromSymbol: String,
    toSymbol: String,
) : Request<ForexMonthly>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "FX_MONTHLY")
            append("from_symbol", fromSymbol)
            append("to_symbol", toSymbol)
        }
    }
)