package com.jinxservers.alphavantage.core.request

import com.jinxservers.alphavantage.Request
import com.jinxservers.alphavantage.core.TickerSearchUnitListSerializer
import com.jinxservers.alphavantage.core.response.TickerSearchUnit
import io.ktor.http.*
import kotlinx.serialization.Serializable


internal class TickerSearchRequest(
    keywords: String,
): Request<@Serializable(with = TickerSearchUnitListSerializer::class)List<TickerSearchUnit>>(
    urlBuilder = URLBuilder().apply {
        parameters.apply {
            append("function", "SYMBOL_SEARCH")
            append("keywords", keywords)
        }
    }
)