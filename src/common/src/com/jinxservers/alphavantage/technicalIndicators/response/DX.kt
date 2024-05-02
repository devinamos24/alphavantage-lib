package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.DXSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = DXSerializer::class)
public class DX(
    public val metaData: DXMetaData,
    public val dxUnits: List<DXUnit>
) {
    internal constructor(
        dxSurrogate: DXSurrogate
    ) : this(
        metaData = dxSurrogate.metaData,
        dxUnits = dxSurrogate.dxUnits.map { DXUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "DX(metaData=$metaData, dxUnits=$dxUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DX

        if (metaData != other.metaData) return false
        if (dxUnits != other.dxUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + dxUnits.hashCode()
        return result
    }
}

@Serializable
internal class DXSurrogate(
    @SerialName("Meta Data")
    val metaData: DXMetaData,
    @SerialName("Technical Analysis: DX")
    val dxUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, DXUnitSurrogate>
) {
    constructor(
        dx: DX
    ) : this(
        metaData = dx.metaData,
        dxUnits = dx.dxUnits.associate { it.timestamp to DXUnitSurrogate(it) }
    )
}

@Serializable
public class DXMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Time Period")
    public val timePeriod: Int,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "DXMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DXMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timePeriod
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class DXUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("DX") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val dx: Double
) {
    internal constructor(
        timestamp: Instant,
        dxUnitSurrogate: DXUnitSurrogate
    ) : this(
        timestamp = timestamp,
        dx = dxUnitSurrogate.dx,
    )

    override fun toString(): String {
        return "DXUnit(timestamp=$timestamp, dx=$dx)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DXUnit

        if (timestamp != other.timestamp) return false
        if (dx != other.dx) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + dx.hashCode()
        return result
    }
}

@Serializable
internal class DXUnitSurrogate(
    @SerialName("DX") @Serializable(with = DoubleAsStringSerializer4D::class)
    val dx: Double
) {
    constructor(
        dxUnit: DXUnit
    ) : this(
        dx = dxUnit.dx,
    )
}