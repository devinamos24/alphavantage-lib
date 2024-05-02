package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MIDPOINTSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MIDPOINTSerializer::class)
public class MIDPOINT(
    public val metaData: MIDPOINTMetaData,
    public val midpointUnits: List<MIDPOINTUnit>
) {
    internal constructor(
        midpointSurrogate: MIDPOINTSurrogate
    ) : this(
        metaData = midpointSurrogate.metaData,
        midpointUnits = midpointSurrogate.midpointUnits.map { MIDPOINTUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MIDPOINT(metaData=$metaData, midpointUnits=$midpointUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MIDPOINT

        if (metaData != other.metaData) return false
        if (midpointUnits != other.midpointUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + midpointUnits.hashCode()
        return result
    }
}

@Serializable
internal class MIDPOINTSurrogate(
    @SerialName("Meta Data")
    val metaData: MIDPOINTMetaData,
    @SerialName("Technical Analysis: MIDPOINT")
    val midpointUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MIDPOINTUnitSurrogate>
) {
    constructor(
        midpoint: MIDPOINT
    ) : this(
        metaData = midpoint.metaData,
        midpointUnits = midpoint.midpointUnits.associate { it.timestamp to MIDPOINTUnitSurrogate(it) }
    )
}

@Serializable
public class MIDPOINTMetaData(
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
    @SerialName("6: Series Type")
    public val seriesType: String,
    @SerialName("7: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "MIDPOINTMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MIDPOINTMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timePeriod
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class MIDPOINTUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MIDPOINT") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val midpoint: Double
) {
    internal constructor(
        timestamp: Instant,
        midpointUnitSurrogate: MIDPOINTUnitSurrogate
    ) : this(
        timestamp = timestamp,
        midpoint = midpointUnitSurrogate.midpoint,
    )

    override fun toString(): String {
        return "MIDPOINTUnit(timestamp=$timestamp, midpoint=$midpoint)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MIDPOINTUnit

        if (timestamp != other.timestamp) return false
        if (midpoint != other.midpoint) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + midpoint.hashCode()
        return result
    }
}

@Serializable
internal class MIDPOINTUnitSurrogate(
    @SerialName("MIDPOINT") @Serializable(with = DoubleAsStringSerializer4D::class)
    val midpoint: Double
) {
    constructor(
        midpointUnit: MIDPOINTUnit
    ) : this(
        midpoint = midpointUnit.midpoint,
    )
}