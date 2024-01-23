package com.jinxservers.alphavantage.forex.response

import com.jinxservers.alphavantage.forex.ForexIntradaySerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable(with = ForexIntradaySerializer::class)
public class ForexIntraday(
    @SerialName("Meta Data")
    public val metaData: ForexIntradayMetaData,
    public val forexUnits: List<ForexUnit>
) : Comparable<ForexIntraday> {

    internal constructor(forexIntradaySurrogate: ForexIntradaySurrogate) : this(
        forexIntradaySurrogate.metaData,
        forexIntradaySurrogate.forexUnits.map { ForexUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "TimeSeriesIntraday(metaData=$metaData, forexUnits=$forexUnits)"
    }

    override fun compareTo(other: ForexIntraday): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexIntraday

        if (metaData != other.metaData) return false
        if (forexUnits != other.forexUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + forexUnits.hashCode()
        return result
    }
}

@Serializable
internal class ForexIntradaySurrogate @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("Meta Data")
    val metaData: ForexIntradayMetaData,
    @JsonNames("Time Series FX (1min)", "Time Series FX (5min)", "Time Series FX (15min)", "Time Series FX (30min)", "Time Series FX (60min)")
    val forexUnits: Map<@Serializable(with = CentralInstantAsStringSerializer::class)Instant, ForexUnitSurrogate>
) {
    constructor(forexIntraday: ForexIntraday) : this(
        forexIntraday.metaData,
        forexIntraday.forexUnits.associate { it.timestamp to ForexUnitSurrogate(it.open, it.high, it.low, it.close) }
    )
}

@Serializable
public class ForexIntradayMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. From Symbol")
    public val fromSymbol: String,
    @SerialName("3. To Symbol")
    public val toSymbol: String,
    @SerialName("4. Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("5. Interval")
    public val interval: String,
    @SerialName("6. Output Size")
    public val outputSize: String,
    @SerialName("7. Time Zone")
    public val timeZone: String
) : Comparable<ForexIntradayMetaData> {

    override fun toString(): String {
        return "MetaData(information='$information', fromSymbol='$fromSymbol', toSymbol='$toSymbol', lastRefreshed=$lastRefreshed, interval=$interval, outputSize='$outputSize', timeZone='$timeZone')"
    }

    override fun compareTo(other: ForexIntradayMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexIntradayMetaData

        if (information != other.information) return false
        if (fromSymbol != other.fromSymbol) return false
        if (toSymbol != other.toSymbol) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (outputSize != other.outputSize) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = information.hashCode()
        result = 31 * result + fromSymbol.hashCode()
        result = 31 * result + toSymbol.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + outputSize.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }

}