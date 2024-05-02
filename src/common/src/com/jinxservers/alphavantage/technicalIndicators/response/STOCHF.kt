package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.STOCHFSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = STOCHFSerializer::class)
public class STOCHF(
    public val metaData: STOCHFMetaData,
    public val stochfUnits: List<STOCHFUnit>
) {
    internal constructor(
        stochfSurrogate: STOCHFSurrogate
    ) : this(
        metaData = stochfSurrogate.metaData,
        stochfUnits = stochfSurrogate.stochfUnits.map { STOCHFUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "STOCHF(metaData=$metaData, stochfUnits=$stochfUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHF

        if (metaData != other.metaData) return false
        if (stochfUnits != other.stochfUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + stochfUnits.hashCode()
        return result
    }
}

@Serializable
internal class STOCHFSurrogate(
    @SerialName("Meta Data")
    val metaData: STOCHFMetaData,
    @SerialName("Technical Analysis: STOCHF")
    val stochfUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, STOCHFUnitSurrogate>
) {
    constructor(
        stochf: STOCHF
    ) : this(
        metaData = stochf.metaData,
        stochfUnits = stochf.stochfUnits.associate { it.timestamp to STOCHFUnitSurrogate(it) }
    )
}

@Serializable
public class STOCHFMetaData(
    @SerialName("1: Symbol")
    public val symbol: String,
    @SerialName("2: Indicator")
    public val indicator: String,
    @SerialName("3: Last Refreshed") @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("4: Interval")
    public val interval: String,
    @SerialName("5.1: FastK Period")
    public val fastKPeriod: Int,
    @SerialName("5.2: FastD Period")
    public val fastDPeriod: Int,
    @SerialName("5.3: FastD MA Type")
    public val fastDMAType: Int,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "STOCHFMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', fastKPeriod=$fastKPeriod, fastDPeriod=$fastDPeriod, fastDMAType=$fastDMAType, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHFMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (fastKPeriod != other.fastKPeriod) return false
        if (fastDPeriod != other.fastDPeriod) return false
        if (fastDMAType != other.fastDMAType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + fastKPeriod
        result = 31 * result + fastDPeriod
        result = 31 * result + fastDMAType
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class STOCHFUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("FastK") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val fastK: Double,
    @SerialName("FastD") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val fastD: Double,
) {
    internal constructor(
        timestamp: Instant,
        stochfUnitSurrogate: STOCHFUnitSurrogate
    ) : this(
        timestamp = timestamp,
        fastK = stochfUnitSurrogate.fastK,
        fastD = stochfUnitSurrogate.fastD
    )

    override fun toString(): String {
        return "STOCHFUnit(timestamp=$timestamp, slowK=$fastK, slowD=$fastD)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHFUnit

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
internal class STOCHFUnitSurrogate(
    @SerialName("FastK") @Serializable(with = DoubleAsStringSerializer4D::class)
    val fastK: Double,
    @SerialName("FastD") @Serializable(with = DoubleAsStringSerializer4D::class)
    val fastD: Double,
) {
    constructor(
        stochfUnit: STOCHFUnit
    ) : this(
        fastK = stochfUnit.fastK,
        fastD = stochfUnit.fastD
    )
}