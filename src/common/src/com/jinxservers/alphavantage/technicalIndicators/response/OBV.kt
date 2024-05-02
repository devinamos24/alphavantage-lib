package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.OBVSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = OBVSerializer::class)
public class OBV(
    public val metaData: OBVMetaData,
    public val obvUnits: List<OBVUnit>
) {
    internal constructor(
        obvSurrogate: OBVSurrogate
    ) : this(
        metaData = obvSurrogate.metaData,
        obvUnits = obvSurrogate.obvUnits.map { OBVUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "OBV(metaData=$metaData, obvUnits=$obvUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as OBV

        if (metaData != other.metaData) return false
        if (obvUnits != other.obvUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + obvUnits.hashCode()
        return result
    }
}

@Serializable
internal class OBVSurrogate(
    @SerialName("Meta Data")
    val metaData: OBVMetaData,
    @SerialName("Technical Analysis: OBV")
    val obvUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, OBVUnitSurrogate>
) {
    constructor(
        obv: OBV
    ) : this(
        metaData = obv.metaData,
        obvUnits = obv.obvUnits.associate { it.timestamp to OBVUnitSurrogate(it) }
    )
}

@Serializable
public class OBVMetaData(
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
        return "OBVMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as OBVMetaData

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
public class OBVUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("OBV") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val obv: Double
) {
    internal constructor(
        timestamp: Instant,
        obvUnitSurrogate: OBVUnitSurrogate
    ) : this(
        timestamp = timestamp,
        obv = obvUnitSurrogate.obv,
    )

    override fun toString(): String {
        return "OBVUnit(timestamp=$timestamp, obv=$obv)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as OBVUnit

        if (timestamp != other.timestamp) return false
        if (obv != other.obv) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + obv.hashCode()
        return result
    }
}

@Serializable
internal class OBVUnitSurrogate(
    @SerialName("OBV") @Serializable(with = DoubleAsStringSerializer4D::class)
    val obv: Double
) {
    constructor(
        obvUnit: OBVUnit
    ) : this(
        obv = obvUnit.obv,
    )
}