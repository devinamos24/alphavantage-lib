package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreIntradaySerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable(with = CoreIntradaySerializer::class)
public class CoreIntraday(
    public val metaData: CoreIntradayMetaData,
    public val coreUnits: List<CoreUnit>
): Comparable<CoreIntraday> {

    internal constructor(coreIntradaySurrogate: CoreIntradaySurrogate) : this(
        coreIntradaySurrogate.metaData,
        coreIntradaySurrogate.coreUnits.map { CoreUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "CoreIntraday(metaData=$metaData, coreUnits=$coreUnits)"
    }

    override fun compareTo(other: CoreIntraday): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreIntraday

        if (metaData != other.metaData) return false
        if (coreUnits != other.coreUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + coreUnits.hashCode()
        return result
    }
}

@Serializable
internal class CoreIntradaySurrogate @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("Meta Data")
    val metaData: CoreIntradayMetaData,
    @JsonNames("Time Series (1min)", "Time Series (5min)", "Time Series (15min)", "Time Series (30min)", "Time Series (60min)")
    val coreUnits: Map<@Serializable(with = CentralInstantAsStringSerializer::class)Instant, CoreUnitSurrogate>
) {
    constructor(coreIntraday: CoreIntraday) : this(
        coreIntraday.metaData,
        coreIntraday.coreUnits.associate { it.timestamp to CoreUnitSurrogate(it.open, it.high, it.low, it.close, it.volume) }
    )
}

@Serializable
public class CoreIntradayMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. Symbol")
    public val symbol: String,
    @SerialName("3. Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4. Interval")
    public val interval: String,
    @SerialName("5. Output Size")
    public val outputSize: String,
    @SerialName("6. Time Zone")
    public val timeZone: String
) : Comparable<CoreIntradayMetaData> {

    override fun toString(): String {
        return "CoreIntradayMetaData(information='$information', symbol='$symbol', lastRefreshed=$lastRefreshed, interval='$interval', outputSize='$outputSize', timeZone='$timeZone')"
    }

    override fun compareTo(other: CoreIntradayMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreIntradayMetaData

        if (information != other.information) return false
        if (symbol != other.symbol) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (outputSize != other.outputSize) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = information.hashCode()
        result = 31 * result + symbol.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + outputSize.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}
