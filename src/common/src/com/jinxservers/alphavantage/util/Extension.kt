package com.jinxservers.alphavantage.util

import kotlin.math.abs

internal fun <K,V> Pair<K,V>.toEntry() = object: Map.Entry<K,V> {
    override val key: K = first
    override val value: V = second
}

internal fun Double.toStringWithoutScientific(): String {
    val string = this.toString()
    if (string.contains("E")) {
        val power = string.substringAfter("E").toInt()
        val strippedString = string.substringBefore("E").replace(".", "").toMutableList()
        if (power > 0) {
            if (strippedString.size == power) {
                strippedString.add('.')
                strippedString.add('0')
                return strippedString.joinToString("")
            } else if (strippedString.size < power) {
                repeat(strippedString.size - power + 1) {
                    strippedString.add('0')
                }
                strippedString.add('.')
                strippedString.add('0')
                return strippedString.joinToString("")
            } else {
                strippedString.add(power + 1, '.')
                return strippedString.joinToString("")
            }
        } else {
            repeat(abs(power) - 1) {
                strippedString.add(0, '0')
            }
            strippedString.add(0, '.')
            strippedString.add(0, '0')
            return strippedString.joinToString("")
        }
    } else {
        return string
    }
}