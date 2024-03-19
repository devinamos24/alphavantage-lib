package com.jinxservers.alphavantage.alphaIntelligence

import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

// FIXME: This solution is temporary and needs to be changed once more information is gathered about the value being consumed
internal object TopTickersInstantAsStringSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TopTickersInstantAsStringSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        val string = value.toString().replace("T", " ").replace("Z", " US/Eastern")
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val string = decoder.decodeString()
        return string.replaceFirst(" ", "T").replace(" US/Eastern", "").plus("Z").toInstant()
    }
}

internal object MarketNewsUnitInstantAsStringSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MarketNewsUnitInstantAsStringSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        val string = value.toString().replace("-", "").replace(":", "").replace("Z", "")
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val string = decoder.decodeString().let {
            val stringChars = it.split("").toMutableList()
            // Add missing symbols to string
            stringChars.add(5, "-")
            stringChars.add(8, "-")
            stringChars.add(14, ":")
            stringChars.add(17, ":")
            stringChars.add("Z")
            stringChars.joinToString(separator = "")
        }
        return string.toInstant()
    }
}