package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ULTOSCSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ULTOSCSerializer::class)
public class ULTOSC(
    public val metaData: ULTOSCMetaData,
    public val ultoscUnits: List<ULTOSCUnit>
) {
    internal constructor(
        ultoscSurrogate: ULTOSCSurrogate
    ) : this(
        metaData = ultoscSurrogate.metaData,
        ultoscUnits = ultoscSurrogate.ultoscUnits.map { ULTOSCUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ULTOSC(metaData=$metaData, ultoscUnits=$ultoscUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ULTOSC

        if (metaData != other.metaData) return false
        if (ultoscUnits != other.ultoscUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + ultoscUnits.hashCode()
        return result
    }
}

@Serializable
internal class ULTOSCSurrogate(
    @SerialName("Meta Data")
    val metaData: ULTOSCMetaData,
    @SerialName("Technical Analysis: ULTOSC")
    val ultoscUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ULTOSCUnitSurrogate>
) {
    constructor(
        ultosc: ULTOSC
    ) : this(
        metaData = ultosc.metaData,
        ultoscUnits = ultosc.ultoscUnits.associate { it.timestamp to ULTOSCUnitSurrogate(it) }
    )
}

@Serializable
public class ULTOSCMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5.1: Time Period 1")
    public val timePeriod1: Int,
    @SerialName("5.2: Time Period 2")
    public val timePeriod2: Int,
    @SerialName("5.3: Time Period 3")
    public val timePeriod3: Int,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "ULTOSCMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod1=$timePeriod1, timePeriod2=$timePeriod2, timePeriod3=$timePeriod3, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ULTOSCMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod1 != other.timePeriod1) return false
        if (timePeriod2 != other.timePeriod2) return false
        if (timePeriod3 != other.timePeriod3) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timePeriod1
        result = 31 * result + timePeriod2
        result = 31 * result + timePeriod3
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class ULTOSCUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("ULTOSC") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val ultosc: Double
) {
    internal constructor(
        timestamp: Instant,
        ultoscUnitSurrogate: ULTOSCUnitSurrogate
    ) : this(
        timestamp = timestamp,
        ultosc = ultoscUnitSurrogate.ultosc,
    )

    override fun toString(): String {
        return "ULTOSCUnit(timestamp=$timestamp, ultosc=$ultosc)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ULTOSCUnit

        if (timestamp != other.timestamp) return false
        if (ultosc != other.ultosc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + ultosc.hashCode()
        return result
    }
}

@Serializable
internal class ULTOSCUnitSurrogate(
    @SerialName("ULTOSC") @Serializable(with = DoubleAsStringSerializer4D::class)
    val ultosc: Double
) {
    constructor(
        ultoscUnit: ULTOSCUnit
    ) : this(
        ultosc = ultoscUnit.ultosc,
    )
}