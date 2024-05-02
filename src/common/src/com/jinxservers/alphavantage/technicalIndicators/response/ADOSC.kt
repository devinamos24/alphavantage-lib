package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ADOSCSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ADOSCSerializer::class)
public class ADOSC(
    public val metaData: ADOSCMetaData,
    public val adoscUnits: List<ADOSCUnit>
) {
    internal constructor(
        adoscSurrogate: ADOSCSurrogate
    ) : this(
        metaData = adoscSurrogate.metaData,
        adoscUnits = adoscSurrogate.adoscUnits.map { ADOSCUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ADOSC(metaData=$metaData, adoscUnits=$adoscUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADOSC

        if (metaData != other.metaData) return false
        if (adoscUnits != other.adoscUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + adoscUnits.hashCode()
        return result
    }
}

@Serializable
internal class ADOSCSurrogate(
    @SerialName("Meta Data")
    val metaData: ADOSCMetaData,
    @SerialName("Technical Analysis: ADOSC")
    val adoscUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ADOSCUnitSurrogate>
) {
    constructor(
        adosc: ADOSC
    ) : this(
        metaData = adosc.metaData,
        adoscUnits = adosc.adoscUnits.associate { it.timestamp to ADOSCUnitSurrogate(it) }
    )
}

@Serializable
public class ADOSCMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5.1: FastK Period")
    public val fastKPeriod: Int,
    @SerialName("5.2: SlowK Period")
    public val slowKPeriod: Int,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "ADOSCMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', fastKPeriod=$fastKPeriod, slowKPeriod=$slowKPeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADOSCMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (fastKPeriod != other.fastKPeriod) return false
        if (slowKPeriod != other.slowKPeriod) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + fastKPeriod
        result = 31 * result + slowKPeriod
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class ADOSCUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("ADOSC") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val adosc: Double
) {
    internal constructor(
        timestamp: Instant,
        adoscUnitSurrogate: ADOSCUnitSurrogate
    ) : this(
        timestamp = timestamp,
        adosc = adoscUnitSurrogate.adosc,
    )

    override fun toString(): String {
        return "ADOSCUnit(timestamp=$timestamp, adosc=$adosc)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ADOSCUnit

        if (timestamp != other.timestamp) return false
        if (adosc != other.adosc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + adosc.hashCode()
        return result
    }
}

@Serializable
internal class ADOSCUnitSurrogate(
    @SerialName("ADOSC") @Serializable(with = DoubleAsStringSerializer4D::class)
    val adosc: Double
) {
    constructor(
        adoscUnit: ADOSCUnit
    ) : this(
        adosc = adoscUnit.adosc,
    )
}