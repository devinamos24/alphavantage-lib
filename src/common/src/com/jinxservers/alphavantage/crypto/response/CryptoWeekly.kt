package com.jinxservers.alphavantage.crypto.response

import com.jinxservers.alphavantage.crypto.CryptoDailySerializer
import com.jinxservers.alphavantage.crypto.CryptoWeeklySerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = CryptoWeeklySerializer::class)
public class CryptoWeekly(
    @SerialName("Meta Data")
    public val metaData: CryptoWeeklyMetaData,
    @SerialName("Time Series (Digital Currency Weekly)")
    public val cryptoUnits: List<CryptoUnit>
) : Comparable<CryptoDaily> {

    override fun toString(): String {
        return "CryptoDaily(metaData=$metaData, CryptoUnits=$cryptoUnits)"
    }

    override fun compareTo(other: CryptoDaily): Int {
        return metaData.compareTo(other.metaData)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CryptoWeekly

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

@Serializable
public class CryptoWeeklyMetaData(
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
    @SerialName("7. Time Zone")
    public val timeZone: String
) : Comparable<CryptoDailyMetaData> {

    override fun toString(): String {
        return "CryptoDailyMetaData(information='$information', digitalCurrencyCode='$digitalCurrencyCode', digitalCurrencyName='$digitalCurrencyName', marketCode='$marketCode', marketName='$marketName', lastRefreshed=$lastRefreshed, timeZone='$timeZone')"
    }

    override fun compareTo(other: CryptoDailyMetaData): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CryptoWeeklyMetaData

        if (information != other.information) return false
        if (digitalCurrencyCode != other.digitalCurrencyCode) return false
        if (digitalCurrencyName != other.digitalCurrencyName) return false
        if (marketCode != other.marketCode) return false
        if (marketName != other.marketName) return false
        if (lastRefreshed != other.lastRefreshed) return false
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
        result = 31 * result + timeZone.hashCode()
        return result
    }

}