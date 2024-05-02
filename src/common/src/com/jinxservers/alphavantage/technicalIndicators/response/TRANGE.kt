package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.TRANGESerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TRANGESerializer::class)
public class TRANGE(
    public val metaData: TRANGEMetaData,
    public val trangeUnits: List<TRANGEUnit>
) {
    internal constructor(
        trangeSurrogate: TRANGESurrogate
    ) : this(
        metaData = trangeSurrogate.metaData,
        trangeUnits = trangeSurrogate.trangeUnits.map { TRANGEUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "TRANGE(metaData=$metaData, trangeUnits=$trangeUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRANGE

        if (metaData != other.metaData) return false
        if (trangeUnits != other.trangeUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + trangeUnits.hashCode()
        return result
    }
}

@Serializable
internal class TRANGESurrogate(
    @SerialName("Meta Data")
    val metaData: TRANGEMetaData,
    @SerialName("Technical Analysis: TRANGE")
    val trangeUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, TRANGEUnitSurrogate>
) {
    constructor(
        trange: TRANGE
    ) : this(
        metaData = trange.metaData,
        trangeUnits = trange.trangeUnits.associate { it.timestamp to TRANGEUnitSurrogate(it) }
    )
}

@Serializable
public class TRANGEMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "TRANGEMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRANGEMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class TRANGEUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("TRANGE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val trange: Double
) {
    internal constructor(
        timestamp: Instant,
        trangeUnitSurrogate: TRANGEUnitSurrogate
    ) : this(
        timestamp = timestamp,
        trange = trangeUnitSurrogate.trange,
    )

    override fun toString(): String {
        return "TRANGEUnit(timestamp=$timestamp, trange=$trange)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRANGEUnit

        if (timestamp != other.timestamp) return false
        if (trange != other.trange) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + trange.hashCode()
        return result
    }
}

@Serializable
internal class TRANGEUnitSurrogate(
    @SerialName("TRANGE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val trange: Double
) {
    constructor(
        trangeUnit: TRANGEUnit
    ) : this(
        trange = trangeUnit.trange,
    )
}