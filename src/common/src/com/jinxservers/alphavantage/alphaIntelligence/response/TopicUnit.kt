package com.jinxservers.alphavantage.alphaIntelligence.response

import com.jinxservers.alphavantage.util.DoubleAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class TopicUnit(
    public val topic: String,
    @SerialName("relevance_score") @Serializable(with = DoubleAsStringSerializer::class)
    public val relevanceScore: Double
) {
    override fun toString(): String {
        return "TopicUnit(topic='$topic', relevanceScore=$relevanceScore)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TopicUnit

        if (topic != other.topic) return false
        if (relevanceScore != other.relevanceScore) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topic.hashCode()
        result = 31 * result + relevanceScore.hashCode()
        return result
    }

}