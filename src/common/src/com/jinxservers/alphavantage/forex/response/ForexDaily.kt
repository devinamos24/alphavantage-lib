package com.jinxservers.alphavantage.forex.response

import com.jinxservers.alphavantage.forex.ForexDailySerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ForexDailySerializer::class)
public class ForexDaily(
    @SerialName("Meta Data")
    public val metaData: ForexDailyMetaData,
    @SerialName("Time Series FX (Daily)")
    public val forexUnits: List<ForexUnit>
) : Comparable<ForexDaily> {

    internal constructor(forexDailySurrogate: ForexDailySurrogate) : this(
        forexDailySurrogate.metaData,
        forexDailySurrogate.forexUnits.map { ForexUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ForexDaily(metaData=$metaData, forexUnits=$forexUnits)"
    }

    override fun compareTo(other: ForexDaily): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexDaily

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
internal class ForexDailySurrogate(
    @SerialName("Meta Data")
    val metaData: ForexDailyMetaData,
    @SerialName("Time Series FX (Daily)")
    val forexUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, ForexUnitSurrogate>
)

@Serializable
public class ForexDailyMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. From Symbol")
    public val fromSymbol: String,
    @SerialName("3. To Symbol")
    public val toSymbol: String,
    @SerialName("4. Output Size")
    public val outputSize: String,
    @SerialName("5. Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("6. Time Zone")
    public val timeZone: String
) : Comparable<ForexDailyMetaData> {

    override fun toString(): String {
        return "ForexDailyMetaData(information='$information', fromSymbol='$fromSymbol', toSymbol='$toSymbol', outputSize='$outputSize', lastRefreshed=$lastRefreshed, timeZone='$timeZone')"
    }

    override fun compareTo(other: ForexDailyMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexDailyMetaData

        if (information != other.information) return false
        if (fromSymbol != other.fromSymbol) return false
        if (toSymbol != other.toSymbol) return false
        if (outputSize != other.outputSize) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = information.hashCode()
        result = 31 * result + fromSymbol.hashCode()
        result = 31 * result + toSymbol.hashCode()
        result = 31 * result + outputSize.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }


}