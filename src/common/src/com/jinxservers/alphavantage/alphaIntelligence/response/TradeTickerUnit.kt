package com.jinxservers.alphavantage.alphaIntelligence.response

import com.jinxservers.alphavantage.util.DoubleAsStringSerializer
import com.jinxservers.alphavantage.util.IntegerAsStringSerializer
import com.jinxservers.alphavantage.util.PercentAsStringSerializer4DMax
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class TradeTickerUnit(
    public val ticker: String,
    @Serializable(with = DoubleAsStringSerializer::class)
    public val price: Double,
    @SerialName("change_amount") @Serializable(with = DoubleAsStringSerializer::class)
    public val changeAmount: Double,
    @SerialName("change_percentage") @Serializable(with = PercentAsStringSerializer4DMax::class)
    public val changePercentage: Double,
    @Serializable(with = IntegerAsStringSerializer::class)
    public val volume: Int,
) {

    override fun toString(): String {
        return "TradeTickerUnit(ticker='$ticker', price=$price, changeAmount=$changeAmount, changePercentage=$changePercentage, volume=$volume)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TradeTickerUnit

        if (ticker != other.ticker) return false
        if (price != other.price) return false
        if (changeAmount != other.changeAmount) return false
        if (changePercentage != other.changePercentage) return false
        if (volume != other.volume) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ticker.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + changeAmount.hashCode()
        result = 31 * result + changePercentage.hashCode()
        result = 31 * result + volume
        return result
    }

}