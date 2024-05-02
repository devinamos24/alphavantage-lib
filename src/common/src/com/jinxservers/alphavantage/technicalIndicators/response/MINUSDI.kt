package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MINUSDISerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MINUSDISerializer::class)
public class MINUSDI(
    public val metaData: MINUSDIMetaData,
    public val minusdiUnits: List<MINUSDIUnit>
) {
    internal constructor(
        minusdiSurrogate: MINUSDISurrogate
    ) : this(
        metaData = minusdiSurrogate.metaData,
        minusdiUnits = minusdiSurrogate.minusdiUnits.map { MINUSDIUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MINUSDI(metaData=$metaData, minusdiUnits=$minusdiUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MINUSDI

        if (metaData != other.metaData) return false
        if (minusdiUnits != other.minusdiUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + minusdiUnits.hashCode()
        return result
    }
}

@Serializable
internal class MINUSDISurrogate(
    @SerialName("Meta Data")
    val metaData: MINUSDIMetaData,
    @SerialName("Technical Analysis: MINUS_DI")
    val minusdiUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MINUSDIUnitSurrogate>
) {
    constructor(
        minusdi: MINUSDI
    ) : this(
        metaData = minusdi.metaData,
        minusdiUnits = minusdi.minusdiUnits.associate { it.timestamp to MINUSDIUnitSurrogate(it) }
    )
}

@Serializable
public class MINUSDIMetaData(
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
        return "MINUSDIMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MINUSDIMetaData

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
public class MINUSDIUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MINUS_DI") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val minusdi: Double
) {
    internal constructor(
        timestamp: Instant,
        minusdiUnitSurrogate: MINUSDIUnitSurrogate
    ) : this(
        timestamp = timestamp,
        minusdi = minusdiUnitSurrogate.minusdi,
    )

    override fun toString(): String {
        return "MINUSDIUnit(timestamp=$timestamp, minusdi=$minusdi)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MINUSDIUnit

        if (timestamp != other.timestamp) return false
        if (minusdi != other.minusdi) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + minusdi.hashCode()
        return result
    }
}

@Serializable
internal class MINUSDIUnitSurrogate(
    @SerialName("MINUS_DI") @Serializable(with = DoubleAsStringSerializer4D::class)
    val minusdi: Double
) {
    constructor(
        minusdiUnit: MINUSDIUnit
    ) : this(
        minusdi = minusdiUnit.minusdi,
    )
}