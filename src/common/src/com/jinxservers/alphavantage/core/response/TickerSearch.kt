package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class TickerSearchUnit(
    @SerialName("1. symbol")
    public val symbol: String,
    @SerialName("2. name")
    public val name: String,
    @SerialName("3. type")
    public val type: String,
    @SerialName("4. region")
    public val region: String,
    @SerialName("5. marketOpen")
    public val marketOpenTime: LocalTime,
    @SerialName("6. marketClose")
    public val marketCloseTime: LocalTime,
    @SerialName("7. timezone")
    public val timezone: String,
    @SerialName("8. currency")
    public val currency: String,
    @SerialName("9. matchScore") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val matchScore: Double
) {

    override fun toString(): String {
        return "TickerSearchUnit(symbol='$symbol', name='$name', type='$type', region='$region', marketOpenTime=$marketOpenTime, marketCloseTime=$marketCloseTime, timezone='$timezone', currency='$currency', matchScore=$matchScore)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TickerSearchUnit

        if (symbol != other.symbol) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (region != other.region) return false
        if (marketOpenTime != other.marketOpenTime) return false
        if (marketCloseTime != other.marketCloseTime) return false
        if (timezone != other.timezone) return false
        if (currency != other.currency) return false
        if (matchScore != other.matchScore) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + region.hashCode()
        result = 31 * result + marketOpenTime.hashCode()
        result = 31 * result + marketCloseTime.hashCode()
        result = 31 * result + timezone.hashCode()
        result = 31 * result + currency.hashCode()
        result = 31 * result + matchScore.hashCode()
        return result
    }
}