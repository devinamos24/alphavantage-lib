package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MFISerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MFISerializer::class)
public class MFI(
    public val metaData: MFIMetaData,
    public val mfiUnits: List<MFIUnit>
) {
    internal constructor(
        mfiSurrogate: MFISurrogate
    ) : this(
        metaData = mfiSurrogate.metaData,
        mfiUnits = mfiSurrogate.mfiUnits.map { MFIUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MFI(metaData=$metaData, mfiUnits=$mfiUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MFI

        if (metaData != other.metaData) return false
        if (mfiUnits != other.mfiUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + mfiUnits.hashCode()
        return result
    }
}

@Serializable
internal class MFISurrogate(
    @SerialName("Meta Data")
    val metaData: MFIMetaData,
    @SerialName("Technical Analysis: MFI")
    val mfiUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MFIUnitSurrogate>
) {
    constructor(
        mfi: MFI
    ) : this(
        metaData = mfi.metaData,
        mfiUnits = mfi.mfiUnits.associate { it.timestamp to MFIUnitSurrogate(it) }
    )
}

@Serializable
public class MFIMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Time Period")
    public val timePeriod: Int,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "MFIMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MFIMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timePeriod
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class MFIUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MFI") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val mfi: Double
) {
    internal constructor(
        timestamp: Instant,
        mfiUnitSurrogate: MFIUnitSurrogate
    ) : this(
        timestamp = timestamp,
        mfi = mfiUnitSurrogate.mfi,
    )

    override fun toString(): String {
        return "MFIUnit(timestamp=$timestamp, mfi=$mfi)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MFIUnit

        if (timestamp != other.timestamp) return false
        if (mfi != other.mfi) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + mfi.hashCode()
        return result
    }
}

@Serializable
internal class MFIUnitSurrogate(
    @SerialName("MFI") @Serializable(with = DoubleAsStringSerializer4D::class)
    val mfi: Double
) {
    constructor(
        mfiUnit: MFIUnit
    ) : this(
        mfi = mfiUnit.mfi,
    )
}