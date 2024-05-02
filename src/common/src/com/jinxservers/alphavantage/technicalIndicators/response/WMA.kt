package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.WMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = WMASerializer::class)
public class WMA(
    public val metaData: WMAMetaData,
    public val wmaUnits: List<WMAUnit>
) {
    internal constructor(
        wmaSurrogate: WMASurrogate
    ) : this(
        metaData = wmaSurrogate.metaData,
        wmaUnits = wmaSurrogate.wmaUnits.map { WMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "WMA(metaData=$metaData, wmaUnits=$wmaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WMA

        if (metaData != other.metaData) return false
        if (wmaUnits != other.wmaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + wmaUnits.hashCode()
        return result
    }
}

@Serializable
internal class WMASurrogate(
    @SerialName("Meta Data")
    val metaData: WMAMetaData,
    @SerialName("Technical Analysis: WMA")
    val wmaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, WMAUnitSurrogate>
) {
    constructor(
        wma: WMA
    ) : this(
        metaData = wma.metaData,
        wmaUnits = wma.wmaUnits.associate { it.timestamp to WMAUnitSurrogate(it) }
    )
}

@Serializable
public class WMAMetaData(
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
        return "WMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WMAMetaData

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
public class WMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("WMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val wma: Double
) {
    internal constructor(
        timestamp: Instant,
        wmaUnitSurrogate: WMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        wma = wmaUnitSurrogate.wma,
    )

    override fun toString(): String {
        return "WMAUnit(timestamp=$timestamp, wma=$wma)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WMAUnit

        if (timestamp != other.timestamp) return false
        if (wma != other.wma) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + wma.hashCode()
        return result
    }
}

@Serializable
internal class WMAUnitSurrogate(
    @SerialName("WMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val wma: Double
) {
    constructor(
        wmaUnit: WMAUnit
    ) : this(
        wma = wmaUnit.wma,
    )
}