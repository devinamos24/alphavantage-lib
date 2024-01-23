package com.jinxservers.alphavantage.crypto

import com.jinxservers.alphavantage.crypto.response.*
import com.jinxservers.alphavantage.crypto.response.IntradayCryptoUnitSurrogate
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.toEntry
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.MapEntrySerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer

internal object CryptoUnitSerializer : KSerializer<CryptoUnit> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(CentralInstantAsStringSerializer, IntradayCryptoUnitSurrogate.serializer()).descriptor

    override fun serialize(encoder: Encoder, value: CryptoUnit) {
        val surrogate = IntradayCryptoUnitSurrogate(value.open, value.high, value.low, value.close, value.volume)
        encoder.encodeSerializableValue(MapEntrySerializer(CentralInstantAsStringSerializer, IntradayCryptoUnitSurrogate.serializer()), (value.timestamp to surrogate).toEntry())
    }

    override fun deserialize(decoder: Decoder): CryptoUnit {
        val data: Pair<Instant, IntradayCryptoUnitSurrogate> = decoder.decodeSerializableValue(
            MapEntrySerializer(
                CentralInstantAsStringSerializer, IntradayCryptoUnitSurrogate.serializer())
        ).toPair()
        return CryptoUnit(data.first, data.second)
    }
}

internal object CryptoIntradaySerializer : KSerializer<CryptoIntraday> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CryptoIntraday") {
        element<CryptoIntradayMetaData>("Meta Data")
        element<Map<Instant, CryptoUnitSurrogate>>("Time Series Crypto")
    }

    override fun serialize(encoder: Encoder, value: CryptoIntraday) =
        encoder.encodeSerializableValue(CryptoIntradaySurrogateSerializer(value.metaData.interval), CryptoIntradaySurrogate(value))


    override fun deserialize(decoder: Decoder): CryptoIntraday =
        CryptoIntraday(decoder.decodeSerializableValue(CryptoIntradaySurrogateSerializer()))
}

internal class CryptoIntradaySurrogateSerializer(private val interval: String? = null) : JsonTransformingSerializer<CryptoIntradaySurrogate>(CryptoIntradaySurrogate.serializer()) {

    override fun transformSerialize(element: JsonElement): JsonElement {
        requireNotNull(interval) { "Interval must not be null when using transformSerialize" }
        if (element is JsonObject) {
            val metaData: JsonElement =
                element["Meta Data"] ?: throw SerializationException("Missing field \"Meta Data\"")
            val cryptoUnits: JsonElement =
                element["Time Series Crypto"] ?: throw SerializationException("Missing field \"Time Series Crypto\"")
            return JsonObject(
                mapOf(
                    "Meta Data" to metaData,
                    "Time Series Crypto ($interval)" to cryptoUnits
                )
            )
        } else {
            throw SerializationException("Serialized CryptoUnitList must be a JsonObject")
        }
    }

    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element is JsonObject) {
            JsonObject(element.map { it.key.substringBefore(" (") to it.value }.toMap())
        } else {
            throw SerializationException("Deserialized CryptoUnitList must be a JsonObject")
        }

}

internal object IntradayCryptoUnitListAsMapSerializer : KSerializer<List<CryptoUnit>> {
    private val serializer = MapSerializer(CentralInstantAsStringSerializer, IntradayCryptoUnitSurrogate.serializer())

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: List<CryptoUnit>) =
        encoder.encodeSerializableValue(serializer,
            value.associate { it.timestamp to IntradayCryptoUnitSurrogate(it.open, it.high, it.low, it.close, it.volume) }
        )

    override fun deserialize(decoder: Decoder): List<CryptoUnit> =
        decoder.decodeSerializableValue(serializer).map { CryptoUnit(it.key, it.value) }

}

internal object CryptoDailySerializer : KSerializer<CryptoDaily> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CryptoDaily") {
        element<CryptoIntradayMetaData>("Meta Data")
        element<Map<Instant, CryptoUnitSurrogate>>("Time Series (Digital Currency Daily)")
    }

    override fun serialize(encoder: Encoder, value: CryptoDaily): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CryptoDailyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CryptoUnitSurrogateSerializer(value.metaData.marketCode)),
                value.cryptoUnits.associate {
                    it.timestamp to CryptoUnitSurrogate(
                        it.open,
                        it.high,
                        it.low,
                        it.close,
                        it.brokenVolume ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoDaily because it is missing brokenVolume field"),
                        it.USDOpen ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoDaily because it is missing USDOpen field"),
                        it.USDHigh ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoDaily because it is missing USDHigh field"),
                        it.USDLow ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoDaily because it is missing USDLow field"),
                        it.USDClose ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoDaily because it is missing USDClose field"),
                        it.USDMarketCap ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoDaily because it is missing USDMarketCap field")
                        )
                }
            )
        }

    override fun deserialize(decoder: Decoder): CryptoDaily =
        decoder.decodeStructure(descriptor) {
            var metaData: CryptoDailyMetaData? = null
            var cryptoUnits: List<CryptoUnit>? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> metaData = decodeSerializableElement(descriptor, 0, CryptoDailyMetaData.serializer())
                    1 -> cryptoUnits = decodeSerializableElement(descriptor, 1, MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CryptoUnitSurrogateSerializer())).map { CryptoUnit(it.key, it.value) }
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index $index")
                }
            }
            requireNotNull(metaData)
            requireNotNull(cryptoUnits)
            CryptoDaily(metaData, cryptoUnits)
        }
}

internal object CryptoWeeklySerializer : KSerializer<CryptoWeekly> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CryptoWeekly") {
        element<CryptoIntradayMetaData>("Meta Data")
        element<Map<Instant, CryptoUnitSurrogate>>("Time Series (Digital Currency Weekly)")
    }

    override fun serialize(encoder: Encoder, value: CryptoWeekly): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CryptoWeeklyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CryptoUnitSurrogateSerializer(value.metaData.marketCode)),
                value.cryptoUnits.associate {
                    it.timestamp to CryptoUnitSurrogate(
                        it.open,
                        it.high,
                        it.low,
                        it.close,
                        it.brokenVolume ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing brokenVolume field"),
                        it.USDOpen ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDOpen field"),
                        it.USDHigh ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDHigh field"),
                        it.USDLow ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDLow field"),
                        it.USDClose ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDClose field"),
                        it.USDMarketCap ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDMarketCap field")
                    )
                }
            )
        }

    override fun deserialize(decoder: Decoder): CryptoWeekly =
        decoder.decodeStructure(descriptor) {
            var metaData: CryptoWeeklyMetaData? = null
            var cryptoUnits: List<CryptoUnit>? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> metaData = decodeSerializableElement(descriptor, 0, CryptoWeeklyMetaData.serializer())
                    1 -> cryptoUnits = decodeSerializableElement(descriptor, 1, MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CryptoUnitSurrogateSerializer())).map { CryptoUnit(it.key, it.value) }
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index $index")
                }
            }
            requireNotNull(metaData)
            requireNotNull(cryptoUnits)
            CryptoWeekly(metaData, cryptoUnits)
        }
}

internal object CryptoMonthlySerializer : KSerializer<CryptoMonthly> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CryptoMonthly") {
        element<CryptoIntradayMetaData>("Meta Data")
        element<Map<Instant, CryptoUnitSurrogate>>("Time Series (Digital Currency Monthly)")
    }

    override fun serialize(encoder: Encoder, value: CryptoMonthly): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CryptoMonthlyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CryptoUnitSurrogateSerializer(value.metaData.marketCode)),
                value.cryptoUnits.associate {
                    it.timestamp to CryptoUnitSurrogate(
                        it.open,
                        it.high,
                        it.low,
                        it.close,
                        it.brokenVolume ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing brokenVolume field"),
                        it.USDOpen ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDOpen field"),
                        it.USDHigh ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDHigh field"),
                        it.USDLow ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDLow field"),
                        it.USDClose ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDClose field"),
                        it.USDMarketCap ?: throw SerializationException("Cannot serialize CryptoUnit in CryptoWeekly because it is missing USDMarketCap field")
                    )
                }
            )
        }

    override fun deserialize(decoder: Decoder): CryptoMonthly =
        decoder.decodeStructure(descriptor) {
            var metaData: CryptoMonthlyMetaData? = null
            var cryptoUnits: List<CryptoUnit>? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> metaData = decodeSerializableElement(descriptor, 0, CryptoMonthlyMetaData.serializer())
                    1 -> cryptoUnits = decodeSerializableElement(descriptor, 1, MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CryptoUnitSurrogateSerializer())).map { CryptoUnit(it.key, it.value) }
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index $index")
                }
            }
            requireNotNull(metaData)
            requireNotNull(cryptoUnits)
            CryptoMonthly(metaData, cryptoUnits)
        }
}

internal class CryptoUnitSurrogateSerializer(private val marketCode: String? = null) : JsonTransformingSerializer<CryptoUnitSurrogate>(CryptoUnitSurrogate.serializer()) {

    private val keys: List<String> = listOf(
        "1a. open ($marketCode)",
        "1b. open (USD)",
        "2a. high ($marketCode)",
        "2b. high (USD)",
        "3a. low ($marketCode)",
        "3b. low (USD)",
        "4a. close ($marketCode)",
        "4b. close (USD)",
        "5. volume",
        "6. market cap (USD)"
    )

    override fun transformSerialize(element: JsonElement): JsonElement {
        requireNotNull(marketCode) { "marketCode must not be null when using transformSerialize" }
        if (element is JsonObject) {
            return JsonObject(keys.associateWith {
                element[it.substringBefore(" (")]
                    ?: throw SerializationException("Missing field \"${it.substringBefore(" (")}\"")
            })
        } else {
            throw SerializationException("Serialized CryptoUnitSurrogate must be a JsonObject")
        }
    }

    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element is JsonObject) {
            JsonObject(element.map { it.key.substringBefore(" (") to it.value }.toMap())
        } else {
            throw SerializationException("Deserialized CryptoUnitSurrogate must be a JsonObject")
        }

}