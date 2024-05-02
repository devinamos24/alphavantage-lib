package com.jinxservers.alphavantage.util

internal sealed interface Interval {
    fun value(): String
}

public enum class ShortInterval(private val value: String) : Interval {
    ONE_MINUTE("1min"),
    FIVE_MINUTES("5min"),
    FIFTEEN_MINUTES("15min"),
    THIRTY_MINUTES("30min"),
    SIXTY_MINUTES("60min");

    override fun value(): String {
        return this.value
    }
}

public enum class LongInterval(private val value: String) : Interval {
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly");

    override fun value(): String {
        return this.value
    }
}

public enum class ShortLongInterval(private val value: String) : Interval {
    ONE_MINUTE("1min"),
    FIVE_MINUTES("5min"),
    FIFTEEN_MINUTES("15min"),
    THIRTY_MINUTES("30min"),
    SIXTY_MINUTES("60min"),
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly");

    override fun value(): String {
        return this.value
    }
}

public enum class CommodityInterval(private val value: String) : Interval {
    MONTHLY("monthly"),
    QUARTERLY("quarterly"),
    ANNUAL("annual");

    override fun value(): String {
        return this.value
    }
}

public enum class GdpInterval(private val value: String) : Interval {
    QUARTERLY("quarterly"),
    ANNUAL("annual");

    override fun value(): String {
        return this.value
    }
}

public enum class CpiInterval(private val value: String) : Interval {
    MONTHLY("monthly"),
    SEMIANNUAL("semiannual");

    override fun value(): String {
        return this.value
    }
}
