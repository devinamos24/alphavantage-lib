package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.WILLRSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = WILLRSerializer::class)
public class WILLR(
    public val metaData: WILLRMetaData,
    public val willrUnits: List<WILLRUnit>
) {
    internal constructor(
        willrSurrogate: WILLRSurrogate
    ) : this(
        metaData = willrSurrogate.metaData,
        willrUnits = willrSurrogate.willrUnits.map { WILLRUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "WILLR(metaData=$metaData, willrUnits=$willrUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WILLR

        if (metaData != other.metaData) return false
        if (willrUnits != other.willrUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + willrUnits.hashCode()
        return result
    }
}

@Serializable
internal class WILLRSurrogate(
    @SerialName("Meta Data")
    val metaData: WILLRMetaData,
    @SerialName("Technical Analysis: WILLR")
    val willrUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, WILLRUnitSurrogate>
) {
    constructor(
        willr: WILLR
    ) : this(
        metaData = willr.metaData,
        willrUnits = willr.willrUnits.associate { it.timestamp to WILLRUnitSurrogate(it) }
    )
}

@Serializable
public class WILLRMetaData(
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
        return "WILLRMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WILLRMetaData

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
public class WILLRUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("WILLR") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val willr: Double
) {
    internal constructor(
        timestamp: Instant,
        willrUnitSurrogate: WILLRUnitSurrogate
    ) : this(
        timestamp = timestamp,
        willr = willrUnitSurrogate.willr,
    )

    override fun toString(): String {
        return "WILLRUnit(timestamp=$timestamp, willr=$willr)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WILLRUnit

        if (timestamp != other.timestamp) return false
        if (willr != other.willr) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + willr.hashCode()
        return result
    }
}

@Serializable
internal class WILLRUnitSurrogate(
    @SerialName("WILLR") @Serializable(with = DoubleAsStringSerializer4D::class)
    val willr: Double
) {
    constructor(
        willrUnit: WILLRUnit
    ) : this(
        willr = willrUnit.willr,
    )
}