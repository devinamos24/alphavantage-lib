package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.HTDCPHASESerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = HTDCPHASESerializer::class)
public class HTDCPHASE(
    public val metaData: HTDCPHASEMetaData,
    public val htdcphaseUnits: List<HTDCPHASEUnit>
) {
    internal constructor(
        htdcphaseSurrogate: HTDCPHASESurrogate
    ) : this(
        metaData = htdcphaseSurrogate.metaData,
        htdcphaseUnits = htdcphaseSurrogate.htdcphaseUnits.map { HTDCPHASEUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "HTDCPHASE(metaData=$metaData, htdcphaseUnits=$htdcphaseUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTDCPHASE

        if (metaData != other.metaData) return false
        if (htdcphaseUnits != other.htdcphaseUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + htdcphaseUnits.hashCode()
        return result
    }
}

@Serializable
internal class HTDCPHASESurrogate(
    @SerialName("Meta Data")
    val metaData: HTDCPHASEMetaData,
    @SerialName("Technical Analysis: HT_DCPHASE")
    val htdcphaseUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, HTDCPHASEUnitSurrogate>
) {
    constructor(
        htdcphase: HTDCPHASE
    ) : this(
        metaData = htdcphase.metaData,
        htdcphaseUnits = htdcphase.htdcphaseUnits.associate { it.timestamp to HTDCPHASEUnitSurrogate(it) }
    )
}

@Serializable
public class HTDCPHASEMetaData(
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
        return "HTDCPHASEMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTDCPHASEMetaData

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
public class HTDCPHASEUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("HT_DCPHASE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val htdcphase: Double
) {
    internal constructor(
        timestamp: Instant,
        htdcphaseUnitSurrogate: HTDCPHASEUnitSurrogate
    ) : this(
        timestamp = timestamp,
        htdcphase = htdcphaseUnitSurrogate.htdcphase,
    )

    override fun toString(): String {
        return "HTDCPHASEUnit(timestamp=$timestamp, htdcphase=$htdcphase)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTDCPHASEUnit

        if (timestamp != other.timestamp) return false
        if (htdcphase != other.htdcphase) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + htdcphase.hashCode()
        return result
    }
}

@Serializable
internal class HTDCPHASEUnitSurrogate(
    @SerialName("HT_DCPHASE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val htdcphase: Double
) {
    constructor(
        htdcphaseUnit: HTDCPHASEUnit
    ) : this(
        htdcphase = htdcphaseUnit.htdcphase,
    )
}