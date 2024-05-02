package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.HTTRENDLINESerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = HTTRENDLINESerializer::class)
public class HTTRENDLINE(
    public val metaData: HTTRENDLINEMetaData,
    public val httrendlineUnits: List<HTTRENDLINEUnit>
) {
    internal constructor(
        httrendlineSurrogate: HTTRENDLINESurrogate
    ) : this(
        metaData = httrendlineSurrogate.metaData,
        httrendlineUnits = httrendlineSurrogate.httrendlineUnits.map { HTTRENDLINEUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "HTTRENDLINE(metaData=$metaData, httrendlineUnits=$httrendlineUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTTRENDLINE

        if (metaData != other.metaData) return false
        if (httrendlineUnits != other.httrendlineUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + httrendlineUnits.hashCode()
        return result
    }
}

@Serializable
internal class HTTRENDLINESurrogate(
    @SerialName("Meta Data")
    val metaData: HTTRENDLINEMetaData,
    @SerialName("Technical Analysis: HT_TRENDLINE")
    val httrendlineUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, HTTRENDLINEUnitSurrogate>
) {
    constructor(
        httrendline: HTTRENDLINE
    ) : this(
        metaData = httrendline.metaData,
        httrendlineUnits = httrendline.httrendlineUnits.associate { it.timestamp to HTTRENDLINEUnitSurrogate(it) }
    )
}

@Serializable
public class HTTRENDLINEMetaData(
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
        return "HTTRENDLINEMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTTRENDLINEMetaData

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
public class HTTRENDLINEUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("HT_TRENDLINE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val httrendline: Double
) {
    internal constructor(
        timestamp: Instant,
        httrendlineUnitSurrogate: HTTRENDLINEUnitSurrogate
    ) : this(
        timestamp = timestamp,
        httrendline = httrendlineUnitSurrogate.httrendline,
    )

    override fun toString(): String {
        return "HTTRENDLINEUnit(timestamp=$timestamp, httrendline=$httrendline)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTTRENDLINEUnit

        if (timestamp != other.timestamp) return false
        if (httrendline != other.httrendline) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + httrendline.hashCode()
        return result
    }
}

@Serializable
internal class HTTRENDLINEUnitSurrogate(
    @SerialName("HT_TRENDLINE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val httrendline: Double
) {
    constructor(
        httrendlineUnit: HTTRENDLINEUnit
    ) : this(
        httrendline = httrendlineUnit.httrendline,
    )
}