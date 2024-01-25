package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreDailySerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CoreDailySerializer::class)
public class CoreDaily(
    @SerialName("Meta Data")
    public val metaData: CoreDailyMetaData,
    public val coreUnits: List<CoreUnit>
): Comparable<CoreDaily> {

    internal constructor(coreDailySurrogate: CoreDailySurrogate) : this(
        coreDailySurrogate.metaData,
        coreDailySurrogate.coreUnits.map { CoreUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "CoreDaily(metaData=$metaData, coreUnits=$coreUnits)"
    }

    override fun compareTo(other: CoreDaily): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreDaily

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
internal class CoreDailySurrogate(
    @SerialName("Meta Data")
    val metaData: CoreDailyMetaData,
    @SerialName("Time Series (Daily)")
    val coreUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, CoreUnitSurrogate>
)

@Serializable
public class CoreDailyMetaData(
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
): Comparable<CoreDailyMetaData> {

    override fun toString(): String {
        return "CoreDailyMetaData(information='$information', symbol='$symbol', lastRefreshed=$lastRefreshed, outputSize='$outputSize', timeZone='$timeZone')"
    }

    override fun compareTo(other: CoreDailyMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreDailyMetaData

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
