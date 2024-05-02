package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.VWAPSerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutSecondsAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = VWAPSerializer::class)
public class VWAP(
    public val metaData: VWAPMetaData,
    public val vwapUnits: List<VWAPUnit>
) {
    internal constructor(
        vwapSurrogate: VWAPSurrogate
    ) : this(
        metaData = vwapSurrogate.metaData,
        vwapUnits = vwapSurrogate.vwapUnits.map { VWAPUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "VWAP(metaData=$metaData, vwapUnits=$vwapUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as VWAP

        if (metaData != other.metaData) return false
        if (vwapUnits != other.vwapUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + vwapUnits.hashCode()
        return result
    }
}

@Serializable
internal class VWAPSurrogate(
    @SerialName("Meta Data")
    val metaData: VWAPMetaData,
    @SerialName("Technical Analysis: VWAP")
    val vwapUnits: Map<@Serializable(with = CentralInstantWithoutSecondsAsStringSerializer::class) Instant, VWAPUnitSurrogate>
) {
    constructor(
        vwap: VWAP
    ) : this(
        metaData = vwap.metaData,
        vwapUnits = vwap.vwapUnits.associate { it.timestamp to VWAPUnitSurrogate(it) }
    )
}

@Serializable
public class VWAPMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "VWAPMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as VWAPMetaData

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
public class VWAPUnit(
    @Serializable(with = CentralInstantWithoutSecondsAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("VWAP") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val vwap: Double
) {
    internal constructor(
        timestamp: Instant,
        vwapUnitSurrogate: VWAPUnitSurrogate
    ) : this(
        timestamp = timestamp,
        vwap = vwapUnitSurrogate.vwap,
    )

    override fun toString(): String {
        return "VWAPUnit(timestamp=$timestamp, vwap=$vwap)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as VWAPUnit

        if (timestamp != other.timestamp) return false
        if (vwap != other.vwap) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + vwap.hashCode()
        return result
    }
}

@Serializable
internal class VWAPUnitSurrogate(
    @SerialName("VWAP") @Serializable(with = DoubleAsStringSerializer4D::class)
    val vwap: Double
) {
    constructor(
        vwapUnit: VWAPUnit
    ) : this(
        vwap = vwapUnit.vwap,
    )
}