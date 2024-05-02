package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MACDEXTSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MACDEXTSerializer::class)
public class MACDEXT(
    public val metaData: MACDEXTMetaData,
    public val macdextUnits: List<MACDEXTUnit>
) {
    internal constructor(
        macdextSurrogate: MACDEXTSurrogate
    ) : this(
        metaData = macdextSurrogate.metaData,
        macdextUnits = macdextSurrogate.macdextUnits.map { MACDEXTUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MACDEXT(metaData=$metaData, macdextUnits=$macdextUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MACDEXT

        if (metaData != other.metaData) return false
        if (macdextUnits != other.macdextUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + macdextUnits.hashCode()
        return result
    }
}

@Serializable
internal class MACDEXTSurrogate(
    @SerialName("Meta Data")
    val metaData: MACDEXTMetaData,
    @SerialName("Technical Analysis: MACDEXT")
    val macdextUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MACDEXTUnitSurrogate>
) {
    constructor(
        macdext: MACDEXT
    ) : this(
        metaData = macdext.metaData,
        macdextUnits = macdext.macdextUnits.associate { it.timestamp to MACDEXTUnitSurrogate(it) }
    )
}

@Serializable
public class MACDEXTMetaData(
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
    @SerialName("5.4: Fast MA Type")
    public val fastMAType: Int,
    @SerialName("5.5: Slow MA Type")
    public val slowMAType: Int,
    @SerialName("5.6: Signal MA Type")
    public val signalMAType: Int,
    @SerialName("6: Series Type")
    public val seriesType: String,
    @SerialName("7: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "MACDEXTMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', fastPeriod=$fastPeriod, slowPeriod=$slowPeriod, signalPeriod=$signalPeriod, fastMAType=$fastMAType, slowMAType=$slowMAType, signalMAType=$signalMAType, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MACDEXTMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (fastPeriod != other.fastPeriod) return false
        if (slowPeriod != other.slowPeriod) return false
        if (signalPeriod != other.signalPeriod) return false
        if (fastMAType != other.fastMAType) return false
        if (slowMAType != other.slowMAType) return false
        if (signalMAType != other.signalMAType) return false
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
        result = 31 * result + fastMAType
        result = 31 * result + slowMAType
        result = 31 * result + signalMAType
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class MACDEXTUnit(
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
        macdextUnitSurrogate: MACDEXTUnitSurrogate
    ) : this(
        timestamp = timestamp,
        macd = macdextUnitSurrogate.macd,
        macdSignal = macdextUnitSurrogate.macdSignal,
        macdHist = macdextUnitSurrogate.macdHist
    )

    override fun toString(): String {
        return "MACDEXTUnit(timestamp=$timestamp, macdext=$macd, macdextSignal=$macdSignal, macdextHist=$macdHist)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MACDEXTUnit

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
internal class MACDEXTUnitSurrogate(
    @SerialName("MACD") @Serializable(with = DoubleAsStringSerializer4D::class)
    val macd: Double,
    @SerialName("MACD_Signal") @Serializable(with = DoubleAsStringSerializer4D::class)
    val macdSignal: Double,
    @SerialName("MACD_Hist") @Serializable(with = DoubleAsStringSerializer4D::class)
    val macdHist: Double
) {
    constructor(
        macdextUnit: MACDEXTUnit
    ) : this(
        macd = macdextUnit.macd,
        macdSignal = macdextUnit.macdSignal,
        macdHist = macdextUnit.macdHist
    )
}