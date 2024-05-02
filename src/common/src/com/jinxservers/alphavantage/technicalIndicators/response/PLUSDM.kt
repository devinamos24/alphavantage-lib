package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.PLUSDMSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = PLUSDMSerializer::class)
public class PLUSDM(
    public val metaData: PLUSDMMetaData,
    public val plusdmUnits: List<PLUSDMUnit>
) {
    internal constructor(
        plusdmSurrogate: PLUSDMSurrogate
    ) : this(
        metaData = plusdmSurrogate.metaData,
        plusdmUnits = plusdmSurrogate.plusdmUnits.map { PLUSDMUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "PLUSDM(metaData=$metaData, plusdmUnits=$plusdmUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PLUSDM

        if (metaData != other.metaData) return false
        if (plusdmUnits != other.plusdmUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + plusdmUnits.hashCode()
        return result
    }
}

@Serializable
internal class PLUSDMSurrogate(
    @SerialName("Meta Data")
    val metaData: PLUSDMMetaData,
    @SerialName("Technical Analysis: PLUS_DM")
    val plusdmUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, PLUSDMUnitSurrogate>
) {
    constructor(
        plusdm: PLUSDM
    ) : this(
        metaData = plusdm.metaData,
        plusdmUnits = plusdm.plusdmUnits.associate { it.timestamp to PLUSDMUnitSurrogate(it) }
    )
}

@Serializable
public class PLUSDMMetaData(
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
        return "PLUSDMMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PLUSDMMetaData

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
public class PLUSDMUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("PLUS_DM") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val plusdm: Double
) {
    internal constructor(
        timestamp: Instant,
        plusdmUnitSurrogate: PLUSDMUnitSurrogate
    ) : this(
        timestamp = timestamp,
        plusdm = plusdmUnitSurrogate.plusdm,
    )

    override fun toString(): String {
        return "PLUSDMUnit(timestamp=$timestamp, plusdm=$plusdm)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PLUSDMUnit

        if (timestamp != other.timestamp) return false
        if (plusdm != other.plusdm) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + plusdm.hashCode()
        return result
    }
}

@Serializable
internal class PLUSDMUnitSurrogate(
    @SerialName("PLUS_DM") @Serializable(with = DoubleAsStringSerializer4D::class)
    val plusdm: Double
) {
    constructor(
        plusdmUnit: PLUSDMUnit
    ) : this(
        plusdm = plusdmUnit.plusdm,
    )
}