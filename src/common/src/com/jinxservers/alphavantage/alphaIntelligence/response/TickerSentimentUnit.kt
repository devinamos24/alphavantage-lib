package com.jinxservers.alphavantage.alphaIntelligence.response

import com.jinxservers.alphavantage.util.DoubleAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class TickerSentimentUnit(
    public val ticker: String,
    @SerialName("relevance_score") @Serializable(with = DoubleAsStringSerializer::class)
    public val relevanceScore: Double,
    @SerialName("ticker_sentiment_score") @Serializable(with = DoubleAsStringSerializer::class)
    public val tickerSentimentScore: Double,
    @SerialName("ticker_sentiment_label")
    public val tickerSentimentLabel: String
) {
    override fun toString(): String {
        return "TickerSentimentUnit(ticker='$ticker', relevanceScore=$relevanceScore, tickerSentimentScore=$tickerSentimentScore, tickerSentimentLabel='$tickerSentimentLabel')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TickerSentimentUnit

        if (ticker != other.ticker) return false
        if (relevanceScore != other.relevanceScore) return false
        if (tickerSentimentScore != other.tickerSentimentScore) return false
        if (tickerSentimentLabel != other.tickerSentimentLabel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ticker.hashCode()
        result = 31 * result + relevanceScore.hashCode()
        result = 31 * result + tickerSentimentScore.hashCode()
        result = 31 * result + tickerSentimentLabel.hashCode()
        return result
    }

}