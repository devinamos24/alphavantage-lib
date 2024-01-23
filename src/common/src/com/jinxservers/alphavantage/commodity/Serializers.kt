package com.jinxservers.alphavantage.commodity

import com.jinxservers.alphavantage.commodity.response.CommodityUnit
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.NullableDoubleAsStringSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

//FIXME: Remove this when they fix the auto-generated serializers
internal object CommodityUnitSerializer : KSerializer<CommodityUnit> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("CommodityUnit") {
            element<Instant>("date")
            element<Double?>("value")
        }

    override fun serialize(encoder: Encoder, value: CommodityUnit) =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CentralInstantWithoutTimeAsStringSerializer, value.date)
            encodeSerializableElement(descriptor, 1, NullableDoubleAsStringSerializer, value.value)
        }


    override fun deserialize(decoder: Decoder): CommodityUnit =
        decoder.decodeStructure(descriptor) {
            var date: Instant? = null
            var value: Double? = -1.0
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> date = decodeSerializableElement(descriptor, 0, CentralInstantWithoutTimeAsStringSerializer)
                    1 -> value = decodeSerializableElement(descriptor, 1, NullableDoubleAsStringSerializer)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            requireNotNull(date)
            if (value != null) {
                require(value != -1.0)
            }
            CommodityUnit(date, value)
        }

}