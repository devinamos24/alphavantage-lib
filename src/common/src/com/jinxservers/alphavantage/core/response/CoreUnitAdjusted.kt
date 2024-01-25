package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreUnitAdjustedSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer1D
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import com.jinxservers.alphavantage.util.IntegerAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CoreUnitAdjustedSerializer::class)
public class CoreUnitAdjusted(
    public val timestamp: Instant,
    public val open: Double,
    public val high: Double,
    public val low: Double,
    public val close: Double,
    public val adjustedClose: Double,
    public val volume: Int,
    public val dividendAmount: Double,
    public val splitCoefficient: Double? = null
): Comparable<CoreUnitAdjusted> {

    internal constructor(
        timestamp: Instant,
        surrogate: CoreUnitAdjustedSurrogate
    ) : this(timestamp, surrogate.open, surrogate.high, surrogate.low, surrogate.close, surrogate.adjustedClose, surrogate.volume, surrogate.dividendAmount, surrogate.splitCoefficient)

    //FIXME: As of writing this comment the alpha vantage api structures CoreUnitAdjusted objects different for CoreDaily requests only. ._.
    internal constructor(
        timestamp: Instant,
        surrogate: CoreUnitAdjustedDailySurrogate
    ) : this(timestamp, surrogate.open, surrogate.high, surrogate.low, surrogate.close, surrogate.adjustedClose, surrogate.volume, surrogate.dividendAmount, surrogate.splitCoefficient)

    override fun toString(): String {
        return "CoreUnitAdjusted(timestamp=$timestamp, open=$open, high=$high, low=$low, close=$close, adjustedClose=$adjustedClose, volume=$volume, dividendAmount=$dividendAmount, splitCoefficient=$splitCoefficient)"
    }

    override fun compareTo(other: CoreUnitAdjusted): Int {
        return timestamp.compareTo(other.timestamp)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreUnitAdjusted

        if (timestamp != other.timestamp) return false
        if (open != other.open) return false
        if (high != other.high) return false
        if (low != other.low) return false
        if (close != other.close) return false
        if (adjustedClose != other.adjustedClose) return false
        if (volume != other.volume) return false
        if (dividendAmount != other.dividendAmount) return false
        if (splitCoefficient != other.splitCoefficient) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + open.hashCode()
        result = 31 * result + high.hashCode()
        result = 31 * result + low.hashCode()
        result = 31 * result + close.hashCode()
        result = 31 * result + adjustedClose.hashCode()
        result = 31 * result + volume.hashCode()
        result = 31 * result + dividendAmount.hashCode()
        result = 31 * result + splitCoefficient.hashCode()
        return result
    }
}

@Serializable
internal class CoreUnitAdjustedSurrogate(
    @SerialName("1. open") @Serializable(with = DoubleAsStringSerializer4D::class)
    val open: Double,
    @SerialName("2. high") @Serializable(with = DoubleAsStringSerializer4D::class)
    val high: Double,
    @SerialName("3. low") @Serializable(with = DoubleAsStringSerializer4D::class)
    val low: Double,
    @SerialName("4. close") @Serializable(with = DoubleAsStringSerializer4D::class)
    val close: Double,
    @SerialName("5. adjusted close") @Serializable(with = DoubleAsStringSerializer4D::class)
    val adjustedClose: Double,
    @SerialName("6. volume") @Serializable(with = IntegerAsStringSerializer::class)
    val volume: Int,
    @SerialName("7. dividend amount") @Serializable(with = DoubleAsStringSerializer4D::class)
    val dividendAmount: Double,
    @SerialName("8. split coefficient") @Serializable(with = DoubleAsStringSerializer1D::class)
    val splitCoefficient: Double? = null
)


//FIXME: As of writing this comment the alpha vantage api structures CoreUnitAdjusted objects different for CoreDaily requests only. ._.
@Serializable
internal class CoreUnitAdjustedDailySurrogate(
    @SerialName("1. open") @Serializable(with = DoubleAsStringSerializer::class)
    val open: Double,
    @SerialName("2. high") @Serializable(with = DoubleAsStringSerializer::class)
    val high: Double,
    @SerialName("3. low") @Serializable(with = DoubleAsStringSerializer::class)
    val low: Double,
    @SerialName("4. close") @Serializable(with = DoubleAsStringSerializer::class)
    val close: Double,
    @SerialName("5. adjusted close") @Serializable(with = DoubleAsStringSerializer::class)
    val adjustedClose: Double,
    @SerialName("6. volume") @Serializable(with = IntegerAsStringSerializer::class)
    val volume: Int,
    @SerialName("7. dividend amount") @Serializable(with = DoubleAsStringSerializer4D::class)
    val dividendAmount: Double,
    @SerialName("8. split coefficient") @Serializable(with = DoubleAsStringSerializer1D::class)
    val splitCoefficient: Double? = null
)