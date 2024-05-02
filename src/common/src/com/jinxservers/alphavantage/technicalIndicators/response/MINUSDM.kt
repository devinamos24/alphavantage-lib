package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MINUSDMSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MINUSDMSerializer::class)
public class MINUSDM(
    public val metaData: MINUSDMMetaData,
    public val minusdmUnits: List<MINUSDMUnit>
) {
    internal constructor(
        minusdmSurrogate: MINUSDMSurrogate
    ) : this(
        metaData = minusdmSurrogate.metaData,
        minusdmUnits = minusdmSurrogate.minusdmUnits.map { MINUSDMUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MINUSDM(metaData=$metaData, minusdmUnits=$minusdmUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MINUSDM

        if (metaData != other.metaData) return false
        if (minusdmUnits != other.minusdmUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + minusdmUnits.hashCode()
        return result
    }
}

@Serializable
internal class MINUSDMSurrogate(
    @SerialName("Meta Data")
    val metaData: MINUSDMMetaData,
    @SerialName("Technical Analysis: MINUS_DM")
    val minusdmUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MINUSDMUnitSurrogate>
) {
    constructor(
        minusdm: MINUSDM
    ) : this(
        metaData = minusdm.metaData,
        minusdmUnits = minusdm.minusdmUnits.associate { it.timestamp to MINUSDMUnitSurrogate(it) }
    )
}

@Serializable
public class MINUSDMMetaData(
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
        return "MINUSDMMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MINUSDMMetaData

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
public class MINUSDMUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MINUS_DM") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val minusdm: Double
) {
    internal constructor(
        timestamp: Instant,
        minusdmUnitSurrogate: MINUSDMUnitSurrogate
    ) : this(
        timestamp = timestamp,
        minusdm = minusdmUnitSurrogate.minusdm,
    )

    override fun toString(): String {
        return "MINUSDMUnit(timestamp=$timestamp, minusdm=$minusdm)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MINUSDMUnit

        if (timestamp != other.timestamp) return false
        if (minusdm != other.minusdm) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + minusdm.hashCode()
        return result
    }
}

@Serializable
internal class MINUSDMUnitSurrogate(
    @SerialName("MINUS_DM") @Serializable(with = DoubleAsStringSerializer4D::class)
    val minusdm: Double
) {
    constructor(
        minusdmUnit: MINUSDMUnit
    ) : this(
        minusdm = minusdmUnit.minusdm,
    )
}