package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.core.CoreMonthlySerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CoreMonthlySerializer::class)
public class CoreMonthly(
    @SerialName("Meta Data")
    public val metaData: CoreMonthlyMetaData,
    public val coreUnits: List<CoreUnit>
): Comparable<CoreMonthly> {

    internal constructor(coreMonthlySurrogate: CoreMonthlySurrogate) : this(
        coreMonthlySurrogate.metaData,
        coreMonthlySurrogate.coreUnits.map { CoreUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "CoreMonthly(metaData=$metaData, coreUnits=$coreUnits)"
    }

    override fun compareTo(other: CoreMonthly): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreMonthly

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
internal class CoreMonthlySurrogate(
    @SerialName("Meta Data")
    val metaData: CoreMonthlyMetaData,
    @SerialName("Monthly Time Series")
    val coreUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)Instant, CoreUnitSurrogate>
)

@Serializable
public class CoreMonthlyMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. Symbol")
    public val symbol: String,
    @SerialName("3. Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4. Time Zone")
    public val timeZone: String
): Comparable<CoreMonthlyMetaData> {

    override fun toString(): String {
        return "CoreMonthlyMetaData(information='$information', symbol='$symbol', lastRefreshed=$lastRefreshed, timeZone='$timeZone')"
    }

    override fun compareTo(other: CoreMonthlyMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CoreMonthlyMetaData

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
