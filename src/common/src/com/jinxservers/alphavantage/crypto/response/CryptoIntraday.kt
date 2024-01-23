package com.jinxservers.alphavantage.crypto.response

import com.jinxservers.alphavantage.crypto.CryptoIntradaySerializer
import com.jinxservers.alphavantage.crypto.IntradayCryptoUnitListAsMapSerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.json.JsonNames

@Serializable(with = CryptoIntradaySerializer::class)
public class CryptoIntraday(
    @SerialName("Meta Data")
    public val metaData: CryptoIntradayMetaData,
    @SerialName("Time Series Crypto")
    public val cryptoUnits: List<CryptoUnit>
) : Comparable<CryptoIntraday> {

    internal constructor(cryptoIntradaySurrogate: CryptoIntradaySurrogate) : this(
        cryptoIntradaySurrogate.metaData,
        cryptoIntradaySurrogate.cryptoUnits
    )

    override fun toString(): String {
        return "TimeSeriesIntraday(metaData=$metaData, CryptoUnits=$cryptoUnits)"
    }

    override fun compareTo(other: CryptoIntraday): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CryptoIntraday

        if (metaData != other.metaData) return false
        if (cryptoUnits != other.cryptoUnits) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metaData.hashCode()
        result = 31 * result + cryptoUnits.hashCode()
        return result
    }
}
// TODO: Use serializer generator when it is stable
@Serializable
internal class CryptoIntradaySurrogate(
    @SerialName("Meta Data")
    val metaData: CryptoIntradayMetaData,
    @SerialName("Time Series Crypto") @Serializable(with = IntradayCryptoUnitListAsMapSerializer::class)
    val cryptoUnits: List<CryptoUnit>
) {
    constructor(cryptoIntraday: CryptoIntraday) : this(
        cryptoIntraday.metaData,
        cryptoIntraday.cryptoUnits
    )
}

@Serializable
public class CryptoIntradayMetaData(
    @SerialName("1. Information")
    public val information: String,
    @SerialName("2. Digital Currency Code")
    public val digitalCurrencyCode: String,
    @SerialName("3. Digital Currency Name")
    public val digitalCurrencyName: String,
    @SerialName("4. Market Code")
    public val marketCode: String,
    @SerialName("5. Market Name")
    public val marketName: String,
    @SerialName("6. Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("7. Interval")
    public val interval: String,
    @SerialName("8. Output Size")
    public val outputSize: String,
    @SerialName("9. Time Zone")
    public val timeZone: String
) : Comparable<CryptoIntradayMetaData> {

    override fun toString(): String {
        return "CryptoIntradayMetaData(information='$information', digitalCurrencyCode='$digitalCurrencyCode', digitalCurrencyName='$digitalCurrencyName', marketCode='$marketCode', marketName='$marketName', lastRefreshed=$lastRefreshed, interval='$interval', outputSize='$outputSize', timeZone='$timeZone')"
    }

    override fun compareTo(other: CryptoIntradayMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CryptoIntradayMetaData

        if (information != other.information) return false
        if (digitalCurrencyCode != other.digitalCurrencyCode) return false
        if (digitalCurrencyName != other.digitalCurrencyName) return false
        if (marketCode != other.marketCode) return false
        if (marketName != other.marketName) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (interval != other.interval) return false
        if (outputSize != other.outputSize) return false
        if (timeZone != other.timeZone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = information.hashCode()
        result = 31 * result + digitalCurrencyCode.hashCode()
        result = 31 * result + digitalCurrencyName.hashCode()
        result = 31 * result + marketCode.hashCode()
        result = 31 * result + marketName.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + outputSize.hashCode()
        result = 31 * result + timeZone.hashCode()
        return result
    }


}