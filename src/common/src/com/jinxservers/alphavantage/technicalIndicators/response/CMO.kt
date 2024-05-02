package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.CMOSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CMOSerializer::class)
public class CMO(
    public val metaData: CMOMetaData,
    public val cmoUnits: List<CMOUnit>
) {
    internal constructor(
        cmoSurrogate: CMOSurrogate
    ) : this(
        metaData = cmoSurrogate.metaData,
        cmoUnits = cmoSurrogate.cmoUnits.map { CMOUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "CMO(metaData=$metaData, cmoUnits=$cmoUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CMO

        if (metaData != other.metaData) return false
        if (cmoUnits != other.cmoUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + cmoUnits.hashCode()
        return result
    }
}

@Serializable
internal class CMOSurrogate(
    @SerialName("Meta Data")
    val metaData: CMOMetaData,
    @SerialName("Technical Analysis: CMO")
    val cmoUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, CMOUnitSurrogate>
) {
    constructor(
        cmo: CMO
    ) : this(
        metaData = cmo.metaData,
        cmoUnits = cmo.cmoUnits.associate { it.timestamp to CMOUnitSurrogate(it) }
    )
}

@Serializable
public class CMOMetaData(
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
        return "CMOMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CMOMetaData

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
public class CMOUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("CMO") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val cmo: Double
) {
    internal constructor(
        timestamp: Instant,
        cmoUnitSurrogate: CMOUnitSurrogate
    ) : this(
        timestamp = timestamp,
        cmo = cmoUnitSurrogate.cmo,
    )

    override fun toString(): String {
        return "CMOUnit(timestamp=$timestamp, cmo=$cmo)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CMOUnit

        if (timestamp != other.timestamp) return false
        if (cmo != other.cmo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + cmo.hashCode()
        return result
    }
}

@Serializable
internal class CMOUnitSurrogate(
    @SerialName("CMO") @Serializable(with = DoubleAsStringSerializer4D::class)
    val cmo: Double
) {
    constructor(
        cmoUnit: CMOUnit
    ) : this(
        cmo = cmoUnit.cmo,
    )
}