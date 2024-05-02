package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.HTDCPERIODSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = HTDCPERIODSerializer::class)
public class HTDCPERIOD(
    public val metaData: HTDCPERIODMetaData,
    public val htdcperiodUnits: List<HTDCPERIODUnit>
) {
    internal constructor(
        htdcperiodSurrogate: HTDCPERIODSurrogate
    ) : this(
        metaData = htdcperiodSurrogate.metaData,
        htdcperiodUnits = htdcperiodSurrogate.htdcperiodUnits.map { HTDCPERIODUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "HTDCPERIOD(metaData=$metaData, htdcperiodUnits=$htdcperiodUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTDCPERIOD

        if (metaData != other.metaData) return false
        if (htdcperiodUnits != other.htdcperiodUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + htdcperiodUnits.hashCode()
        return result
    }
}

@Serializable
internal class HTDCPERIODSurrogate(
    @SerialName("Meta Data")
    val metaData: HTDCPERIODMetaData,
    @SerialName("Technical Analysis: HT_DCPERIOD")
    val htdcperiodUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, HTDCPERIODUnitSurrogate>
) {
    constructor(
        htdcperiod: HTDCPERIOD
    ) : this(
        metaData = htdcperiod.metaData,
        htdcperiodUnits = htdcperiod.htdcperiodUnits.associate { it.timestamp to HTDCPERIODUnitSurrogate(it) }
    )
}

@Serializable
public class HTDCPERIODMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Series Type")
    public val seriesType: String,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "HTDCPERIODMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTDCPERIODMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class HTDCPERIODUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("DCPERIOD") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val dcperiod: Double
) {
    internal constructor(
        timestamp: Instant,
        htdcperiodUnitSurrogate: HTDCPERIODUnitSurrogate
    ) : this(
        timestamp = timestamp,
        dcperiod = htdcperiodUnitSurrogate.dcperiod,
    )

    override fun toString(): String {
        return "HTDCPERIODUnit(timestamp=$timestamp, dcperiod=$dcperiod)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTDCPERIODUnit

        if (timestamp != other.timestamp) return false
        if (dcperiod != other.dcperiod) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + dcperiod.hashCode()
        return result
    }
}

@Serializable
internal class HTDCPERIODUnitSurrogate(
    @SerialName("DCPERIOD") @Serializable(with = DoubleAsStringSerializer4D::class)
    val dcperiod: Double
) {
    constructor(
        htdcperiodUnit: HTDCPERIODUnit
    ) : this(
        dcperiod = htdcperiodUnit.dcperiod,
    )
}