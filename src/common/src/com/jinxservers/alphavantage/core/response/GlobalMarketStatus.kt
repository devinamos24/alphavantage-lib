package com.jinxservers.alphavantage.core.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class GlobalMarketStatus(
    @SerialName("endpoint")
    public val endpoint: String,
    @SerialName("markets")
    public val markets: List<MarketUnit>
) {

    override fun toString(): String {
        return "GlobalMarketStatus(endpoint='$endpoint', markets=$markets)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as GlobalMarketStatus

        if (endpoint != other.endpoint) return false
        if (markets != other.markets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = endpoint.hashCode()
        result = 31 * result + markets.hashCode()
        return result
    }
}