package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.NATRSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = NATRSerializer::class)
public class NATR(
    public val metaData: NATRMetaData,
    public val natrUnits: List<NATRUnit>
) {
    internal constructor(
        natrSurrogate: NATRSurrogate
    ) : this(
        metaData = natrSurrogate.metaData,
        natrUnits = natrSurrogate.natrUnits.map { NATRUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "NATR(metaData=$metaData, natrUnits=$natrUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as NATR

        if (metaData != other.metaData) return false
        if (natrUnits != other.natrUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + natrUnits.hashCode()
        return result
    }
}

@Serializable
internal class NATRSurrogate(
    @SerialName("Meta Data")
    val metaData: NATRMetaData,
    @SerialName("Technical Analysis: NATR")
    val natrUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, NATRUnitSurrogate>
) {
    constructor(
        natr: NATR
    ) : this(
        metaData = natr.metaData,
        natrUnits = natr.natrUnits.associate { it.timestamp to NATRUnitSurrogate(it) }
    )
}

@Serializable
public class NATRMetaData(
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
        return "NATRMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as NATRMetaData

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
public class NATRUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("NATR") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val natr: Double
) {
    internal constructor(
        timestamp: Instant,
        natrUnitSurrogate: NATRUnitSurrogate
    ) : this(
        timestamp = timestamp,
        natr = natrUnitSurrogate.natr,
    )

    override fun toString(): String {
        return "NATRUnit(timestamp=$timestamp, natr=$natr)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as NATRUnit

        if (timestamp != other.timestamp) return false
        if (natr != other.natr) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + natr.hashCode()
        return result
    }
}

@Serializable
internal class NATRUnitSurrogate(
    @SerialName("NATR") @Serializable(with = DoubleAsStringSerializer4D::class)
    val natr: Double
) {
    constructor(
        natrUnit: NATRUnit
    ) : this(
        natr = natrUnit.natr,
    )
}