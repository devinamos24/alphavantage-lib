package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.TechnicalIndicatorObjectSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TechnicalIndicatorObjectSerializer::class)
public class TechnicalIndicatorObject(
    public val metaData: TechnicalIndicatorMetaData,
    public val technicalIndicatorUnits: List<TechnicalIndicatorUnit>
) {
    internal constructor(technicalIndicatorObjectSurrogate: TechnicalIndicatorObjectSurrogate) : this(
        metaData = technicalIndicatorObjectSurrogate.metaData,
        technicalIndicatorUnits = technicalIndicatorObjectSurrogate.mapOfUnits
    )

    override fun toString(): String {
        return "TechnicalIndicatorObject(metaData=$metaData, technicalIndicatorUnits=$technicalIndicatorUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TechnicalIndicatorObject

        if (metaData != other.metaData) return false
        if (technicalIndicatorUnits != other.technicalIndicatorUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + technicalIndicatorUnits.hashCode()
        return result
    }
}

@Serializable
internal class TechnicalIndicatorObjectSurrogate(
    @SerialName("Meta Data")
    val metaData: TechnicalIndicatorMetaData,
    val mapOfUnits: List<TechnicalIndicatorUnit>
) {
    constructor(technicalIndicatorObject: TechnicalIndicatorObject) : this(
        metaData = technicalIndicatorObject.metaData,
        mapOfUnits = technicalIndicatorObject.technicalIndicatorUnits
    )
}

@Serializable
public class TechnicalIndicatorMetaData(
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
        return "TechnicalIndicatorMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TechnicalIndicatorMetaData

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

