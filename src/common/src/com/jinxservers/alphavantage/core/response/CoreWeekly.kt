package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreWeeklySerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CoreWeeklySerializer::class)
public class CoreWeekly(
    @SerialName("Meta Data")
    public val metaData: CoreWeeklyMetaData,
    public val coreUnits: List<CoreUnit>
): Comparable<CoreWeekly> {

    internal constructor(coreWeeklySurrogate: CoreWeeklySurrogate) : this(
        coreWeeklySurrogate.metaData,
        coreWeeklySurrogate.coreUnits.map { CoreUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "CoreWeekly(metaData=$metaData, coreUnits=$coreUnits)"
    }

    override fun compareTo(other: CoreWeekly): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreWeekly

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
internal class CoreWeeklySurrogate(
    @SerialName("Meta Data")
    val metaData: CoreWeeklyMetaData,
    @SerialName("Weekly Time Series")
    val coreUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, CoreUnitSurrogate>
)

@Serializable
public class CoreWeeklyMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. Symbol")
    public val symbol: String,
    @SerialName("3. Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4. Time Zone")
    public val timeZone: String
): Comparable<CoreWeeklyMetaData> {

    override fun toString(): String {
        return "CoreWeeklyMetaData(information='$information', symbol='$symbol', lastRefreshed=$lastRefreshed, timeZone='$timeZone')"
    }

    override fun compareTo(other: CoreWeeklyMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreWeeklyMetaData

        if (information != other.information) return false
        if (symbol != other.symbol) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = information.hashCode()
        result = 31 * result + symbol.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}
