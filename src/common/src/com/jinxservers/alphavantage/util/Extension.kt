package com.jinxservers.alphavantage.util

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.minus

internal fun <K,V> Pair<K,V>.toEntry() = object: Map.Entry<K,V> {
    override val key: K = first
    override val value: V = second
}

internal fun String.substringBetween(startDelimiter: String, endDelimiter: String) =
    substringBefore(startDelimiter).substringAfter(endDelimiter)

internal fun Instant.toEasternTime(): String = minus(5, DateTimeUnit.HOUR).toString().replace("Z", "-05:00")