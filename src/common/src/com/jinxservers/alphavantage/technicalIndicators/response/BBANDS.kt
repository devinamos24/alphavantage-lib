package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.BBANDSSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = BBANDSSerializer::class)
public class BBANDS(
    public val metaData: BBANDSMetaData,
    public val bbandsUnits: List<BBANDSUnit>
) {
    internal constructor(
        bbandsSurrogate: BBANDSSurrogate
    ) : this(
        metaData = bbandsSurrogate.metaData,
        bbandsUnits = bbandsSurrogate.bbandsUnits.map { BBANDSUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "BBANDS(metaData=$metaData, bbandsUnits=$bbandsUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BBANDS

        if (metaData != other.metaData) return false
        if (bbandsUnits != other.bbandsUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + bbandsUnits.hashCode()
        return result
    }
}

@Serializable
internal class BBANDSSurrogate(
    @SerialName("Meta Data")
    val metaData: BBANDSMetaData,
    @SerialName("Technical Analysis: BBANDS")
    val bbandsUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, BBANDSUnitSurrogate>
) {
    constructor(
        bbands: BBANDS
    ) : this(
        metaData = bbands.metaData,
        bbandsUnits = bbands.bbandsUnits.associate { it.timestamp to BBANDSUnitSurrogate(it) }
    )
}

@Serializable
public class BBANDSMetaData(
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
    @SerialName("6.1: Deviation multiplier for upper band")
    public val upperBandDeviationMultiplier: Int,
    @SerialName("6.2: Deviation multiplier for lower band")
    public val lowerBandDeviationMultiplier: Int,
    @SerialName("6.3: MA Type")
    public val maType: Int,
    @SerialName("7: Series Type")
    public val seriesType: String,
    @SerialName("8: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "BBANDSMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, upperBandDeviationMultiplier=$upperBandDeviationMultiplier, lowerBandDeviationMultiplier=$lowerBandDeviationMultiplier, maType=$maType, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BBANDSMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (upperBandDeviationMultiplier != other.upperBandDeviationMultiplier) return false
        if (lowerBandDeviationMultiplier != other.lowerBandDeviationMultiplier) return false
        if (maType != other.maType) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timePeriod
        result = 31 * result + upperBandDeviationMultiplier
        result = 31 * result + lowerBandDeviationMultiplier
        result = 31 * result + maType
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class BBANDSUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("Real Upper Band") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val realUpperBand: Double,
    @SerialName("Real Middle Band") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val realMiddleBand: Double,
    @SerialName("Real Lower Band") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val realLowerBand: Double
) {
    internal constructor(
        timestamp: Instant,
        bbandsUnitSurrogate: BBANDSUnitSurrogate
    ) : this(
        timestamp = timestamp,
        realUpperBand = bbandsUnitSurrogate.realUpperBand,
        realMiddleBand = bbandsUnitSurrogate.realMiddleBand,
        realLowerBand = bbandsUnitSurrogate.realLowerBand,
    )

    override fun toString(): String {
        return "BBANDSUnit(timestamp=$timestamp, realUpperBand=$realUpperBand, realMiddleBand=$realMiddleBand, realLowerBand=$realLowerBand)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BBANDSUnit

        if (timestamp != other.timestamp) return false
        if (realUpperBand != other.realUpperBand) return false
        if (realMiddleBand != other.realMiddleBand) return false
        if (realLowerBand != other.realLowerBand) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + realUpperBand.hashCode()
        result = 31 * result + realMiddleBand.hashCode()
        result = 31 * result + realLowerBand.hashCode()
        return result
    }
}

@Serializable
internal class BBANDSUnitSurrogate(
    @SerialName("Real Upper Band") @Serializable(with = DoubleAsStringSerializer4D::class)
    val realUpperBand: Double,
    @SerialName("Real Middle Band") @Serializable(with = DoubleAsStringSerializer4D::class)
    val realMiddleBand: Double,
    @SerialName("Real Lower Band") @Serializable(with = DoubleAsStringSerializer4D::class)
    val realLowerBand: Double
) {
    constructor(
        bbandsUnit: BBANDSUnit
    ) : this(
        realUpperBand = bbandsUnit.realUpperBand,
        realMiddleBand = bbandsUnit.realMiddleBand,
        realLowerBand = bbandsUnit.realLowerBand
    )
}