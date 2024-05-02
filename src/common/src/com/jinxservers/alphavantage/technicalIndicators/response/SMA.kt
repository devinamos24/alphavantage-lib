package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.SMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = SMASerializer::class)
public class SMA(
    public val metaData: SMAMetaData,
    public val smaUnits: List<SMAUnit>
) {
    internal constructor(
        smaSurrogate: SMASurrogate
    ) : this(
        metaData = smaSurrogate.metaData,
        smaUnits = smaSurrogate.smaUnits.map { SMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "SMA(metaData=$metaData, smaUnits=$smaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SMA

        if (metaData != other.metaData) return false
        if (smaUnits != other.smaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + smaUnits.hashCode()
        return result
    }
}

@Serializable
internal class SMASurrogate(
    @SerialName("Meta Data")
    val metaData: SMAMetaData,
    @SerialName("Technical Analysis: SMA")
    val smaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, SMAUnitSurrogate>
) {
    constructor(
        sma: SMA
    ) : this(
        metaData = sma.metaData,
        smaUnits = sma.smaUnits.associate { it.timestamp to SMAUnitSurrogate(it) }
    )
}

@Serializable
public class SMAMetaData(
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
        return "SMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SMAMetaData

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
public class SMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("SMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val sma: Double
) {
    internal constructor(
        timestamp: Instant,
        smaUnitSurrogate: SMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        sma = smaUnitSurrogate.sma,
    )

    override fun toString(): String {
        return "SMAUnit(timestamp=$timestamp, sma=$sma)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SMAUnit

        if (timestamp != other.timestamp) return false
        if (sma != other.sma) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + sma.hashCode()
        return result
    }
}

@Serializable
internal class SMAUnitSurrogate(
    @SerialName("SMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val sma: Double
) {
    constructor(
        smaUnit: SMAUnit
    ) : this(
        sma = smaUnit.sma,
    )
}