package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.STOCHRSISerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = STOCHRSISerializer::class)
public class STOCHRSI(
    public val metaData: STOCHRSIMetaData,
    public val stochrsiUnits: List<STOCHRSIUnit>
) {
    internal constructor(
        stochrsiSurrogate: STOCHRSISurrogate
    ) : this(
        metaData = stochrsiSurrogate.metaData,
        stochrsiUnits = stochrsiSurrogate.stochrsiUnits.map { STOCHRSIUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "STOCHRSI(metaData=$metaData, stochrsiUnits=$stochrsiUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHRSI

        if (metaData != other.metaData) return false
        if (stochrsiUnits != other.stochrsiUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + stochrsiUnits.hashCode()
        return result
    }
}

@Serializable
internal class STOCHRSISurrogate(
    @SerialName("Meta Data")
    val metaData: STOCHRSIMetaData,
    @SerialName("Technical Analysis: STOCHRSI")
    val stochrsiUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, STOCHRSIUnitSurrogate>
) {
    constructor(
        stochrsi: STOCHRSI
    ) : this(
        metaData = stochrsi.metaData,
        stochrsiUnits = stochrsi.stochrsiUnits.associate { it.timestamp to STOCHRSIUnitSurrogate(it) }
    )
}

@Serializable
public class STOCHRSIMetaData(
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
    @SerialName("6.1: FastK Period")
    public val fastKPeriod: Int,
    @SerialName("6.2: FastD Period")
    public val fastDPeriod: Int,
    @SerialName("6.3: FastD MA Type")
    public val fastDMAType: Int,
    @SerialName("7: Series Type")
    public val seriesType: String,
    @SerialName("8: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "STOCHRSIMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, fastKPeriod=$fastKPeriod, fastDPeriod=$fastDPeriod, fastDMAType=$fastDMAType, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHRSIMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (fastKPeriod != other.fastKPeriod) return false
        if (fastDPeriod != other.fastDPeriod) return false
        if (fastDMAType != other.fastDMAType) return false
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
        result = 31 * result + fastKPeriod
        result = 31 * result + fastDPeriod
        result = 31 * result + fastDMAType
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class STOCHRSIUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("FastK") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val fastK: Double,
    @SerialName("FastD") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val fastD: Double,
) {
    internal constructor(
        timestamp: Instant,
        stochrsiUnitSurrogate: STOCHRSIUnitSurrogate
    ) : this(
        timestamp = timestamp,
        fastK = stochrsiUnitSurrogate.fastK,
        fastD = stochrsiUnitSurrogate.fastD
    )

    override fun toString(): String {
        return "STOCHRSIUnit(timestamp=$timestamp, slowK=$fastK, slowD=$fastD)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHRSIUnit

        if (timestamp != other.timestamp) return false
        if (fastK != other.fastK) return false
        if (fastD != other.fastD) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + fastK.hashCode()
        result = 31 * result + fastD.hashCode()
        return result
    }
}

@Serializable
internal class STOCHRSIUnitSurrogate(
    @SerialName("FastK") @Serializable(with = DoubleAsStringSerializer4D::class)
    val fastK: Double,
    @SerialName("FastD") @Serializable(with = DoubleAsStringSerializer4D::class)
    val fastD: Double,
) {
    constructor(
        stochrsiUnit: STOCHRSIUnit
    ) : this(
        fastK = stochrsiUnit.fastK,
        fastD = stochrsiUnit.fastD
    )
}