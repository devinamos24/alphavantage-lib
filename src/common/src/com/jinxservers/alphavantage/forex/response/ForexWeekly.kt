package com.jinxservers.alphavantage.forex.response

import com.jinxservers.alphavantage.forex.ForexWeeklySerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ForexWeeklySerializer::class)
public class ForexWeekly(
    @SerialName("Meta Data")
    public val metaData: ForexWeeklyMetaData,
    @SerialName("Time Series FX (Weekly)")
    public val forexUnits: List<ForexUnit>
) : Comparable<ForexWeekly> {

    internal constructor(forexWeeklySurrogate: ForexWeeklySurrogate) : this(
        forexWeeklySurrogate.metaData,
        forexWeeklySurrogate.forexUnits.map { ForexUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ForexWeekly(metaData=$metaData, forexUnits=$forexUnits)"
    }

    override fun compareTo(other: ForexWeekly): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexWeekly

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
internal class ForexWeeklySurrogate(
    @SerialName("Meta Data")
    val metaData: ForexWeeklyMetaData,
    @SerialName("Time Series FX (Weekly)")
    val forexUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, ForexUnitSurrogate>
)

@Serializable
public class ForexWeeklyMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. From Symbol")
    public val fromSymbol: String,
    @SerialName("3. To Symbol")
    public val toSymbol: String,
    @SerialName("4. Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("5. Time Zone")
    public val timeZone: String
) : Comparable<ForexWeeklyMetaData> {

    override fun toString(): String {
        return "ForexWeeklyMetaData(information='$information', fromSymbol='$fromSymbol', toSymbol='$toSymbol', lastRefreshed=$lastRefreshed, timeZone='$timeZone')"
    }

    override fun compareTo(other: ForexWeeklyMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexWeeklyMetaData

        if (information != other.information) return false
        if (fromSymbol != other.fromSymbol) return false
        if (toSymbol != other.toSymbol) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = information.hashCode()
        result = 31 * result + fromSymbol.hashCode()
        result = 31 * result + toSymbol.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }


}