package com.jinxservers.alphavantage.crypto.response

import com.jinxservers.alphavantage.crypto.CryptoUnitSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer5D
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer8D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable(with = CryptoUnitSerializer::class)
public class CryptoUnit(
    public val timestamp: Instant,
    public val open: Double,
    public val high: Double,
    public val low: Double,
    public val close: Double,
    public val volume: Int,
    internal var USDOpen: Double? = null,
    internal var USDHigh: Double? = null,
    internal var USDLow: Double? = null,
    internal var USDClose: Double? = null,
    // FIXME: The volume currently returned by alpha vantage is broken, please fix this when they fix their api
    internal var brokenVolume: Double? = null,
    internal var USDMarketCap: Double? = null
) : Comparable<CryptoUnit> {
    public constructor(
        timestamp: Instant,
        open: Double,
        USDOpen: Double,
        high: Double,
        USDHigh: Double,
        // FIXME: The volume currently returned by alpha vantage is broken, please fix this when they fix their api
        low: Double,
        USDLow: Double,
        close: Double,
        USDClose: Double,
        brokenVolume: Double,
        USDMarketCap: Double
    ) : this(timestamp, open, high, low, close, brokenVolume.toInt(), USDOpen, USDHigh, USDLow, USDClose, brokenVolume, USDMarketCap)

    internal constructor(
        timestamp: Instant,
        surrogate: IntradayCryptoUnitSurrogate
    ) : this(timestamp, surrogate.open, surrogate.high, surrogate.low, surrogate.close, surrogate.volume)

    internal constructor(
        timestamp: Instant,
        surrogate: CryptoUnitSurrogate
    ) : this(timestamp, surrogate.open, surrogate.high, surrogate.low, surrogate.close, surrogate.volume.toInt()) {
        USDOpen = surrogate.USDOpen
        USDHigh = surrogate.USDHigh
        USDLow = surrogate.USDLow
        USDClose = surrogate.USDClose
        // FIXME: The volume currently returned by alpha vantage is broken, please fix this when they fix their api
        brokenVolume = surrogate.volume
        USDMarketCap = surrogate.USDMarketCap
    }

    override fun toString(): String {
        return "CryptoUnit(timestamp=$timestamp, open=$open, high=$high, low=$low, close=$close, volume=$volume, USDOpen=$USDOpen, USDHigh=$USDHigh, USDLow=$USDLow, USDClose=$USDClose, brokenVolume=$brokenVolume, USDMarketCap=$USDMarketCap)"
    }

    override fun compareTo(other: CryptoUnit): Int {
        return timestamp.compareTo(other.timestamp)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CryptoUnit

        if (timestamp != other.timestamp) return false
        if (open != other.open) return false
        if (high != other.high) return false
        if (low != other.low) return false
        if (close != other.close) return false
        if (volume != other.volume) return false
        if (USDOpen != other.USDOpen) return false
        if (USDHigh != other.USDHigh) return false
        if (USDLow != other.USDLow) return false
        if (USDClose != other.USDClose) return false
        if (brokenVolume != other.brokenVolume) return false
        if (USDMarketCap != other.USDMarketCap) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + open.hashCode()
        result = 31 * result + high.hashCode()
        result = 31 * result + low.hashCode()
        result = 31 * result + close.hashCode()
        result = 31 * result + volume
        result = 31 * result + (USDOpen?.hashCode() ?: 0)
        result = 31 * result + (USDHigh?.hashCode() ?: 0)
        result = 31 * result + (USDLow?.hashCode() ?: 0)
        result = 31 * result + (USDClose?.hashCode() ?: 0)
        result = 31 * result + (brokenVolume?.hashCode() ?: 0)
        result = 31 * result + (USDMarketCap?.hashCode() ?: 0)
        return result
    }


}

@Serializable
internal class IntradayCryptoUnitSurrogate(
    @SerialName("1. open") @Serializable(with = DoubleAsStringSerializer5D::class)
    val open: Double,
    @SerialName("2. high") @Serializable(with = DoubleAsStringSerializer5D::class)
    val high: Double,
    @SerialName("3. low") @Serializable(with = DoubleAsStringSerializer5D::class)
    val low: Double,
    @SerialName("4. close") @Serializable(with = DoubleAsStringSerializer5D::class)
    val close: Double,
    @SerialName("5. volume")
    val volume: Int
)

@Serializable
internal class CryptoUnitSurrogate(
    @SerialName("1a. open") @Serializable(with = DoubleAsStringSerializer8D::class)
    val open: Double,
    @SerialName("2a. high") @Serializable(with = DoubleAsStringSerializer8D::class)
    val high: Double,
    @SerialName("3a. low") @Serializable(with = DoubleAsStringSerializer8D::class)
    val low: Double,
    @SerialName("4a. close") @Serializable(with = DoubleAsStringSerializer8D::class)
    val close: Double,
    // FIXME: The volume currently returned by alpha vantage is broken, please fix this when they fix their api
    @SerialName("5. volume") @Serializable(with = DoubleAsStringSerializer8D::class)
    val volume: Double,
    @SerialName("1b. open") @Serializable(with = DoubleAsStringSerializer8D::class)
    val USDOpen: Double,
    @SerialName("2b. high") @Serializable(with = DoubleAsStringSerializer8D::class)
    val USDHigh: Double,
    @SerialName("3b. low") @Serializable(with = DoubleAsStringSerializer8D::class)
    val USDLow: Double,
    @SerialName("4b. close") @Serializable(with = DoubleAsStringSerializer8D::class)
    val USDClose: Double,
    @SerialName("6. market cap") @Serializable(with = DoubleAsStringSerializer8D::class)
    val USDMarketCap: Double
)