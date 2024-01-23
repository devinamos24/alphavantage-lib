package com.jinxservers.alphavantage.forex.response

import com.jinxservers.alphavantage.forex.ForexMonthlySerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ForexMonthlySerializer::class)
public class ForexMonthly(
    @SerialName("Meta Data")
    public val metaData: ForexMonthlyMetaData,
    @SerialName("Time Series FX (Monthly)")
    public val forexUnits: List<ForexUnit>
) : Comparable<ForexMonthly> {

    internal constructor(forexMonthlySurrogate: ForexMonthlySurrogate) : this(
        forexMonthlySurrogate.metaData,
        forexMonthlySurrogate.forexUnits.map { ForexUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ForexMonthly(metaData=$metaData, forexUnits=$forexUnits)"
    }

    override fun compareTo(other: ForexMonthly): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexMonthly

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
internal class ForexMonthlySurrogate(
    @SerialName("Meta Data")
    val metaData: ForexMonthlyMetaData,
    @SerialName("Time Series FX (Monthly)")
    val forexUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, ForexUnitSurrogate>
)

@Serializable
public class ForexMonthlyMetaData(
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
) : Comparable<ForexMonthlyMetaData> {

    override fun toString(): String {
        return "ForexMonthlyMetaData(information='$information', fromSymbol='$fromSymbol', toSymbol='$toSymbol', lastRefreshed=$lastRefreshed, timeZone='$timeZone')"
    }

    override fun compareTo(other: ForexMonthlyMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ForexMonthlyMetaData

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