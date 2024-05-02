package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.EMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = EMASerializer::class)
public class EMA(
    public val metaData: EMAMetaData,
    public val emaUnits: List<EMAUnit>
) {
    internal constructor(
        emaSurrogate: EMASurrogate
    ) : this(
        metaData = emaSurrogate.metaData,
        emaUnits = emaSurrogate.emaUnits.map { EMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "EMA(metaData=$metaData, emaUnits=$emaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EMA

        if (metaData != other.metaData) return false
        if (emaUnits != other.emaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + emaUnits.hashCode()
        return result
    }
}

@Serializable
internal class EMASurrogate(
    @SerialName("Meta Data")
    val metaData: EMAMetaData,
    @SerialName("Technical Analysis: EMA")
    val emaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, EMAUnitSurrogate>
) {
    constructor(
        ema: EMA
    ) : this(
        metaData = ema.metaData,
        emaUnits = ema.emaUnits.associate { it.timestamp to EMAUnitSurrogate(it) }
    )
}

@Serializable
public class EMAMetaData(
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
        return "EMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EMAMetaData

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
public class EMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("EMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val ema: Double
) {
    internal constructor(
        timestamp: Instant,
        emaUnitSurrogate: EMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        ema = emaUnitSurrogate.ema,
    )

    override fun toString(): String {
        return "EMAUnit(timestamp=$timestamp, ema=$ema)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EMAUnit

        if (timestamp != other.timestamp) return false
        if (ema != other.ema) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + ema.hashCode()
        return result
    }
}

@Serializable
internal class EMAUnitSurrogate(
    @SerialName("EMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val ema: Double
) {
    constructor(
        emaUnit: EMAUnit
    ) : this(
        ema = emaUnit.ema,
    )
}