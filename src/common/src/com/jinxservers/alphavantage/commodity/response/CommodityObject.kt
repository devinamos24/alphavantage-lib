package com.jinxservers.alphavantage.commodity.response

import kotlinx.serialization.Serializable

@Serializable
public class CommodityObject (
    public val name: String,
    public val interval: String,
    public val unit: String,
    public val data: List<CommodityUnit>
) {
    override fun toString(): String {
        return "CommodityObject(name='$name', interval='$interval', unit='$unit', data=$data)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CommodityObject

        if (name != other.name) return false
        if (interval != other.interval) return false
        if (unit != other.unit) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + interval.hashCode()
        result = 31 * result + unit.hashCode()
        result = 31 * result + data.hashCode()
        return result
    }
}