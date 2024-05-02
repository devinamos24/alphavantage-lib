package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.HTTRENDMODESerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.IntegerAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = HTTRENDMODESerializer::class)
public class HTTRENDMODE(
    public val metaData: HTTRENDMODEMetaData,
    public val httrendmodeUnits: List<HTTRENDMODEUnit>
) {
    internal constructor(
        httrendmodeSurrogate: HTTRENDMODESurrogate
    ) : this(
        metaData = httrendmodeSurrogate.metaData,
        httrendmodeUnits = httrendmodeSurrogate.httrendmodeUnits.map { HTTRENDMODEUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "HTTRENDMODE(metaData=$metaData, httrendmodeUnits=$httrendmodeUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTTRENDMODE

        if (metaData != other.metaData) return false
        if (httrendmodeUnits != other.httrendmodeUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + httrendmodeUnits.hashCode()
        return result
    }
}

@Serializable
internal class HTTRENDMODESurrogate(
    @SerialName("Meta Data")
    val metaData: HTTRENDMODEMetaData,
    @SerialName("Technical Analysis: HT_TRENDMODE")
    val httrendmodeUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, HTTRENDMODEUnitSurrogate>
) {
    constructor(
        httrendmode: HTTRENDMODE
    ) : this(
        metaData = httrendmode.metaData,
        httrendmodeUnits = httrendmode.httrendmodeUnits.associate { it.timestamp to HTTRENDMODEUnitSurrogate(it) }
    )
}

@Serializable
public class HTTRENDMODEMetaData(
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
        return "HTTRENDMODEMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTTRENDMODEMetaData

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
public class HTTRENDMODEUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("TRENDMODE") @Serializable(with = IntegerAsStringSerializer::class)
    public val trendmode: Int
) {
    internal constructor(
        timestamp: Instant,
        httrendmodeUnitSurrogate: HTTRENDMODEUnitSurrogate
    ) : this(
        timestamp = timestamp,
        trendmode = httrendmodeUnitSurrogate.trendmode,
    )

    override fun toString(): String {
        return "HTTRENDMODEUnit(timestamp=$timestamp, trendmode=$trendmode)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTTRENDMODEUnit

        if (timestamp != other.timestamp) return false
        if (trendmode != other.trendmode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + trendmode.hashCode()
        return result
    }
}

@Serializable
internal class HTTRENDMODEUnitSurrogate(
    @SerialName("TRENDMODE") @Serializable(with = IntegerAsStringSerializer::class)
    val trendmode: Int
) {
    constructor(
        httrendmodeUnit: HTTRENDMODEUnit
    ) : this(
        trendmode = httrendmodeUnit.trendmode,
    )
}