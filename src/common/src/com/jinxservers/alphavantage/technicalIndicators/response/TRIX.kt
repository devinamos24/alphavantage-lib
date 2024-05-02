package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.TRIXSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TRIXSerializer::class)
public class TRIX(
    public val metaData: TRIXMetaData,
    public val trixUnits: List<TRIXUnit>
) {
    internal constructor(
        trixSurrogate: TRIXSurrogate
    ) : this(
        metaData = trixSurrogate.metaData,
        trixUnits = trixSurrogate.trixUnits.map { TRIXUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "TRIX(metaData=$metaData, trixUnits=$trixUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRIX

        if (metaData != other.metaData) return false
        if (trixUnits != other.trixUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + trixUnits.hashCode()
        return result
    }
}

@Serializable
internal class TRIXSurrogate(
    @SerialName("Meta Data")
    val metaData: TRIXMetaData,
    @SerialName("Technical Analysis: TRIX")
    val trixUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, TRIXUnitSurrogate>
) {
    constructor(
        trix: TRIX
    ) : this(
        metaData = trix.metaData,
        trixUnits = trix.trixUnits.associate { it.timestamp to TRIXUnitSurrogate(it) }
    )
}

@Serializable
public class TRIXMetaData(
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
        return "TRIXMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRIXMetaData

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
public class TRIXUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("TRIX") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val trix: Double
) {
    internal constructor(
        timestamp: Instant,
        trixUnitSurrogate: TRIXUnitSurrogate
    ) : this(
        timestamp = timestamp,
        trix = trixUnitSurrogate.trix,
    )

    override fun toString(): String {
        return "TRIXUnit(timestamp=$timestamp, trix=$trix)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRIXUnit

        if (timestamp != other.timestamp) return false
        if (trix != other.trix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + trix.hashCode()
        return result
    }
}

@Serializable
internal class TRIXUnitSurrogate(
    @SerialName("TRIX") @Serializable(with = DoubleAsStringSerializer4D::class)
    val trix: Double
) {
    constructor(
        trixUnit: TRIXUnit
    ) : this(
        trix = trixUnit.trix,
    )
}