package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MAMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MAMASerializer::class)
public class MAMA(
    public val metaData: MAMAMetaData,
    public val mamaUnits: List<MAMAUnit>
) {
    internal constructor(
        mamaSurrogate: MAMASurrogate
    ) : this(
        metaData = mamaSurrogate.metaData,
        mamaUnits = mamaSurrogate.mamaUnits.map { MAMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MAMA(metaData=$metaData, mamaUnits=$mamaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MAMA

        if (metaData != other.metaData) return false
        if (mamaUnits != other.mamaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + mamaUnits.hashCode()
        return result
    }
}

@Serializable
internal class MAMASurrogate(
    @SerialName("Meta Data")
    val metaData: MAMAMetaData,
    @SerialName("Technical Analysis: MAMA")
    val mamaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MAMAUnitSurrogate>
) {
    constructor(
        mama: MAMA
    ) : this(
        metaData = mama.metaData,
        mamaUnits = mama.mamaUnits.associate { it.timestamp to MAMAUnitSurrogate(it) }
    )
}

@Serializable
public class MAMAMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5.1: Fast Limit")
    public val fastLimit: Double,
    @SerialName("5.2: Slow Limit")
    public val slowLimit: Double,
    @SerialName("6: Series Type")
    public val seriesType: String,
    @SerialName("7: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "MAMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', fastLimit=$fastLimit, slowLimit=$slowLimit, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MAMAMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (fastLimit != other.fastLimit) return false
        if (slowLimit != other.slowLimit) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + fastLimit.hashCode()
        result = 31 * result + slowLimit.hashCode()
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class MAMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MAMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val mama: Double,
    @SerialName("FAMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val fama: Double
) {
    internal constructor(
        timestamp: Instant,
        mamaUnitSurrogate: MAMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        mama = mamaUnitSurrogate.mama,
        fama = mamaUnitSurrogate.fama
    )

    override fun toString(): String {
        return "MAMAUnit(timestamp=$timestamp, mama=$mama, fama=$fama)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MAMAUnit

        if (timestamp != other.timestamp) return false
        if (mama != other.mama) return false
        if (fama != other.fama) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + mama.hashCode()
        result = 31 * result + fama.hashCode()
        return result
    }
}

@Serializable
internal class MAMAUnitSurrogate(
    @SerialName("MAMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val mama: Double,
    @SerialName("FAMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val fama: Double
) {
    constructor(
        mamaUnit: MAMAUnit
    ) : this(
        mama = mamaUnit.mama,
        fama = mamaUnit.fama
    )
}