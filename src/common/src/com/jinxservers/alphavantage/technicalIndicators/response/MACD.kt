package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MACDSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MACDSerializer::class)
public class MACD(
    public val metaData: MACDMetaData,
    public val macdUnits: List<MACDUnit>
) {
    internal constructor(
        macdSurrogate: MACDSurrogate
    ) : this(
        metaData = macdSurrogate.metaData,
        macdUnits = macdSurrogate.macdUnits.map { MACDUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MACD(metaData=$metaData, macdUnits=$macdUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MACD

        if (metaData != other.metaData) return false
        if (macdUnits != other.macdUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + macdUnits.hashCode()
        return result
    }
}

@Serializable
internal class MACDSurrogate(
    @SerialName("Meta Data")
    val metaData: MACDMetaData,
    @SerialName("Technical Analysis: MACD")
    val macdUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MACDUnitSurrogate>
) {
    constructor(
        macd: MACD
    ) : this(
        metaData = macd.metaData,
        macdUnits = macd.macdUnits.associate { it.timestamp to MACDUnitSurrogate(it) }
    )
}

@Serializable
public class MACDMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5.1: Fast Period")
    public val fastPeriod: Int,
    @SerialName("5.2: Slow Period")
    public val slowPeriod: Int,
    @SerialName("5.3: Signal Period")
    public val signalPeriod: Int,
    @SerialName("6: Series Type")
    public val seriesType: String,
    @SerialName("7: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "MACDMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', fastPeriod=$fastPeriod, slowPeriod=$slowPeriod, signalPeriod=$signalPeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MACDMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (fastPeriod != other.fastPeriod) return false
        if (slowPeriod != other.slowPeriod) return false
        if (signalPeriod != other.signalPeriod) return false
        if (seriesType != other.seriesType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + fastPeriod
        result = 31 * result + slowPeriod
        result = 31 * result + signalPeriod
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class MACDUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MACD") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val macd: Double,
    @SerialName("MACD_Signal") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val macdSignal: Double,
    @SerialName("MACD_Hist") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val macdHist: Double
) {
    internal constructor(
        timestamp: Instant,
        macdUnitSurrogate: MACDUnitSurrogate
    ) : this(
        timestamp = timestamp,
        macd = macdUnitSurrogate.macd,
        macdSignal = macdUnitSurrogate.macdSignal,
        macdHist = macdUnitSurrogate.macdHist
    )

    override fun toString(): String {
        return "MACDUnit(timestamp=$timestamp, macd=$macd, macdSignal=$macdSignal, macdHist=$macdHist)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MACDUnit

        if (timestamp != other.timestamp) return false
        if (macd != other.macd) return false
        if (macdSignal != other.macdSignal) return false
        if (macdHist != other.macdHist) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + macd.hashCode()
        result = 31 * result + macdSignal.hashCode()
        result = 31 * result + macdHist.hashCode()
        return result
    }
}

@Serializable
internal class MACDUnitSurrogate(
    @SerialName("MACD") @Serializable(with = DoubleAsStringSerializer4D::class)
    val macd: Double,
    @SerialName("MACD_Signal") @Serializable(with = DoubleAsStringSerializer4D::class)
    val macdSignal: Double,
    @SerialName("MACD_Hist") @Serializable(with = DoubleAsStringSerializer4D::class)
    val macdHist: Double
) {
    constructor(
        macdUnit: MACDUnit
    ) : this(
        macd = macdUnit.macd,
        macdSignal = macdUnit.macdSignal,
        macdHist = macdUnit.macdHist
    )
}