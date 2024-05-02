package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MOMSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MOMSerializer::class)
public class MOM(
    public val metaData: MOMMetaData,
    public val momUnits: List<MOMUnit>
) {
    internal constructor(
        momSurrogate: MOMSurrogate
    ) : this(
        metaData = momSurrogate.metaData,
        momUnits = momSurrogate.momUnits.map { MOMUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MOM(metaData=$metaData, momUnits=$momUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MOM

        if (metaData != other.metaData) return false
        if (momUnits != other.momUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + momUnits.hashCode()
        return result
    }
}

@Serializable
internal class MOMSurrogate(
    @SerialName("Meta Data")
    val metaData: MOMMetaData,
    @SerialName("Technical Analysis: MOM")
    val momUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MOMUnitSurrogate>
) {
    constructor(
        mom: MOM
    ) : this(
        metaData = mom.metaData,
        momUnits = mom.momUnits.associate { it.timestamp to MOMUnitSurrogate(it) }
    )
}

@Serializable
public class MOMMetaData(
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
        return "MOMMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MOMMetaData

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
public class MOMUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MOM") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val mom: Double
) {
    internal constructor(
        timestamp: Instant,
        momUnitSurrogate: MOMUnitSurrogate
    ) : this(
        timestamp = timestamp,
        mom = momUnitSurrogate.mom,
    )

    override fun toString(): String {
        return "MOMUnit(timestamp=$timestamp, mom=$mom)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MOMUnit

        if (timestamp != other.timestamp) return false
        if (mom != other.mom) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + mom.hashCode()
        return result
    }
}

@Serializable
internal class MOMUnitSurrogate(
    @SerialName("MOM") @Serializable(with = DoubleAsStringSerializer4D::class)
    val mom: Double
) {
    constructor(
        momUnit: MOMUnit
    ) : this(
        mom = momUnit.mom,
    )
}