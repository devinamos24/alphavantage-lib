package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.STOCHSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = STOCHSerializer::class)
public class STOCH(
    public val metaData: STOCHMetaData,
    public val stochUnits: List<STOCHUnit>
) {
    internal constructor(
        stochSurrogate: STOCHSurrogate
    ) : this(
        metaData = stochSurrogate.metaData,
        stochUnits = stochSurrogate.stochUnits.map { STOCHUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "STOCH(metaData=$metaData, stochUnits=$stochUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCH

        if (metaData != other.metaData) return false
        if (stochUnits != other.stochUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + stochUnits.hashCode()
        return result
    }
}

@Serializable
internal class STOCHSurrogate(
    @SerialName("Meta Data")
    val metaData: STOCHMetaData,
    @SerialName("Technical Analysis: STOCH")
    val stochUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, STOCHUnitSurrogate>
) {
    constructor(
        stoch: STOCH
    ) : this(
        metaData = stoch.metaData,
        stochUnits = stoch.stochUnits.associate { it.timestamp to STOCHUnitSurrogate(it) }
    )
}

@Serializable
public class STOCHMetaData(
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
    @SerialName("5.2: SlowK Period")
    public val slowKPeriod: Int,
    @SerialName("5.3: SlowK MA Type")
    public val slowKMAType: Int,
    @SerialName("5.4: SlowD Period")
    public val slowDPeriod: Int,
    @SerialName("5.5: SlowD MA Type")
    public val slowDMAType: Int,
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "STOCHMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', fastKPeriod=$fastKPeriod, slowKPeriod=$slowKPeriod, slowKMAType=$slowKMAType, slowDPeriod=$slowDPeriod, slowDMAType=$slowDMAType, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (fastKPeriod != other.fastKPeriod) return false
        if (slowKPeriod != other.slowKPeriod) return false
        if (slowKMAType != other.slowKMAType) return false
        if (slowDPeriod != other.slowDPeriod) return false
        if (slowDMAType != other.slowDMAType) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + fastKPeriod
        result = 31 * result + slowKPeriod
        result = 31 * result + slowKMAType
        result = 31 * result + slowDPeriod
        result = 31 * result + slowDMAType
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class STOCHUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("SlowK") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val slowK: Double,
    @SerialName("SlowD") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val slowD: Double,
) {
    internal constructor(
        timestamp: Instant,
        stochUnitSurrogate: STOCHUnitSurrogate
    ) : this(
        timestamp = timestamp,
        slowK = stochUnitSurrogate.slowK,
        slowD = stochUnitSurrogate.slowD
    )

    override fun toString(): String {
        return "STOCHUnit(timestamp=$timestamp, slowK=$slowK, slowD=$slowD)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as STOCHUnit

        if (timestamp != other.timestamp) return false
        if (slowK != other.slowK) return false
        if (slowD != other.slowD) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + slowK.hashCode()
        result = 31 * result + slowD.hashCode()
        return result
    }
}

@Serializable
internal class STOCHUnitSurrogate(
    @SerialName("SlowK") @Serializable(with = DoubleAsStringSerializer4D::class)
    val slowK: Double,
    @SerialName("SlowD") @Serializable(with = DoubleAsStringSerializer4D::class)
    val slowD: Double,
) {
    constructor(
        stochUnit: STOCHUnit
    ) : this(
        slowK = stochUnit.slowK,
        slowD = stochUnit.slowD
    )
}