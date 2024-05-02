package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.KAMASerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = KAMASerializer::class)
public class KAMA(
    public val metaData: KAMAMetaData,
    public val kamaUnits: List<KAMAUnit>
) {
    internal constructor(
        kamaSurrogate: KAMASurrogate
    ) : this(
        metaData = kamaSurrogate.metaData,
        kamaUnits = kamaSurrogate.kamaUnits.map { KAMAUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "KAMA(metaData=$metaData, kamaUnits=$kamaUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as KAMA

        if (metaData != other.metaData) return false
        if (kamaUnits != other.kamaUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + kamaUnits.hashCode()
        return result
    }
}

@Serializable
internal class KAMASurrogate(
    @SerialName("Meta Data")
    val metaData: KAMAMetaData,
    @SerialName("Technical Analysis: KAMA")
    val kamaUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, KAMAUnitSurrogate>
) {
    constructor(
        kama: KAMA
    ) : this(
        metaData = kama.metaData,
        kamaUnits = kama.kamaUnits.associate { it.timestamp to KAMAUnitSurrogate(it) }
    )
}

@Serializable
public class KAMAMetaData(
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
        return "KAMAMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, seriesType='$seriesType', timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as KAMAMetaData

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
public class KAMAUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("KAMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val kama: Double
) {
    internal constructor(
        timestamp: Instant,
        kamaUnitSurrogate: KAMAUnitSurrogate
    ) : this(
        timestamp = timestamp,
        kama = kamaUnitSurrogate.kama,
    )

    override fun toString(): String {
        return "KAMAUnit(timestamp=$timestamp, kama=$kama)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as KAMAUnit

        if (timestamp != other.timestamp) return false
        if (kama != other.kama) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + kama.hashCode()
        return result
    }
}

@Serializable
internal class KAMAUnitSurrogate(
    @SerialName("KAMA") @Serializable(with = DoubleAsStringSerializer4D::class)
    val kama: Double
) {
    constructor(
        kamaUnit: KAMAUnit
    ) : this(
        kama = kamaUnit.kama,
    )
}