package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.APOSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = APOSerializer::class)
public class APO(
    public val metaData: APOMetaData,
    public val apoUnits: List<APOUnit>
) {
    internal constructor(
        apoSurrogate: APOSurrogate
    ) : this(
        metaData = apoSurrogate.metaData,
        apoUnits = apoSurrogate.apoUnits.map { APOUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "APO(metaData=$metaData, apoUnits=$apoUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as APO

        if (metaData != other.metaData) return false
        if (apoUnits != other.apoUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + apoUnits.hashCode()
        return result
    }
}

@Serializable
internal class APOSurrogate(
    @SerialName("Meta Data")
    val metaData: APOMetaData,
    @SerialName("Technical Analysis: APO")
    val apoUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, APOUnitSurrogate>
) {
    constructor(
        apo: APO
    ) : this(
        metaData = apo.metaData,
        apoUnits = apo.apoUnits.associate { it.timestamp to APOUnitSurrogate(it) }
    )
}

@Serializable
public class APOMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5.1: Fast Period")
    public val fastPeriod: Int,
    @SerialName("5.2: Slow Period")
    public val slowPeriod: Int,
    @SerialName("5.3: MA Type")
    public val maType: Int,
    @SerialName("6: Series Type")
    public val seriesType: String,
    @SerialName("7: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "APOMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', fastPeriod=$fastPeriod, slowPeriod=$slowPeriod, maType=$maType, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as APOMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (fastPeriod != other.fastPeriod) return false
        if (slowPeriod != other.slowPeriod) return false
        if (maType != other.maType) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + fastPeriod
        result = 31 * result + slowPeriod
        result = 31 * result + maType
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class APOUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("APO") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val apo: Double
) {
    internal constructor(
        timestamp: Instant,
        apoUnitSurrogate: APOUnitSurrogate
    ) : this(
        timestamp = timestamp,
        apo = apoUnitSurrogate.apo,
    )

    override fun toString(): String {
        return "APOUnit(timestamp=$timestamp, apo=$apo)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as APOUnit

        if (timestamp != other.timestamp) return false
        if (apo != other.apo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + apo.hashCode()
        return result
    }
}

@Serializable
internal class APOUnitSurrogate(
    @SerialName("APO") @Serializable(with = DoubleAsStringSerializer4D::class)
    val apo: Double
) {
    constructor(
        apoUnit: APOUnit
    ) : this(
        apo = apoUnit.apo,
    )
}