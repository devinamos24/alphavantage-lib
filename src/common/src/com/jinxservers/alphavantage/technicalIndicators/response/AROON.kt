package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.AROONSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = AROONSerializer::class)
public class AROON(
    public val metaData: AROONMetaData,
    public val aroonUnits: List<AROONUnit>
) {
    internal constructor(
        aroonSurrogate: AROONSurrogate
    ) : this(
        metaData = aroonSurrogate.metaData,
        aroonUnits = aroonSurrogate.aroonUnits.map { AROONUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "AROON(metaData=$metaData, aroonUnits=$aroonUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AROON

        if (metaData != other.metaData) return false
        if (aroonUnits != other.aroonUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + aroonUnits.hashCode()
        return result
    }
}

@Serializable
internal class AROONSurrogate(
    @SerialName("Meta Data")
    val metaData: AROONMetaData,
    @SerialName("Technical Analysis: AROON")
    val aroonUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, AROONUnitSurrogate>
) {
    constructor(
        aroon: AROON
    ) : this(
        metaData = aroon.metaData,
        aroonUnits = aroon.aroonUnits.associate { it.timestamp to AROONUnitSurrogate(it) }
    )
}

@Serializable
public class AROONMetaData(
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
        return "AROONMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AROONMetaData

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
public class AROONUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("Aroon Down") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val aroonDown: Double,
    @SerialName("Aroon Up") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val aroonUp: Double
) {
    internal constructor(
        timestamp: Instant,
        aroonUnitSurrogate: AROONUnitSurrogate
    ) : this(
        timestamp = timestamp,
        aroonDown = aroonUnitSurrogate.aroonDown,
        aroonUp = aroonUnitSurrogate.aroonUp,
    )

    override fun toString(): String {
        return "AROONUnit(timestamp=$timestamp, aroonDown=$aroonDown, aroonUp=$aroonUp)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AROONUnit

        if (timestamp != other.timestamp) return false
        if (aroonDown != other.aroonDown) return false
        if (aroonUp != other.aroonUp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + aroonDown.hashCode()
        result = 31 * result + aroonUp.hashCode()
        return result
    }
}

@Serializable
internal class AROONUnitSurrogate(
    @SerialName("Aroon Down") @Serializable(with = DoubleAsStringSerializer4D::class)
    val aroonDown: Double,
    @SerialName("Aroon Up") @Serializable(with = DoubleAsStringSerializer4D::class)
    val aroonUp: Double
) {
    constructor(
        aroonUnit: AROONUnit
    ) : this(
        aroonDown = aroonUnit.aroonDown,
        aroonUp = aroonUnit.aroonUp,
    )
}