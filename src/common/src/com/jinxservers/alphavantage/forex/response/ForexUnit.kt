package com.jinxservers.alphavantage.forex.response

import com.jinxservers.alphavantage.forex.ForexUnitSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer5D
import com.jinxservers.alphavantage.util.toEntry
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapEntrySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder



@Serializable(with = ForexUnitSerializer::class)
public class ForexUnit(
    public val timestamp: Instant,
    public val open: Double,
    public val high: Double,
    public val low: Double,
    public val close: Double
) : Comparable<ForexUnit> {
    internal constructor(
        timestamp: Instant,
        surrogate: ForexUnitSurrogate
    ) : this(timestamp, surrogate.open, surrogate.high, surrogate.low, surrogate.close)

    override fun toString(): String {
        return "ForexUnit(timestamp=$timestamp, open=$open, high=$high, low=$low, close=$close)"
    }

    override fun compareTo(other: ForexUnit): Int {
        return timestamp.compareTo(other.timestamp)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexUnit

        if (timestamp != other.timestamp) return false
        if (open != other.open) return false
        if (high != other.high) return false
        if (low != other.low) return false
        if (close != other.close) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + open.hashCode()
        result = 31 * result + high.hashCode()
        result = 31 * result + low.hashCode()
        result = 31 * result + close.hashCode()
        return result
    }
}

@Serializable
@SerialName("ForexUnit")
internal class ForexUnitSurrogate(
    @SerialName("1. open") @Serializable(with = DoubleAsStringSerializer5D::class)
    val open: Double,
    @SerialName("2. high") @Serializable(with = DoubleAsStringSerializer5D::class)
    val high: Double,
    @SerialName("3. low") @Serializable(with = DoubleAsStringSerializer5D::class)
    val low: Double,
    @SerialName("4. close") @Serializable(with = DoubleAsStringSerializer5D::class)
    val close: Double
)