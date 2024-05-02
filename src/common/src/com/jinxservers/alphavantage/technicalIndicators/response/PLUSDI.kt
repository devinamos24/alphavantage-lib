package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.PLUSDISerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = PLUSDISerializer::class)
public class PLUSDI(
    public val metaData: PLUSDIMetaData,
    public val plusdiUnits: List<PLUSDIUnit>
) {
    internal constructor(
        plusdiSurrogate: PLUSDISurrogate
    ) : this(
        metaData = plusdiSurrogate.metaData,
        plusdiUnits = plusdiSurrogate.plusdiUnits.map { PLUSDIUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "PLUSDI(metaData=$metaData, plusdiUnits=$plusdiUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PLUSDI

        if (metaData != other.metaData) return false
        if (plusdiUnits != other.plusdiUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + plusdiUnits.hashCode()
        return result
    }
}

@Serializable
internal class PLUSDISurrogate(
    @SerialName("Meta Data")
    val metaData: PLUSDIMetaData,
    @SerialName("Technical Analysis: PLUS_DI")
    val plusdiUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, PLUSDIUnitSurrogate>
) {
    constructor(
        plusdi: PLUSDI
    ) : this(
        metaData = plusdi.metaData,
        plusdiUnits = plusdi.plusdiUnits.associate { it.timestamp to PLUSDIUnitSurrogate(it) }
    )
}

@Serializable
public class PLUSDIMetaData(
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
        return "PLUSDIMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PLUSDIMetaData

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
public class PLUSDIUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("PLUS_DI") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val plusdi: Double
) {
    internal constructor(
        timestamp: Instant,
        plusdiUnitSurrogate: PLUSDIUnitSurrogate
    ) : this(
        timestamp = timestamp,
        plusdi = plusdiUnitSurrogate.plusdi,
    )

    override fun toString(): String {
        return "PLUSDIUnit(timestamp=$timestamp, plusdi=$plusdi)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PLUSDIUnit

        if (timestamp != other.timestamp) return false
        if (plusdi != other.plusdi) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + plusdi.hashCode()
        return result
    }
}

@Serializable
internal class PLUSDIUnitSurrogate(
    @SerialName("PLUS_DI") @Serializable(with = DoubleAsStringSerializer4D::class)
    val plusdi: Double
) {
    constructor(
        plusdiUnit: PLUSDIUnit
    ) : this(
        plusdi = plusdiUnit.plusdi,
    )
}