package com.jinxservers.alphavantage

import io.ktor.http.*

internal abstract class Request <T> (
    val urlBuilder: URLBuilder,
)