package com.jinxservers.alphavantage.alphaIntelligence.response

import com.jinxservers.alphavantage.util.IntegerAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class MarketNews(
    @Serializable(with = IntegerAsStringSerializer::class)
    public val items: Int,
    @SerialName("sentiment_score_definition")
    public val sentimentScoreDefinition: String,
    @SerialName("relevance_score_definition")
    public val relevanceScoreDefinition: String,
    public val feed: List<MarketNewsUnit>
) {

    override fun toString(): String {
        return "MarketNews(items=$items, sentimentScoreDefinition='$sentimentScoreDefinition', relevanceScoreDefinition='$relevanceScoreDefinition', feed=$feed)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MarketNews

        if (items != other.items) return false
        if (sentimentScoreDefinition != other.sentimentScoreDefinition) return false
        if (relevanceScoreDefinition != other.relevanceScoreDefinition) return false
        if (feed != other.feed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = items
        result = 31 * result + sentimentScoreDefinition.hashCode()
        result = 31 * result + relevanceScoreDefinition.hashCode()
        result = 31 * result + feed.hashCode()
        return result
    }

}