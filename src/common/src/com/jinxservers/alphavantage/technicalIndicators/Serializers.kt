package com.jinxservers.alphavantage.technicalIndicators

import com.jinxservers.alphavantage.technicalIndicators.response.TechnicalIndicatorMetaData
import com.jinxservers.alphavantage.technicalIndicators.response.TechnicalIndicatorObject
import com.jinxservers.alphavantage.technicalIndicators.response.TechnicalIndicatorObjectSurrogate
import com.jinxservers.alphavantage.technicalIndicators.response.TechnicalIndicatorUnit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

internal object TechnicalIndicatorObjectSerializer : KSerializer<TechnicalIndicatorObject> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TechnicalIndicatorObject") {
        element<TechnicalIndicatorMetaData>("Meta Data")
        element<List<TechnicalIndicatorUnit>>("Technical Indicator")
    }

    override fun serialize(encoder: Encoder, value: TechnicalIndicatorObject) =
        encoder.encodeSerializableValue(TechnicalIndicatorObjectSurrogateSerializer(value.metaData.indicator.substringAfter("(").substringBefore(")")), TechnicalIndicatorObjectSurrogate(value))

    override fun deserialize(decoder: Decoder): TechnicalIndicatorObject =
        TechnicalIndicatorObject(decoder.decodeSerializableValue(TechnicalIndicatorObjectSurrogateSerializer()))

}

internal class TechnicalIndicatorObjectSurrogateSerializer(private val indicator: String? = null) : JsonTransformingSerializer<TechnicalIndicatorObjectSurrogate>(TechnicalIndicatorObjectSurrogate.serializer()) {

    override fun transformSerialize(element: JsonElement): JsonElement {
        requireNotNull(indicator) { "Indicator must not be null when using transformSerialize" }
        if (element is JsonObject) {
            val metaData: JsonElement = element["Meta Data"] ?: throw SerializationException("Missing field \"Meta Data\"")
            val mapOfUnits: JsonElement = element["mapOfUnits"] ?: throw SerializationException("Missing field \"mapOfUnits\"")
            val mapOfUnitsObject: JsonObject
            if (mapOfUnits is JsonArray) {
                mapOfUnitsObject = JsonObject(
                    mapOfUnits.associate {
                        if (it is JsonObject) {
                            val timestamp = it["timestamp"]
                                ?: throw SerializationException("All values in TechnicalIndicatorList must contain field \"timestamp\"")
                            val value = it["value"]
                                ?: throw SerializationException("All values in TechnicalIndicatorList must contain field \"value\"")

                            timestamp.toString().substringAfter("\"").substringBefore("\"") to JsonObject(mapOf(indicator to value))

                        } else {
                            throw SerializationException("All values in TechnicalIndicatorList must be JsonObjects")
                        }
                    }
                )
            } else {
                throw SerializationException("Serialized TechnicalIndicatorList must be a JsonObject")
            }
            return JsonObject(
                mapOf(
                    "Meta Data" to metaData,
                    "Technical Analysis: $indicator" to mapOfUnitsObject
                )
            )
        } else {
            throw SerializationException("Serialized TechnicalIndicatorObjectSurrogate must be a JsonObject")
        }
    }

    override fun transformDeserialize(element: JsonElement): JsonElement {
        if (element is JsonObject) {
            val metaData: JsonElement = element["Meta Data"] ?: throw SerializationException("Missing field \"Meta Data\"")
            val mapOfUnits: JsonElement = element.filterKeys { it.startsWith("Technical Analysis") }.toList().elementAtOrNull(0)?.second ?: throw SerializationException("Missing field \"Technical Analysis\"")
            val listOfUnits: JsonArray
            if (mapOfUnits is JsonObject) {
                listOfUnits = JsonArray(
                    mapOfUnits.map {
                        val value: JsonElement = (it.value as? JsonObject)?.toList()?.getOrNull(0)?.second ?: throw SerializationException("All values in TechnicalIndicatorList must contain value field")
                        JsonObject(
                            mapOf(
                                "timestamp" to JsonPrimitive(it.key),
                                "value" to value
                            )
                        )
                    }
                )
            } else {
                throw SerializationException("Deserialized TechnicalIndicatorList must be a JsonObject")
            }
            return JsonObject(
                mapOf(
                    "Meta Data" to metaData,
                    "mapOfUnits" to listOfUnits
                )
            )
        } else {
            throw SerializationException("Deserialized TechnicalIndicatorObjectSurrogate must be a JsonObject")
        }
    }

}

//internal class TechnicalIndicatorListSerializer(private val indicator: String? = null) : JsonTransformingSerializer<List<TechnicalIndicatorUnit>>(ListSerializer(TechnicalIndicatorUnitSurrogateSerializer)) {
//
//    override fun transformSerialize(element: JsonElement): JsonElement {
//        requireNotNull(indicator) { "Indicator must not be null when using transformSerialize" }
//        if (element is JsonArray) {
//            return JsonObject(
//                element.associate {
//                    if (it is JsonObject) {
//                        val timestamp = it["timestamp"]
//                            ?: throw SerializationException("All values in TechnicalIndicatorList must contain field \"timestamp\"")
//                        val value = it["value"]
//                            ?: throw SerializationException("All values in TechnicalIndicatorList must contain field \"value\"")
//
//                        timestamp.toString() to JsonObject(mapOf(indicator to value))
//
//                    } else {
//                        throw SerializationException("All values in TechnicalIndicatorList must be JsonObjects")
//                    }
//                }
//            )
//        } else {
//            throw SerializationException("Serialized TechnicalIndicatorList must be a JsonObject")
//        }
//    }
//
//    override fun transformDeserialize(element: JsonElement): JsonElement {
//        if (element is JsonObject) {
//            return JsonArray(
//                element.map {
//                    val value: JsonElement = (it.value as? JsonObject)?.toList()?.getOrNull(0)?.second ?: throw SerializationException("All values in TechnicalIndicatorList must contain value field")
//                    JsonObject(
//                        mapOf(
//                            "timestamp" to JsonPrimitive(it.key),
//                            "value" to value
//                        )
//                    )
//                }
//            )
//        } else {
//            throw SerializationException("Deserialized TechnicalIndicatorList must be a JsonObject")
//        }
//    }
//
//}










//internal object TechnicalIndicatorUnitSerializer : KSerializer<TechnicalIndicatorUnit> {
//    override val descriptor: SerialDescriptor = MapEntrySerializer(CentralInstantAsStringSerializer, TechnicalIndicatorUnitSurrogate.serializer()).descriptor
//
//    override fun serialize(encoder: Encoder, value: TechnicalIndicatorUnit) {
//        val surrogate = TechnicalIndicatorUnitSurrogate(value.value)
//        if (value.timestamp.toString().substringAfter("T") == "00:00:00Z")
//        encoder.encodeSerializableValue(MapEntrySerializer(CentralInstantAsStringSerializer, TechnicalIndicatorUnitSurrogate.serializer()), (value.timestamp to surrogate).toEntry())
//    }
//
//    override fun deserialize(decoder: Decoder): TechnicalIndicatorUnit {
//        val data:
//    }
//
//}

//internal object TechnicalIndicatorUnitSurrogateSerializer : KSerializer<TechnicalIndicatorUnit> {
//    override val descriptor: SerialDescriptor
//        get() = TODO("Not yet implemented")
//
//    override fun serialize(encoder: Encoder, value: TechnicalIndicatorUnit) {
//        TODO("Not yet implemented")
//    }
//
//    override fun deserialize(decoder: Decoder): TechnicalIndicatorUnit {
//        TODO("Not yet implemented")
//    }
//
//}

//internal class TechnicalIndicatorUnitSurrogateSerializer(private val indicator: String?) : JsonTransformingSerializer<TechnicalIndicatorUnitSurrogate>(TechnicalIndicatorUnitSurrogate.serializer()) {
//
//    override fun transformSerialize(element: JsonElement): JsonElement {
//        requireNotNull(indicator) { "Indicator must not be null when using transformSerialize" }
//        if (element is JsonObject) {
//            val value: JsonElement = element["value"] ?: throw SerializationException("Missing field \"value\"")
//            return JsonObject(
//                mapOf(
//                    indicator to value
//                )
//            )
//        } else {
//            throw SerializationException("Serialized TechnicalIndicatorUnitSurrogate must be a JsonObject")
//        }
//    }
//
//    override fun transformDeserialize(element: JsonElement): JsonElement {
//        if (element is JsonObject) {
//            JsonObject(
//                element.map { "value" to it.value }.toMap()
//            )
//        } else {
//            throw SerializationException("Deserialized TechnicalIndicatorUnitSurrogate must be a JsonObject")
//        }
//    }
//}