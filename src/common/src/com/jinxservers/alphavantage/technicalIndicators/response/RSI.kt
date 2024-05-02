package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.RSISerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = RSISerializer::class)
public class RSI(
    public val metaData: RSIMetaData,
    public val rsiUnits: List<RSIUnit>
) {
    internal constructor(
        rsiSurrogate: RSISurrogate
    ) : this(
        metaData = rsiSurrogate.metaData,
        rsiUnits = rsiSurrogate.rsiUnits.map { RSIUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "RSI(metaData=$metaData, rsiUnits=$rsiUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as RSI

        if (metaData != other.metaData) return false
        if (rsiUnits != other.rsiUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + rsiUnits.hashCode()
        return result
    }
}

@Serializable
internal class RSISurrogate(
    @SerialName("Meta Data")
    val metaData: RSIMetaData,
    @SerialName("Technical Analysis: RSI")
    val rsiUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, RSIUnitSurrogate>
) {
    constructor(
        rsi: RSI
    ) : this(
        metaData = rsi.metaData,
        rsiUnits = rsi.rsiUnits.associate { it.timestamp to RSIUnitSurrogate(it) }
    )
}

@Serializable
public class RSIMetaData(
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
    @SerialName("6: Series Type")
    public val seriesType: String,
    @SerialName("7: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "RSIMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as RSIMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timePeriod
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class RSIUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("RSI") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val rsi: Double
) {
    internal constructor(
        timestamp: Instant,
        rsiUnitSurrogate: RSIUnitSurrogate
    ) : this(
        timestamp = timestamp,
        rsi = rsiUnitSurrogate.rsi,
    )

    override fun toString(): String {
        return "RSIUnit(timestamp=$timestamp, rsi=$rsi)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as RSIUnit

        if (timestamp != other.timestamp) return false
        if (rsi != other.rsi) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + rsi.hashCode()
        return result
    }
}

@Serializable
internal class RSIUnitSurrogate(
    @SerialName("RSI") @Serializable(with = DoubleAsStringSerializer4D::class)
    val rsi: Double
) {
    constructor(
        rsiUnit: RSIUnit
    ) : this(
        rsi = rsiUnit.rsi,
    )
}