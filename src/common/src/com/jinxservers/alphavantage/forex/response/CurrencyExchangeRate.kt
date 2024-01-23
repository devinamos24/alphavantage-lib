package com.jinxservers.alphavantage.forex.response

import com.jinxservers.alphavantage.forex.CurrencyExchangeRateSerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer8D
import kotlinx.datetime.Instant
import kotlinx.serialization.*

@Serializable(with = CurrencyExchangeRateSerializer::class)
public class CurrencyExchangeRate(
    @SerialName("1. From_Currency Code")
    public val fromCurrencyCode: String,
    @SerialName("2. From_Currency Name")
    public val fromCurrencyName: String,
    @SerialName("3. To_Currency Code")
    public val toCurrencyCode: String,
    @SerialName("4. To_Currency Name")
    public val toCurrencyName: String,
    @SerialName("5. Exchange Rate") @Serializable(with = DoubleAsStringSerializer8D::class)
    public val exchangeRate: Double,
    @SerialName("6. Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    public val lastRefreshed: Instant,
    @SerialName("7. Time Zone")
    public val timeZone: String,
    @SerialName("8. Bid Price") @Serializable(with = DoubleAsStringSerializer8D::class)
    public val bidPrice: Double,
    @SerialName("9. Ask Price") @Serializable(with = DoubleAsStringSerializer8D::class)
    public val askPrice: Double
): Comparable<CurrencyExchangeRate> {

    override fun toString(): String {
        return "CurrencyExchangeRate(fromCurrencyCode='$fromCurrencyCode', fromCurrencyName='$fromCurrencyName', toCurrencyCode='$toCurrencyCode', toCurrencyName='$toCurrencyName', exchangeRate=$exchangeRate, lastRefreshed=$lastRefreshed, timeZone='$timeZone', bidPrice=$bidPrice, askPrice=$askPrice)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CurrencyExchangeRate

        if (fromCurrencyCode != other.fromCurrencyCode) return false
        if (fromCurrencyName != other.fromCurrencyName) return false
        if (toCurrencyCode != other.toCurrencyCode) return false
        if (toCurrencyName != other.toCurrencyName) return false
        if (exchangeRate != other.exchangeRate) return false
        if (lastRefreshed != other.lastRefreshed) return false
        if (timeZone != other.timeZone) return false
        if (bidPrice != other.bidPrice) return false
        if (askPrice != other.askPrice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fromCurrencyCode.hashCode()
        result = 31 * result + fromCurrencyName.hashCode()
        result = 31 * result + toCurrencyCode.hashCode()
        result = 31 * result + toCurrencyName.hashCode()
        result = 31 * result + exchangeRate.hashCode()
        result = 31 * result + lastRefreshed.hashCode()
        result = 31 * result + timeZone.hashCode()
        result = 31 * result + bidPrice.hashCode()
        result = 31 * result + askPrice.hashCode()
        return result
    }

    override fun compareTo(other: CurrencyExchangeRate): Int {
        return lastRefreshed.compareTo(other.lastRefreshed)
    }
}


@Serializable
internal class CurrencyExchangeRateSurrogate(
    @SerialName("1. From_Currency Code")
    val fromCurrencyCode: String,
    @SerialName("2. From_Currency Name")
    val fromCurrencyName: String,
    @SerialName("3. To_Currency Code")
    val toCurrencyCode: String,
    @SerialName("4. To_Currency Name")
    val toCurrencyName: String,
    @SerialName("5. Exchange Rate") @Serializable(with = DoubleAsStringSerializer8D::class)
    val exchangeRate: Double,
    @SerialName("6. Last Refreshed") @Serializable(with = CentralInstantAsStringSerializer::class)
    val lastRefreshed: Instant,
    @SerialName("7. Time Zone")
    val timeZone: String,
    @SerialName("8. Bid Price") @Serializable(with = DoubleAsStringSerializer8D::class)
    val bidPrice: Double,
    @SerialName("9. Ask Price") @Serializable(with = DoubleAsStringSerializer8D::class)
    val askPrice: Double
) {
    constructor(currencyExchangeRate: CurrencyExchangeRate) : this(
        currencyExchangeRate.fromCurrencyCode,
        currencyExchangeRate.fromCurrencyName,
        currencyExchangeRate.toCurrencyCode,
        currencyExchangeRate.toCurrencyName,
        currencyExchangeRate.exchangeRate,
        currencyExchangeRate.lastRefreshed,
        currencyExchangeRate.timeZone,
        currencyExchangeRate.bidPrice,
        currencyExchangeRate.askPrice
    )
}