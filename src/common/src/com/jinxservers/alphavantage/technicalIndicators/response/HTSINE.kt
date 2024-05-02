package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.HTSINESerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = HTSINESerializer::class)
public class HTSINE(
    public val metaData: HTSINEMetaData,
    public val htsineUnits: List<HTSINEUnit>
) {
    internal constructor(
        htsineSurrogate: HTSINESurrogate
    ) : this(
        metaData = htsineSurrogate.metaData,
        htsineUnits = htsineSurrogate.htsineUnits.map { HTSINEUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "HTSINE(metaData=$metaData, htsineUnits=$htsineUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTSINE

        if (metaData != other.metaData) return false
        if (htsineUnits != other.htsineUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + htsineUnits.hashCode()
        return result
    }
}

@Serializable
internal class HTSINESurrogate(
    @SerialName("Meta Data")
    val metaData: HTSINEMetaData,
    @SerialName("Technical Analysis: HT_SINE")
    val htsineUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, HTSINEUnitSurrogate>
) {
    constructor(
        htsine: HTSINE
    ) : this(
        metaData = htsine.metaData,
        htsineUnits = htsine.htsineUnits.associate { it.timestamp to HTSINEUnitSurrogate(it) }
    )
}

@Serializable
public class HTSINEMetaData(
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
        return "HTSINEMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTSINEMetaData

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
public class HTSINEUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("SINE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val sine: Double,
    @SerialName("LEAD SINE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val leadSine: Double
) {
    internal constructor(
        timestamp: Instant,
        htsineUnitSurrogate: HTSINEUnitSurrogate
    ) : this(
        timestamp = timestamp,
        sine = htsineUnitSurrogate.sine,
        leadSine = htsineUnitSurrogate.leadSine,
    )

    override fun toString(): String {
        return "HTSINEUnit(timestamp=$timestamp, sine=$sine, leadSine=$leadSine)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as HTSINEUnit

        if (timestamp != other.timestamp) return false
        if (sine != other.sine) return false
        if (leadSine != other.leadSine) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + sine.hashCode()
        result = 31 * result + leadSine.hashCode()
        return result
    }
}

@Serializable
internal class HTSINEUnitSurrogate(
    @SerialName("SINE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val sine: Double,
    @SerialName("LEAD SINE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val leadSine: Double
) {
    constructor(
        htsineUnit: HTSINEUnit
    ) : this(
        sine = htsineUnit.sine,
        leadSine = htsineUnit.leadSine,
    )
}