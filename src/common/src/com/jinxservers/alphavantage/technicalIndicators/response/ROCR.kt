package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.ROCRSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ROCRSerializer::class)
public class ROCR(
    public val metaData: ROCRMetaData,
    public val rocrUnits: List<ROCRUnit>
) {
    internal constructor(
        rocrSurrogate: ROCRSurrogate
    ) : this(
        metaData = rocrSurrogate.metaData,
        rocrUnits = rocrSurrogate.rocrUnits.map { ROCRUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "ROCR(metaData=$metaData, rocrUnits=$rocrUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ROCR

        if (metaData != other.metaData) return false
        if (rocrUnits != other.rocrUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + rocrUnits.hashCode()
        return result
    }
}

@Serializable
internal class ROCRSurrogate(
    @SerialName("Meta Data")
    val metaData: ROCRMetaData,
    @SerialName("Technical Analysis: ROCR")
    val rocrUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, ROCRUnitSurrogate>
) {
    constructor(
        rocr: ROCR
    ) : this(
        metaData = rocr.metaData,
        rocrUnits = rocr.rocrUnits.associate { it.timestamp to ROCRUnitSurrogate(it) }
    )
}

@Serializable
public class ROCRMetaData(
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
        return "ROCRMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ROCRMetaData

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
public class ROCRUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("ROCR") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val rocr: Double
) {
    internal constructor(
        timestamp: Instant,
        rocrUnitSurrogate: ROCRUnitSurrogate
    ) : this(
        timestamp = timestamp,
        rocr = rocrUnitSurrogate.rocr,
    )

    override fun toString(): String {
        return "ROCRUnit(timestamp=$timestamp, rocr=$rocr)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ROCRUnit

        if (timestamp != other.timestamp) return false
        if (rocr != other.rocr) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + rocr.hashCode()
        return result
    }
}

@Serializable
internal class ROCRUnitSurrogate(
    @SerialName("ROCR") @Serializable(with = DoubleAsStringSerializer4D::class)
    val rocr: Double
) {
    constructor(
        rocrUnit: ROCRUnit
    ) : this(
        rocr = rocrUnit.rocr,
    )
}