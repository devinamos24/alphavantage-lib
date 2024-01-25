package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.QuoteEndpointSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import com.jinxservers.alphavantage.util.IntegerAsStringSerializer
import com.jinxservers.alphavantage.util.PercentAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = QuoteEndpointSerializer::class)
public class QuoteEndpoint(
    public val symbol: String,
    public val open: Double,
    public val high: Double,
    public val low: Double,
    public val price: Double,
    public val volume: Int,
    public val latestTradingDay: Instant,
    public val previousClose: Double,
    public val change: Double,
    public val changePercent: Double
): Comparable<QuoteEndpoint>  {

    internal constructor(quoteEndpointSurrogate: QuoteEndpointSurrogate) : this(
        quoteEndpointSurrogate.symbol,
        quoteEndpointSurrogate.open,
        quoteEndpointSurrogate.high,
        quoteEndpointSurrogate.low,
        quoteEndpointSurrogate.price,
        quoteEndpointSurrogate.volume,
        quoteEndpointSurrogate.latestTradingDay,
        quoteEndpointSurrogate.previousClose,
        quoteEndpointSurrogate.change,
        quoteEndpointSurrogate.changePercent
    )

    override fun toString(): String {
        return "QuoteEndpoint(symbol='$symbol', open=$open, high=$high, low=$low, price=$price, volume=$volume, latestTradingDay=$latestTradingDay, previousClose=$previousClose, change=$change, changePercent=$changePercent)"
    }

    override fun compareTo(other: QuoteEndpoint): Int {
        return latestTradingDay.compareTo(other.latestTradingDay)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as QuoteEndpoint

        if (symbol != other.symbol) return false
        if (open != other.open) return false
        if (high != other.high) return false
        if (low != other.low) return false
        if (price != other.price) return false
        if (volume != other.volume) return false
        if (latestTradingDay != other.latestTradingDay) return false
        if (previousClose != other.previousClose) return false
        if (change != other.change) return false
        if (changePercent != other.changePercent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + open.hashCode()
        result = 31 * result + high.hashCode()
        result = 31 * result + low.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + volume
        result = 31 * result + latestTradingDay.hashCode()
        result = 31 * result + previousClose.hashCode()
        result = 31 * result + change.hashCode()
        result = 31 * result + changePercent.hashCode()
        return result
    }
}

@Serializable
internal class QuoteEndpointSurrogate(
    @SerialName("01. symbol")
    val symbol: String,
    @SerialName("02. open") @Serializable(with = DoubleAsStringSerializer4D::class)
    val open: Double,
    @SerialName("03. high") @Serializable(with = DoubleAsStringSerializer4D::class)
    val high: Double,
    @SerialName("04. low") @Serializable(with = DoubleAsStringSerializer4D::class)
    val low: Double,
    @SerialName("05. price") @Serializable(with = DoubleAsStringSerializer4D::class)
    val price: Double,
    @SerialName("06. volume") @Serializable(with = IntegerAsStringSerializer::class)
    val volume: Int,
    @SerialName("07. latest trading day") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    val latestTradingDay: Instant,
    @SerialName("08. previous close") @Serializable(with = DoubleAsStringSerializer4D::class)
    val previousClose: Double,
    @SerialName("09. change") @Serializable(with = DoubleAsStringSerializer4D::class)
    val change: Double,
    @SerialName("10. change percent") @Serializable(with = PercentAsStringSerializer4D::class)
    val changePercent: Double
) {
    internal constructor(quoteEndpoint: QuoteEndpoint) : this(
        quoteEndpoint.symbol,
        quoteEndpoint.open,
        quoteEndpoint.high,
        quoteEndpoint.low,
        quoteEndpoint.price,
        quoteEndpoint.volume,
        quoteEndpoint.latestTradingDay,
        quoteEndpoint.previousClose,
        quoteEndpoint.change,
        quoteEndpoint.changePercent
    )
}