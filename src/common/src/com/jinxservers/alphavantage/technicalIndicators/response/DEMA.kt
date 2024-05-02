package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.DEMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = DEMASerializer::class)
public class DEMA(
    public val metaData: DEMAMetaData,
    public val demaUnits: List<DEMAUnit>
) {
    internal constructor(
        demaSurrogate: DEMASurrogate
    ) : this(
        metaData = demaSurrogate.metaData,
        demaUnits = demaSurrogate.demaUnits.map { DEMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "DEMA(metaData=$metaData, demaUnits=$demaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DEMA

        if (metaData != other.metaData) return false
        if (demaUnits != other.demaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + demaUnits.hashCode()
        return result
    }
}

@Serializable
internal class DEMASurrogate(
    @SerialName("Meta Data")
    val metaData: DEMAMetaData,
    @SerialName("Technical Analysis: DEMA")
    val demaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, DEMAUnitSurrogate>
) {
    constructor(
        dema: DEMA
    ) : this(
        metaData = dema.metaData,
        demaUnits = dema.demaUnits.associate { it.timestamp to DEMAUnitSurrogate(it) }
    )
}

@Serializable
public class DEMAMetaData(
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
        return "DEMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DEMAMetaData

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
public class DEMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("DEMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val dema: Double
) {
    internal constructor(
        timestamp: Instant,
        demaUnitSurrogate: DEMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        dema = demaUnitSurrogate.dema,
    )

    override fun toString(): String {
        return "DEMAUnit(timestamp=$timestamp, dema=$dema)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DEMAUnit

        if (timestamp != other.timestamp) return false
        if (dema != other.dema) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + dema.hashCode()
        return result
    }
}

@Serializable
internal class DEMAUnitSurrogate(
    @SerialName("DEMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val dema: Double
) {
    constructor(
        demaUnit: DEMAUnit
    ) : this(
        dema = demaUnit.dema,
    )
}