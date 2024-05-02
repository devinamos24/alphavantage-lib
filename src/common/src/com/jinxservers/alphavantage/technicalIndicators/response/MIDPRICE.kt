package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.technicalIndicators.MIDPRICESerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MIDPRICESerializer::class)
public class MIDPRICE(
    public val metaData: MIDPRICEMetaData,
    public val midpriceUnits: List<MIDPRICEUnit>
) {
    internal constructor(
        midpriceSurrogate: MIDPRICESurrogate
    ) : this(
        metaData = midpriceSurrogate.metaData,
        midpriceUnits = midpriceSurrogate.midpriceUnits.map { MIDPRICEUnit(it.key, it.value) }
    )

    override fun toString(): String {
        return "MIDPRICE(metaData=$metaData, midpriceUnits=$midpriceUnits)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MIDPRICE

        if (metaData != other.metaData) return false
        if (midpriceUnits != other.midpriceUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + midpriceUnits.hashCode()
        return result
    }
}

@Serializable
internal class MIDPRICESurrogate(
    @SerialName("Meta Data")
    val metaData: MIDPRICEMetaData,
    @SerialName("Technical Analysis: MIDPRICE")
    val midpriceUnits: Map<@Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class) Instant, MIDPRICEUnitSurrogate>
) {
    constructor(
        midprice: MIDPRICE
    ) : this(
        metaData = midprice.metaData,
        midpriceUnits = midprice.midpriceUnits.associate { it.timestamp to MIDPRICEUnitSurrogate(it) }
    )
}

@Serializable
public class MIDPRICEMetaData(
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
    @SerialName("6: Time Zone")
    public val timeZone: String
) {
    override fun toString(): String {
        return "MIDPRICEMetaData(symbol='$symbol', indicator='$indicator', lastRefreshed=$lastRefreshed, interval='$interval', timePeriod=$timePeriod, timeZone='$timeZone')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MIDPRICEMetaData

        if (symbol != other.symbol) return false
        if (indicator != other.indicator) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (timePeriod != other.timePeriod) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + indicator.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + timePeriod
        result = 31 * result + timeZone.hashCode()
        return result
    }
}

@Serializable
public class MIDPRICEUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @SerialName("MIDPRICE") @Serializable(with = DoubleAsStringSerializer4D::class)
    public val midprice: Double
) {
    internal constructor(
        timestamp: Instant,
        midpriceUnitSurrogate: MIDPRICEUnitSurrogate
    ) : this(
        timestamp = timestamp,
        midprice = midpriceUnitSurrogate.midprice,
    )

    override fun toString(): String {
        return "MIDPRICEUnit(timestamp=$timestamp, midprice=$midprice)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MIDPRICEUnit

        if (timestamp != other.timestamp) return false
        if (midprice != other.midprice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + midprice.hashCode()
        return result
    }
}

@Serializable
internal class MIDPRICEUnitSurrogate(
    @SerialName("MIDPRICE") @Serializable(with = DoubleAsStringSerializer4D::class)
    val midprice: Double
) {
    constructor(
        midpriceUnit: MIDPRICEUnit
    ) : this(
        midprice = midpriceUnit.midprice,
    )
}