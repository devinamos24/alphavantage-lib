package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.TRIMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TRIMASerializer::class)
public class TRIMA(
    public val metaData: TRIMAMetaData,
    public val trimaUnits: List<TRIMAUnit>
) {
    internal constructor(
        trimaSurrogate: TRIMASurrogate
    ) : this(
        metaData = trimaSurrogate.metaData,
        trimaUnits = trimaSurrogate.trimaUnits.map { TRIMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "TRIMA(metaData=$metaData, trimaUnits=$trimaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRIMA

        if (metaData != other.metaData) return false
        if (trimaUnits != other.trimaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + trimaUnits.hashCode()
        return result
    }
}

@Serializable
internal class TRIMASurrogate(
    @SerialName("Meta Data")
    val metaData: TRIMAMetaData,
    @SerialName("Technical Analysis: TRIMA")
    val trimaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, TRIMAUnitSurrogate>
) {
    constructor(
        trima: TRIMA
    ) : this(
        metaData = trima.metaData,
        trimaUnits = trima.trimaUnits.associate { it.timestamp to TRIMAUnitSurrogate(it) }
    )
}

@Serializable
public class TRIMAMetaData(
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
        return "TRIMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRIMAMetaData

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
public class TRIMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("TRIMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val trima: Double
) {
    internal constructor(
        timestamp: Instant,
        trimaUnitSurrogate: TRIMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        trima = trimaUnitSurrogate.trima,
    )

    override fun toString(): String {
        return "TRIMAUnit(timestamp=$timestamp, trima=$trima)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TRIMAUnit

        if (timestamp != other.timestamp) return false
        if (trima != other.trima) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + trima.hashCode()
        return result
    }
}

@Serializable
internal class TRIMAUnitSurrogate(
    @SerialName("TRIMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val trima: Double
) {
    constructor(
        trimaUnit: TRIMAUnit
    ) : this(
        trima = trimaUnit.trima,
    )
}