package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ATRSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ATRSerializer::class)
public class ATR(
    public val metaData: ATRMetaData,
    public val atrUnits: List<ATRUnit>
) {
    internal constructor(
        atrSurrogate: ATRSurrogate
    ) : this(
        metaData = atrSurrogate.metaData,
        atrUnits = atrSurrogate.atrUnits.map { ATRUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ATR(metaData=$metaData, atrUnits=$atrUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ATR

        if (metaData != other.metaData) return false
        if (atrUnits != other.atrUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + atrUnits.hashCode()
        return result
    }
}

@Serializable
internal class ATRSurrogate(
    @SerialName("Meta Data")
    val metaData: ATRMetaData,
    @SerialName("Technical Analysis: ATR")
    val atrUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ATRUnitSurrogate>
) {
    constructor(
        atr: ATR
    ) : this(
        metaData = atr.metaData,
        atrUnits = atr.atrUnits.associate { it.timestamp to ATRUnitSurrogate(it) }
    )
}

@Serializable
public class ATRMetaData(
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
        return "ATRMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ATRMetaData

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
public class ATRUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("ATR") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val atr: Double
) {
    internal constructor(
        timestamp: Instant,
        atrUnitSurrogate: ATRUnitSurrogate
    ) : this(
        timestamp = timestamp,
        atr = atrUnitSurrogate.atr,
    )

    override fun toString(): String {
        return "ATRUnit(timestamp=$timestamp, atr=$atr)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ATRUnit

        if (timestamp != other.timestamp) return false
        if (atr != other.atr) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + atr.hashCode()
        return result
    }
}

@Serializable
internal class ATRUnitSurrogate(
    @SerialName("ATR") @Serializable(with = DoubleAsStringSerializer4D::class)
    val atr: Double
) {
    constructor(
        atrUnit: ATRUnit
    ) : this(
        atr = atrUnit.atr,
    )
}