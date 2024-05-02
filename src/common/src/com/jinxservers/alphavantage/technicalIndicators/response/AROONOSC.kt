package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.AROONOSCSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = AROONOSCSerializer::class)
public class AROONOSC(
    public val metaData: AROONOSCMetaData,
    public val aroonoscUnits: List<AROONOSCUnit>
) {
    internal constructor(
        aroonoscSurrogate: AROONOSCSurrogate
    ) : this(
        metaData = aroonoscSurrogate.metaData,
        aroonoscUnits = aroonoscSurrogate.aroonoscUnits.map { AROONOSCUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "AROONOSC(metaData=$metaData, aroonoscUnits=$aroonoscUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AROONOSC

        if (metaData != other.metaData) return false
        if (aroonoscUnits != other.aroonoscUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + aroonoscUnits.hashCode()
        return result
    }
}

@Serializable
internal class AROONOSCSurrogate(
    @SerialName("Meta Data")
    val metaData: AROONOSCMetaData,
    @SerialName("Technical Analysis: AROONOSC")
    val aroonoscUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, AROONOSCUnitSurrogate>
) {
    constructor(
        aroonosc: AROONOSC
    ) : this(
        metaData = aroonosc.metaData,
        aroonoscUnits = aroonosc.aroonoscUnits.associate { it.timestamp to AROONOSCUnitSurrogate(it) }
    )
}

@Serializable
public class AROONOSCMetaData(
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
        return "AROONOSCMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AROONOSCMetaData

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
public class AROONOSCUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("AROONOSC") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val aroonosc: Double
) {
    internal constructor(
        timestamp: Instant,
        aroonoscUnitSurrogate: AROONOSCUnitSurrogate
    ) : this(
        timestamp = timestamp,
        aroonosc = aroonoscUnitSurrogate.aroonosc,
    )

    override fun toString(): String {
        return "AROONOSCUnit(timestamp=$timestamp, aroonosc=$aroonosc)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AROONOSCUnit

        if (timestamp != other.timestamp) return false
        if (aroonosc != other.aroonosc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + aroonosc.hashCode()
        return result
    }
}

@Serializable
internal class AROONOSCUnitSurrogate(
    @SerialName("AROONOSC") @Serializable(with = DoubleAsStringSerializer4D::class)
    val aroonosc: Double
) {
    constructor(
        aroonoscUnit: AROONOSCUnit
    ) : this(
        aroonosc = aroonoscUnit.aroonosc,
    )
}