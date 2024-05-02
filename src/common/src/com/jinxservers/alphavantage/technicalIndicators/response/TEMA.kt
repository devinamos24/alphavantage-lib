package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.TEMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TEMASerializer::class)
public class TEMA(
    public val metaData: TEMAMetaData,
    public val temaUnits: List<TEMAUnit>
) {
    internal constructor(
        temaSurrogate: TEMASurrogate
    ) : this(
        metaData = temaSurrogate.metaData,
        temaUnits = temaSurrogate.temaUnits.map { TEMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "TEMA(metaData=$metaData, temaUnits=$temaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TEMA

        if (metaData != other.metaData) return false
        if (temaUnits != other.temaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + temaUnits.hashCode()
        return result
    }
}

@Serializable
internal class TEMASurrogate(
    @SerialName("Meta Data")
    val metaData: TEMAMetaData,
    @SerialName("Technical Analysis: TEMA")
    val temaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, TEMAUnitSurrogate>
) {
    constructor(
        tema: TEMA
    ) : this(
        metaData = tema.metaData,
        temaUnits = tema.temaUnits.associate { it.timestamp to TEMAUnitSurrogate(it) }
    )
}

@Serializable
public class TEMAMetaData(
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
        return "TEMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TEMAMetaData

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
public class TEMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("TEMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val tema: Double
) {
    internal constructor(
        timestamp: Instant,
        temaUnitSurrogate: TEMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        tema = temaUnitSurrogate.tema,
    )

    override fun toString(): String {
        return "TEMAUnit(timestamp=$timestamp, tema=$tema)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TEMAUnit

        if (timestamp != other.timestamp) return false
        if (tema != other.tema) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + tema.hashCode()
        return result
    }
}

@Serializable
internal class TEMAUnitSurrogate(
    @SerialName("TEMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val tema: Double
) {
    constructor(
        temaUnit: TEMAUnit
    ) : this(
        tema = temaUnit.tema,
    )
}