package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ADXSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ADXSerializer::class)
public class ADX(
    public val metaData: ADXMetaData,
    public val adxUnits: List<ADXUnit>
) {
    internal constructor(
        adxSurrogate: ADXSurrogate
    ) : this(
        metaData = adxSurrogate.metaData,
        adxUnits = adxSurrogate.adxUnits.map { ADXUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ADX(metaData=$metaData, adxUnits=$adxUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADX

        if (metaData != other.metaData) return false
        if (adxUnits != other.adxUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + adxUnits.hashCode()
        return result
    }
}

@Serializable
internal class ADXSurrogate(
    @SerialName("Meta Data")
    val metaData: ADXMetaData,
    @SerialName("Technical Analysis: ADX")
    val adxUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ADXUnitSurrogate>
) {
    constructor(
        adx: ADX
    ) : this(
        metaData = adx.metaData,
        adxUnits = adx.adxUnits.associate { it.timestamp to ADXUnitSurrogate(it) }
    )
}

@Serializable
public class ADXMetaData(
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
        return "ADXMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADXMetaData

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
public class ADXUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("ADX") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val adx: Double
) {
    internal constructor(
        timestamp: Instant,
        adxUnitSurrogate: ADXUnitSurrogate
    ) : this(
        timestamp = timestamp,
        adx = adxUnitSurrogate.adx,
    )

    override fun toString(): String {
        return "ADXUnit(timestamp=$timestamp, adx=$adx)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADXUnit

        if (timestamp != other.timestamp) return false
        if (adx != other.adx) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + adx.hashCode()
        return result
    }
}

@Serializable
internal class ADXUnitSurrogate(
    @SerialName("ADX") @Serializable(with = DoubleAsStringSerializer4D::class)
    val adx: Double
) {
    constructor(
        adxUnit: ADXUnit
    ) : this(
        adx = adxUnit.adx,
    )
}