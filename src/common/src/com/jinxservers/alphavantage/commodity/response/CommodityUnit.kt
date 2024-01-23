package com.jinxservers.alphavantage.commodity.response

import com.jinxservers.alphavantage.commodity.CommodityUnitSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer
import com.jinxservers.alphavantage.util.NullableDoubleAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

//FIXME: auto-generated serializer for this class not using NullableDoubleAsStringSerializer for encoding null values
@Serializable(with = CommodityUnitSerializer::class)
public class CommodityUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val date: Instant,
    @Serializable(with = NullableDoubleAsStringSerializer::class)
    public val value: Double?
) : Comparable<CommodityUnit> {

    override fun toString(): String {
        return "CommodityUnit(date=$date, value=$value)"
    }

    override fun compareTo(other: CommodityUnit): Int {
        return date.compareTo(other.date)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CommodityUnit

        if (date != other.date) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }
}

