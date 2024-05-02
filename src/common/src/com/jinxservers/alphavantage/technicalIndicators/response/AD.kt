package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ADSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ADSerializer::class)
public class AD(
    public val metaData: ADMetaData,
    public val adUnits: List<ADUnit>
) {
    internal constructor(
        adSurrogate: ADSurrogate
    ) : this(
        metaData = adSurrogate.metaData,
        adUnits = adSurrogate.adUnits.map { ADUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "AD(metaData=$metaData, adUnits=$adUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AD

        if (metaData != other.metaData) return false
        if (adUnits != other.adUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + adUnits.hashCode()
        return result
    }
}

@Serializable
internal class ADSurrogate(
    @SerialName("Meta Data")
    val metaData: ADMetaData,
    @SerialName("Technical Analysis: Chaikin A/D")
    val adUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ADUnitSurrogate>
) {
    constructor(
        ad: AD
    ) : this(
        metaData = ad.metaData,
        adUnits = ad.adUnits.associate { it.timestamp to ADUnitSurrogate(it) }
    )
}

@Serializable
public class ADMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "ADMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class ADUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("Chaikin A/D") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val ad: Double
) {
    internal constructor(
        timestamp: Instant,
        adUnitSurrogate: ADUnitSurrogate
    ) : this(
        timestamp = timestamp,
        ad = adUnitSurrogate.ad,
    )

    override fun toString(): String {
        return "ADUnit(timestamp=$timestamp, ad=$ad)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADUnit

        if (timestamp != other.timestamp) return false
        if (ad != other.ad) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + ad.hashCode()
        return result
    }
}

@Serializable
internal class ADUnitSurrogate(
    @SerialName("Chaikin A/D") @Serializable(with = DoubleAsStringSerializer4D::class)
    val ad: Double
) {
    constructor(
        adUnit: ADUnit
    ) : this(
        ad = adUnit.ad,
    )
}