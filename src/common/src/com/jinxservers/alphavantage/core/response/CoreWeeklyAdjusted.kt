package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreWeeklyAdjustedSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CoreWeeklyAdjustedSerializer::class)
public class CoreWeeklyAdjusted(
    public val metaData: CoreWeeklyAdjustedMetaData,
    public val adjustedCoreUnits: List<CoreUnitAdjusted>
): Comparable<CoreWeeklyAdjusted> {

    internal constructor(coreWeeklyAdjustedSurrogate: CoreWeeklyAdjustedSurrogate) : this(
        coreWeeklyAdjustedSurrogate.metaData,
        coreWeeklyAdjustedSurrogate.adjustedCoreUnits.map { CoreUnitAdjusted(it.key, it.value) }
    )

    override fun toString(): String {
        return "CoreWeeklyAdjusted(metaData=$metaData, adjustedCoreUnits=$adjustedCoreUnits)"
    }

    override fun compareTo(other: CoreWeeklyAdjusted): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreWeeklyAdjusted

        if (metaData != other.metaData) return false
        if (adjustedCoreUnits != other.adjustedCoreUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + adjustedCoreUnits.hashCode()
        return result
    }


}

@Serializable
internal class CoreWeeklyAdjustedSurrogate(
    @SerialName("Meta Data")
    val metaData: CoreWeeklyAdjustedMetaData,
    @SerialName("Weekly Adjusted Time Series")
    val adjustedCoreUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, CoreUnitAdjustedSurrogate>
)

@Serializable
public class CoreWeeklyAdjustedMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. Symbol")
    public val symbol: String,
    @SerialName("3. Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4. Time Zone")
    public val timeZone: String
): Comparable<CoreWeeklyAdjustedMetaData> {

    override fun toString(): String {
        return "CoreWeeklyAdjustedMetaData(information='$information', symbol='$symbol', lastRefreshed=$lastRefreshed, timeZone='$timeZone')"
    }

    override fun compareTo(other: CoreWeeklyAdjustedMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreWeeklyAdjustedMetaData

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