package com.jinxservers.alphavantage.core.response

import com.jinxservers.alphavantage.util.LocalTimeAsStringSerializer
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class MarketUnit(
    @SerialName("market_type")
    public val marketType: String,
    @SerialName("region")
    public val region: String,
    @SerialName("primary_exchanges")
    public val primaryExchanges: String,
    @SerialName("local_open") @Serializable(with = LocalTimeAsStringSerializer::class)
    public val localOpen: LocalTime,
    @SerialName("local_close") @Serializable(with = LocalTimeAsStringSerializer::class)
    public val localClose: LocalTime,
    @SerialName("current_status")
    public val currentStatus: String,
    @SerialName("notes")
    public val notes: String
) {

    override fun toString(): String {
        return "MarketUnit(marketType='$marketType', region='$region', primaryExchanges='$primaryExchanges', localOpen=$localOpen, localClose=$localClose, currentStatus='$currentStatus', notes='$notes')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MarketUnit

        if (marketType != other.marketType) return false
        if (region != other.region) return false
        if (primaryExchanges != other.primaryExchanges) return false
        if (localOpen != other.localOpen) return false
        if (localClose != other.localClose) return false
        if (currentStatus != other.currentStatus) return false
        if (notes != other.notes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = marketType.hashCode()
        result = 31 * result + region.hashCode()
        result = 31 * result + primaryExchanges.hashCode()
        result = 31 * result + localOpen.hashCode()
        result = 31 * result + localClose.hashCode()
        result = 31 * result + currentStatus.hashCode()
        result = 31 * result + notes.hashCode()
        return result
    }
}