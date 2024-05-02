package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.BOPSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = BOPSerializer::class)
public class BOP(
    public val metaData: BOPMetaData,
    public val bopUnits: List<BOPUnit>
) {
    internal constructor(
        bopSurrogate: BOPSurrogate
    ) : this(
        metaData = bopSurrogate.metaData,
        bopUnits = bopSurrogate.bopUnits.map { BOPUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "BOP(metaData=$metaData, bopUnits=$bopUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BOP

        if (metaData != other.metaData) return false
        if (bopUnits != other.bopUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + bopUnits.hashCode()
        return result
    }
}

@Serializable
internal class BOPSurrogate(
    @SerialName("Meta Data")
    val metaData: BOPMetaData,
    @SerialName("Technical Analysis: BOP")
    val bopUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, BOPUnitSurrogate>
) {
    constructor(
        bop: BOP
    ) : this(
        metaData = bop.metaData,
        bopUnits = bop.bopUnits.associate { it.timestamp to BOPUnitSurrogate(it) }
    )
}

@Serializable
public class BOPMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "BOPMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BOPMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class BOPUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("BOP") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val bop: Double
) {
    internal constructor(
        timestamp: Instant,
        bopUnitSurrogate: BOPUnitSurrogate
    ) : this(
        timestamp = timestamp,
        bop = bopUnitSurrogate.bop,
    )

    override fun toString(): String {
        return "BOPUnit(timestamp=$timestamp, bop=$bop)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BOPUnit

        if (timestamp != other.timestamp) return false
        if (bop != other.bop) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + bop.hashCode()
        return result
    }
}

@Serializable
internal class BOPUnitSurrogate(
    @SerialName("BOP") @Serializable(with = DoubleAsStringSerializer4D::class)
    val bop: Double
) {
    constructor(
        bopUnit: BOPUnit
    ) : this(
        bop = bopUnit.bop,
    )
}