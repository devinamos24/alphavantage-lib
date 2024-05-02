package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ROCSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ROCSerializer::class)
public class ROC(
    public val metaData: ROCMetaData,
    public val rocUnits: List<ROCUnit>
) {
    internal constructor(
        rocSurrogate: ROCSurrogate
    ) : this(
        metaData = rocSurrogate.metaData,
        rocUnits = rocSurrogate.rocUnits.map { ROCUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ROC(metaData=$metaData, rocUnits=$rocUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ROC

        if (metaData != other.metaData) return false
        if (rocUnits != other.rocUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + rocUnits.hashCode()
        return result
    }
}

@Serializable
internal class ROCSurrogate(
    @SerialName("Meta Data")
    val metaData: ROCMetaData,
    @SerialName("Technical Analysis: ROC")
    val rocUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ROCUnitSurrogate>
) {
    constructor(
        roc: ROC
    ) : this(
        metaData = roc.metaData,
        rocUnits = roc.rocUnits.associate { it.timestamp to ROCUnitSurrogate(it) }
    )
}

@Serializable
public class ROCMetaData(
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
    @SerialName("6: Series Type")
    public val seriesType: String,
    @SerialName("7: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "ROCMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ROCMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
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
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class ROCUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("ROC") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val roc: Double
) {
    internal constructor(
        timestamp: Instant,
        rocUnitSurrogate: ROCUnitSurrogate
    ) : this(
        timestamp = timestamp,
        roc = rocUnitSurrogate.roc,
    )

    override fun toString(): String {
        return "ROCUnit(timestamp=$timestamp, roc=$roc)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ROCUnit

        if (timestamp != other.timestamp) return false
        if (roc != other.roc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + roc.hashCode()
        return result
    }
}

@Serializable
internal class ROCUnitSurrogate(
    @SerialName("ROC") @Serializable(with = DoubleAsStringSerializer4D::class)
    val roc: Double
) {
    constructor(
        rocUnit: ROCUnit
    ) : this(
        roc = rocUnit.roc,
    )
}