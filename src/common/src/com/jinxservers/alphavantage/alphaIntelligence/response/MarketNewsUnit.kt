package com.jinxservers.alphavantage.alphaIntelligence.response

import com.jinxservers.alphavantage.alphaIntelligence.MarketNewsUnitInstantAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class MarketNewsUnit(
    public val title: String,
    public val url: String,
    @SerialName("time_published") @Serializable(with = MarketNewsUnitInstantAsStringSerializer::class)
    public val timePublished: Instant,
    public val authors: List<String>,
    public val summary: String,
    @SerialName("banner_image")
    public val bannerImage: String,
    public val source: String,
    @SerialName("category_within_source")
    public val categoryWithinSource: String,
    @SerialName("source_domain")
    public val sourceDomain: String,
    public val topics: List<TopicUnit>,
    @SerialName("overall_sentiment_score")
    public val overallSentimentScore: Double,
    @SerialName("overall_sentiment_label")
    public val overallSentimentLabel: String,
    @SerialName("ticker_sentiment")
    public val tickerSentiment: List<TickerSentimentUnit>
) {
    override fun toString(): String {
        return "MarketNewsUnit(title='$title', url='$url', timePublished=$timePublished, authors=$authors, summary='$summary', bannerImage='$bannerImage', source='$source', categoryWithinSource='$categoryWithinSource', sourceDomain='$sourceDomain', topics=$topics, overallSentimentScore=$overallSentimentScore, overallSentimentLabel='$overallSentimentLabel', tickerSentiment=$tickerSentiment)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MarketNewsUnit

        if (title != other.title) return false
        if (url != other.url) return false
        if (timePublished != other.timePublished) return false
        if (authors != other.authors) return false
        if (summary != other.summary) return false
        if (bannerImage != other.bannerImage) return false
        if (source != other.source) return false
        if (categoryWithinSource != other.categoryWithinSource) return false
        if (sourceDomain != other.sourceDomain) return false
        if (topics != other.topics) return false
        if (overallSentimentScore != other.overallSentimentScore) return false
        if (overallSentimentLabel != other.overallSentimentLabel) return false
        if (tickerSentiment != other.tickerSentiment) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + timePublished.hashCode()
        result = 31 * result + authors.hashCode()
        result = 31 * result + summary.hashCode()
        result = 31 * result + bannerImage.hashCode()
        result = 31 * result + source.hashCode()
        result = 31 * result + categoryWithinSource.hashCode()
        result = 31 * result + sourceDomain.hashCode()
        result = 31 * result + topics.hashCode()
        result = 31 * result + overallSentimentScore.hashCode()
        result = 31 * result + overallSentimentLabel.hashCode()
        result = 31 * result + tickerSentiment.hashCode()
        return result
    }

}