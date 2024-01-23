package com.jinxservers.alphavantage.economic.response

import com.jinxservers.alphavantage.economic.EconomicUnitSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.NullableDoubleAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

//FIXME: auto-generated serializer for this class not using NullableDoubleAsStringSerializer for encoding null values
@Serializable(with = EconomicUnitSerializer::class)
public class EconomicUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val date: Instant,
    @Serializable(with = NullableDoubleAsStringSerializer::class)
    public val value: Double?
) : Comparable<EconomicUnit> {

    override fun toString(): String {
        return "EconomicUnit(date=$date, value=$value)"
    }

    override fun compareTo(other: EconomicUnit): Int {
        return date.compareTo(other.date)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EconomicUnit

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

