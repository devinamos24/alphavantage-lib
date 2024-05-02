package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.T3Serializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = T3Serializer::class)
public class T3(
    public val metaData: T3MetaData,
    public val t3Units: List<T3Unit>
) {
    internal constructor(
        t3Surrogate: T3Surrogate
    ) : this(
        metaData = t3Surrogate.metaData,
        t3Units = t3Surrogate.t3Units.map { T3Unit(it.key, it.value) }
    )

    override fun toString(): String {
        return "T3(metaData=$metaData, t3Units=$t3Units)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as T3

        if (metaData != other.metaData) return false
        if (t3Units != other.t3Units) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + t3Units.hashCode()
        return result
    }
}

@Serializable
internal class T3Surrogate(
    @SerialName("Meta Data")
    val metaData: T3MetaData,
    @SerialName("Technical Analysis: T3")
    val t3Units: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, T3UnitSurrogate>
) {
    constructor(
        t3: T3
    ) : this(
        metaData = t3.metaData,
        t3Units = t3.t3Units.associate { it.timestamp to T3UnitSurrogate(it) }
    )
}

@Serializable
public class T3MetaData(
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
    @SerialName("6: Volume Factor (vFactor)")
    public val volumeFactor: Double,
    @SerialName("7: Series Type")
    public val seriesType: String,
    @SerialName("8: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "T3MetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, volumeFactor=$volumeFactor, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as T3MetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (volumeFactor != other.volumeFactor) return false
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
        result = 31 * result + volumeFactor.hashCode()
        result = 31 * result + seriesType.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class T3Unit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("T3") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val t3: Double
) {
    internal constructor(
        timestamp: Instant,
        t3UnitSurrogate: T3UnitSurrogate
    ) : this(
        timestamp = timestamp,
        t3 = t3UnitSurrogate.t3,
    )

    override fun toString(): String {
        return "T3Unit(timestamp=$timestamp, t3=$t3)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as T3Unit

        if (timestamp != other.timestamp) return false
        if (t3 != other.t3) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + t3.hashCode()
        return result
    }
}

@Serializable
internal class T3UnitSurrogate(
    @SerialName("T3") @Serializable(with = DoubleAsStringSerializer4D::class)
    val t3: Double
) {
    constructor(
        t3Unit: T3Unit
    ) : this(
        t3 = t3Unit.t3,
    )
}