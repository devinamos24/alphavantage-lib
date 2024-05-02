package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.CCISerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CCISerializer::class)
public class CCI(
    public val metaData: CCIMetaData,
    public val cciUnits: List<CCIUnit>
) {
    internal constructor(
        cciSurrogate: CCISurrogate
    ) : this(
        metaData = cciSurrogate.metaData,
        cciUnits = cciSurrogate.cciUnits.map { CCIUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "CCI(metaData=$metaData, cciUnits=$cciUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CCI

        if (metaData != other.metaData) return false
        if (cciUnits != other.cciUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + cciUnits.hashCode()
        return result
    }
}

@Serializable
internal class CCISurrogate(
    @SerialName("Meta Data")
    val metaData: CCIMetaData,
    @SerialName("Technical Analysis: CCI")
    val cciUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, CCIUnitSurrogate>
) {
    constructor(
        cci: CCI
    ) : this(
        metaData = cci.metaData,
        cciUnits = cci.cciUnits.associate { it.timestamp to CCIUnitSurrogate(it) }
    )
}

@Serializable
public class CCIMetaData(
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
        return "CCIMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CCIMetaData

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
public class CCIUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("CCI") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val cci: Double
) {
    internal constructor(
        timestamp: Instant,
        cciUnitSurrogate: CCIUnitSurrogate
    ) : this(
        timestamp = timestamp,
        cci = cciUnitSurrogate.cci,
    )

    override fun toString(): String {
        return "CCIUnit(timestamp=$timestamp, cci=$cci)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CCIUnit

        if (timestamp != other.timestamp) return false
        if (cci != other.cci) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + cci.hashCode()
        return result
    }
}

@Serializable
internal class CCIUnitSurrogate(
    @SerialName("CCI") @Serializable(with = DoubleAsStringSerializer4D::class)
    val cci: Double
) {
    constructor(
        cciUnit: CCIUnit
    ) : this(
        cci = cciUnit.cci,
    )
}