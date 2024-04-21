package com.jinxservers.alphavantage.technicalIndicators.response

import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.DoubleAsStringSerializer4D
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
public class TechnicalIndicatorUnit(
    @Serializable(with = CentralInstantWithoutTimeAsStringSerializer::class)
    public val timestamp: Instant,
    @Serializable(with = DoubleAsStringSerializer4D::class)
    public val value: Double
) : Comparable<TechnicalIndicatorUnit> {

    override fun compareTo(other: TechnicalIndicatorUnit): Int {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "TechnicalIndicatorUnit(timestamp=$timestamp, value=$value)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TechnicalIndicatorUnit

        if (timestamp != other.timestamp) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}