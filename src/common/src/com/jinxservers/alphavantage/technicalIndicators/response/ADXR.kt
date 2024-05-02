package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ADXRSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ADXRSerializer::class)
public class ADXR(
    public val metaData: ADXRMetaData,
    public val adxrUnits: List<ADXRUnit>
) {
    internal constructor(
        adxrSurrogate: ADXRSurrogate
    ) : this(
        metaData = adxrSurrogate.metaData,
        adxrUnits = adxrSurrogate.adxrUnits.map { ADXRUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ADXR(metaData=$metaData, adxrUnits=$adxrUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADXR

        if (metaData != other.metaData) return false
        if (adxrUnits != other.adxrUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + adxrUnits.hashCode()
        return result
    }
}

@Serializable
internal class ADXRSurrogate(
    @SerialName("Meta Data")
    val metaData: ADXRMetaData,
    @SerialName("Technical Analysis: ADXR")
    val adxrUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ADXRUnitSurrogate>
) {
    constructor(
        adxr: ADXR
    ) : this(
        metaData = adxr.metaData,
        adxrUnits = adxr.adxrUnits.associate { it.timestamp to ADXRUnitSurrogate(it) }
    )
}

@Serializable
public class ADXRMetaData(
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
        return "ADXRMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADXRMetaData

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
public class ADXRUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("ADXR") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val adxr: Double
) {
    internal constructor(
        timestamp: Instant,
        adxrUnitSurrogate: ADXRUnitSurrogate
    ) : this(
        timestamp = timestamp,
        adxr = adxrUnitSurrogate.adxr,
    )

    override fun toString(): String {
        return "ADXRUnit(timestamp=$timestamp, adxr=$adxr)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADXRUnit

        if (timestamp != other.timestamp) return false
        if (adxr != other.adxr) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + adxr.hashCode()
        return result
    }
}

@Serializable
internal class ADXRUnitSurrogate(
    @SerialName("ADXR") @Serializable(with = DoubleAsStringSerializer4D::class)
    val adxr: Double
) {
    constructor(
        adxrUnit: ADXRUnit
    ) : this(
        adxr = adxrUnit.adxr,
    )
}