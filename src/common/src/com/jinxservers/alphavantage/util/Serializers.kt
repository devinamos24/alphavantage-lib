package com.jinxservers.alphavantage.util

import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object EasternInstantAsStringSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        val string = value.toEasternTime().replace("T", " ").replace("-05:00", "")
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val string = decoder.decodeString()
        return string.replace(" ", "T").plus("-05:00").toInstant()
    }
}

internal object CentralInstantAsStringSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("CentralInstantAsString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        val string = value.toString().replace("T", " ").replace("Z", "")
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val string = decoder.decodeString()
        return string.replace(" ", "T").plus("Z").toInstant()
    }
}

internal object CentralInstantWithoutTimeAsStringSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("CentralInstantWithoutTimeAsString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        val string = value.toString().substringBefore("T")
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val string = decoder.decodeString()
        return string.plus("T00:00:00Z").toInstant()
    }
}

internal object DoubleAsStringSerializer : KSerializer<Double> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DoubleAsString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Double) =
        encoder.encodeString(value.toString())


    override fun deserialize(decoder: Decoder): Double =
        decoder.decodeString().toDouble()
}

internal object NullableDoubleAsStringSerializer : KSerializer<Double?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("NullableDoubleAsString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Double?) {
        if (value == null) {
            encoder.encodeString(".")
        } else {
            encoder.encodeString(value.toString())
        }
    }


    override fun deserialize(decoder: Decoder): Double? =
        decoder.decodeString().toDoubleOrNull()

}

internal object DoubleAsStringSerializer8D : KSerializer<Double> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DoubleAsString8D", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Double) {
        val string = value.toString().let {
            var totalDecimals = 0
            var newString = it
            if (it.contains(".")) {
                totalDecimals = it.substringAfter(".").length
            } else {
                newString = "$newString."
            }
            if (totalDecimals < 8) {
                newString + ("0".repeat(8-totalDecimals))
            } else {
                newString
            }
        }
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Double {
        return decoder.decodeDouble()
    }
}

internal object DoubleAsStringSerializer5D : KSerializer<Double> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DoubleAsString5D", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Double) {
        val string = value.toString().let {
            var totalDecimals = 0
            var newString = it
            if (it.contains(".")) {
                totalDecimals = it.substringAfter(".").length
            } else {
                newString = "$newString."
            }
            if (totalDecimals < 5) {
                newString + ("0".repeat(5-totalDecimals))
            } else {
                newString
            }
        }
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Double {
        return decoder.decodeDouble()
    }
}