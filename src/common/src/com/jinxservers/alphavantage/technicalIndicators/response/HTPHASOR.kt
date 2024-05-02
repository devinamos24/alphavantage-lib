package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.HTPHASORSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = HTPHASORSerializer::class)
public class HTPHASOR(
    public val metaData: HTPHASORMetaData,
    public val htphasorUnits: List<HTPHASORUnit>
) {
    internal constructor(
        htphasorSurrogate: HTPHASORSurrogate
    ) : this(
        metaData = htphasorSurrogate.metaData,
        htphasorUnits = htphasorSurrogate.htphasorUnits.map { HTPHASORUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "HTPHASOR(metaData=$metaData, htphasorUnits=$htphasorUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTPHASOR

        if (metaData != other.metaData) return false
        if (htphasorUnits != other.htphasorUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + htphasorUnits.hashCode()
        return result
    }
}

@Serializable
internal class HTPHASORSurrogate(
    @SerialName("Meta Data")
    val metaData: HTPHASORMetaData,
    @SerialName("Technical Analysis: HT_PHASOR")
    val htphasorUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, HTPHASORUnitSurrogate>
) {
    constructor(
        htphasor: HTPHASOR
    ) : this(
        metaData = htphasor.metaData,
        htphasorUnits = htphasor.htphasorUnits.associate { it.timestamp to HTPHASORUnitSurrogate(it) }
    )
}

@Serializable
public class HTPHASORMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5: Series Type")
    public val seriesType: String,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "HTPHASORMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTPHASORMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class HTPHASORUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("PHASE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val phase: Double,
    @SerialName("QUADRATURE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val quadrature: Double
) {
    internal constructor(
        timestamp: Instant,
        htphasorUnitSurrogate: HTPHASORUnitSurrogate
    ) : this(
        timestamp = timestamp,
        phase = htphasorUnitSurrogate.phase,
        quadrature = htphasorUnitSurrogate.quadrature,
    )

    override fun toString(): String {
        return "HTPHASORUnit(timestamp=$timestamp, phase=$phase, quadrature=$quadrature)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTPHASORUnit

        if (timestamp != other.timestamp) return false
        if (phase != other.phase) return false
        if (quadrature != other.quadrature) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + phase.hashCode()
        result = 31 * result + quadrature.hashCode()
        return result
    }
}

@Serializable
internal class HTPHASORUnitSurrogate(
    @SerialName("PHASE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val phase: Double,
    @SerialName("QUADRATURE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val quadrature: Double
) {
    constructor(
        htphasorUnit: HTPHASORUnit
    ) : this(
        phase = htphasorUnit.phase,
        quadrature = htphasorUnit.quadrature,
    )
}