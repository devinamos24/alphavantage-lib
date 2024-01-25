package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreDailyAdjustedSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CoreDailyAdjustedSerializer::class)
public class CoreDailyAdjusted(
    public val metaData: CoreDailyAdjustedMetaData,
    public val adjustedCoreUnits: List<CoreUnitAdjusted>
): Comparable<CoreDailyAdjusted> {

    internal constructor(coreDailyAdjustedSurrogate: CoreDailyAdjustedSurrogate) : this(
        coreDailyAdjustedSurrogate.metaData,
        coreDailyAdjustedSurrogate.adjustedCoreUnits.map { CoreUnitAdjusted(it.key, it.value) }
    )

    override fun toString(): String {
        return "CoreDailyAdjusted(metaData=$metaData, adjustedCoreUnits=$adjustedCoreUnits)"
    }

    override fun compareTo(other: CoreDailyAdjusted): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreDailyAdjusted

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

//FIXME: As of writing this comment the alpha vantage api structures CoreUnitAdjusted objects different for CoreDaily requests only. ._.
@Serializable
internal class CoreDailyAdjustedSurrogate(
    @SerialName("Meta Data")
    val metaData: CoreDailyAdjustedMetaData,
    @SerialName("Time Series (Daily)")
    val adjustedCoreUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, CoreUnitAdjustedDailySurrogate>
)

@Serializable
public class CoreDailyAdjustedMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. Symbol")
    public val symbol: String,
    @SerialName("3. Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4. Output Size")
    public val outputSize: String,
    @SerialName("5. Time Zone")
    public val timeZone: String
): Comparable<CoreDailyAdjustedMetaData> {

    override fun toString(): String {
        return "CoreDailyAdjustedMetaData(information='$information', symbol='$symbol', lastRefreshed=$lastRefreshed, outputSize='$outputSize', timeZone='$timeZone')"
    }

    override fun compareTo(other: CoreDailyAdjustedMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreDailyAdjustedMetaData

        if (information != other.information) return false
        if (symbol != other.symbol) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (outputSize != other.outputSize) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = information.hashCode()
        result = 31 * result + symbol.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + outputSize.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}