package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreUnitSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import com.jinxservers.alphavantage.util.IntegerAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CoreUnitSerializer::class)
public class CoreUnit(
    public val timestamp: Instant,
    public val open: Double,
    public val high: Double,
    public val low: Double,
    public val close: Double,
    public val volume: Int
): Comparable<CoreUnit> {

    internal constructor(
        timestamp: Instant,
        surrogate: CoreUnitSurrogate
    ) : this(timestamp, surrogate.open, surrogate.high, surrogate.low, surrogate.close, surrogate.volume)

    override fun toString(): String {
        return "CoreUnit(timestamp=$timestamp, open=$open, high=$high, low=$low, close=$close, volume=$volume)"
    }

    override fun compareTo(other: CoreUnit): Int {
        return timestamp.compareTo(other.timestamp)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreUnit

        if (timestamp != other.timestamp) return false
        if (open != other.open) return false
        if (high != other.high) return false
        if (low != other.low) return false
        if (close != other.close) return false
        if (volume != other.volume) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + open.hashCode()
        result = 31 * result + high.hashCode()
        result = 31 * result + low.hashCode()
        result = 31 * result + close.hashCode()
        result = 31 * result + volume.hashCode()
        return result
    }
}

@Serializable
internal class CoreUnitSurrogate(
    @SerialName("1. open") @Serializable(with = DoubleAsStringSerializer4D::class)
    val open: Double,
    @SerialName("2. high") @Serializable(with = DoubleAsStringSerializer4D::class)
    val high: Double,
    @SerialName("3. low") @Serializable(with = DoubleAsStringSerializer4D::class)
    val low: Double,
    @SerialName("4. close") @Serializable(with = DoubleAsStringSerializer4D::class)
    val close: Double,
    @SerialName("5. volume") @Serializable(with = IntegerAsStringSerializer::class)
    val volume: Int
)
