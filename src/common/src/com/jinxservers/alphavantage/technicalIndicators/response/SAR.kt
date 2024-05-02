package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.SARSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = SARSerializer::class)
public class SAR(
    public val metaData: SARMetaData,
    public val sarUnits: List<SARUnit>
) {
    internal constructor(
        sarSurrogate: SARSurrogate
    ) : this(
        metaData = sarSurrogate.metaData,
        sarUnits = sarSurrogate.sarUnits.map { SARUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "SAR(metaData=$metaData, sarUnits=$sarUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SAR

        if (metaData != other.metaData) return false
        if (sarUnits != other.sarUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + sarUnits.hashCode()
        return result
    }
}

@Serializable
internal class SARSurrogate(
    @SerialName("Meta Data")
    val metaData: SARMetaData,
    @SerialName("Technical Analysis: SAR")
    val sarUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, SARUnitSurrogate>
) {
    constructor(
        sar: SAR
    ) : this(
        metaData = sar.metaData,
        sarUnits = sar.sarUnits.associate { it.timestamp to SARUnitSurrogate(it) }
    )
}

@Serializable
public class SARMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5.1: Acceleration")
    public val acceleration: Double,
    @SerialName("5.2: Maximum")
    public val maximum: Double,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "SARMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', acceleration=$acceleration, maximum=$maximum, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SARMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (acceleration != other.acceleration) return false
        if (maximum != other.maximum) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + acceleration.hashCode()
        result = 31 * result + maximum.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class SARUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("SAR") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val sar: Double
) {
    internal constructor(
        timestamp: Instant,
        sarUnitSurrogate: SARUnitSurrogate
    ) : this(
        timestamp = timestamp,
        sar = sarUnitSurrogate.sar,
    )

    override fun toString(): String {
        return "SARUnit(timestamp=$timestamp, sar=$sar)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SARUnit

        if (timestamp != other.timestamp) return false
        if (sar != other.sar) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + sar.hashCode()
        return result
    }
}

@Serializable
internal class SARUnitSurrogate(
    @SerialName("SAR") @Serializable(with = DoubleAsStringSerializer4D::class)
    val sar: Double
) {
    constructor(
        sarUnit: SARUnit
    ) : this(
        sar = sarUnit.sar,
    )
}