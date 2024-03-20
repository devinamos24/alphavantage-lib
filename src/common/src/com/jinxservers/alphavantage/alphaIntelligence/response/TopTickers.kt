package com.jinxservers.alphavantage.alphaIntelligence.response

import com.jinxservers.alphavantage.alphaIntelligence.TopTickersInstantAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class TopTickers(
    public val metadata: String,
    @SerialName("last_updated") @Serializable(with = TopTickersInstantAsStringSerializer::class)
    public val lastUpdated: Instant,
    @SerialName("top_gainers")
    public val topGainers: List<TradeTickerUnit>,
    @SerialName("top_losers")
    public val topLosers: List<TradeTickerUnit>,
    @SerialName("most_actively_traded")
    public val mostActivelyTraded: List<TradeTickerUnit>
) {
    override fun toString(): String {
        return "TopTickers(metadata='$metadata', lastUpdated=$lastUpdated, topGainers=$topGainers, topLosers=$topLosers, mostActivelyTraded=$mostActivelyTraded)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TopTickers

        if (metadata != other.metadata) return false
        if (lastUpdated != other.lastUpdated) return false
        if (topGainers != other.topGainers) return false
        if (topLosers != other.topLosers) return false
        if (mostActivelyTraded != other.mostActivelyTraded) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metadata.hashCode()
        result = 31 * result + lastUpdated.hashCode()
        result = 31 * result + topGainers.hashCode()
        result = 31 * result + topLosers.hashCode()
        result = 31 * result + mostActivelyTraded.hashCode()
        return result
    }

}