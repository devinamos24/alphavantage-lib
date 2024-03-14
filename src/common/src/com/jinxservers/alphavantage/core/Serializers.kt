package com.jinxservers.alphavantage.core

import com.jinxservers.alphavantage.core.response.*
import com.jinxservers.alphavantage.forex.ForexIntradaySerializer
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.toEntry
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapEntrySerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure

internal object CoreUnitSerializer : KSerializer<CoreUnit> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(CentralInstantAsStringSerializer, CoreUnitSurrogate.serializer()).descriptor

    override fun serialize(encoder: Encoder, value: CoreUnit) {
        val surrogate = CoreUnitSurrogate(value.open, value.high, value.low, value.close, value.volume)
        encoder.encodeSerializableValue(MapEntrySerializer(CentralInstantAsStringSerializer, CoreUnitSurrogate.serializer()), (value.timestamp to surrogate).toEntry())
    }

    override fun deserialize(decoder: Decoder): CoreUnit {
        val data: Pair<Instant, CoreUnitSurrogate> = decoder.decodeSerializableValue(
            MapEntrySerializer(
                CentralInstantAsStringSerializer, CoreUnitSurrogate.serializer())
        ).toPair()
        return CoreUnit(data.first, data.second)
    }
}

//FIXME: As of writing this comment the alpha vantage api structures CoreUnitAdjusted objects different for CoreDaily requests only. ._.
internal object CoreUnitAdjustedDailySerializer : KSerializer<CoreUnitAdjusted> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(CentralInstantAsStringSerializer, CoreUnitAdjustedDailySurrogate.serializer()).descriptor

    override fun serialize(encoder: Encoder, value: CoreUnitAdjusted) {
        val surrogate = CoreUnitAdjustedDailySurrogate(value.open, value.high, value.low, value.close, value.adjustedClose, value.volume, value.dividendAmount, value.splitCoefficient)
        encoder.encodeSerializableValue(MapEntrySerializer(CentralInstantAsStringSerializer, CoreUnitAdjustedDailySurrogate.serializer()), (value.timestamp to surrogate).toEntry())
    }

    override fun deserialize(decoder: Decoder): CoreUnitAdjusted {
        val data: Pair<Instant, CoreUnitAdjustedDailySurrogate> = decoder.decodeSerializableValue(
            MapEntrySerializer(
                CentralInstantAsStringSerializer, CoreUnitAdjustedDailySurrogate.serializer())
        ).toPair()
        return CoreUnitAdjusted(data.first, data.second)
    }
}

internal object CoreUnitAdjustedSerializer : KSerializer<CoreUnitAdjusted> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(CentralInstantAsStringSerializer, CoreUnitAdjustedSurrogate.serializer()).descriptor

    override fun serialize(encoder: Encoder, value: CoreUnitAdjusted) {
        val surrogate = CoreUnitAdjustedSurrogate(value.open, value.high, value.low, value.close, value.adjustedClose, value.volume, value.dividendAmount, value.splitCoefficient)
        encoder.encodeSerializableValue(MapEntrySerializer(CentralInstantAsStringSerializer, CoreUnitAdjustedSurrogate.serializer()), (value.timestamp to surrogate).toEntry())
    }

    override fun deserialize(decoder: Decoder): CoreUnitAdjusted {
        val data: Pair<Instant, CoreUnitAdjustedSurrogate> = decoder.decodeSerializableValue(
            MapEntrySerializer(
                CentralInstantAsStringSerializer, CoreUnitAdjustedSurrogate.serializer())
        ).toPair()
        return CoreUnitAdjusted(data.first, data.second)
    }
}

internal object CoreIntradaySerializer: KSerializer<CoreIntraday> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ForexIntraday") {
        element<CoreIntradayMetaData>("Meta Data")
        element<Map<Instant, CoreIntradaySurrogate>>("Time Series (1min)", isOptional = true)
        element<Map<Instant, CoreIntradaySurrogate>>("Time Series (5min)", isOptional = true)
        element<Map<Instant, CoreIntradaySurrogate>>("Time Series (15min)", isOptional = true)
        element<Map<Instant, CoreIntradaySurrogate>>("Time Series (30min)", isOptional = true)
        element<Map<Instant, CoreIntradaySurrogate>>("Time Series (60min)", isOptional = true)
    }

    override fun serialize(encoder: Encoder, value: CoreIntraday): Unit =
        encoder.encodeStructure(ForexIntradaySerializer.descriptor) {
            val descriptorIndex = when(value.metaData.interval.substringBefore("m")) {
                "1" -> 1
                "5" -> 2
                "15" -> 3
                "30" -> 4
                "60" -> 5
                else -> error("Interval not supported by this serializer")
            }
            encodeSerializableElement(descriptor, 0, CoreIntradayMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                descriptorIndex,
                MapSerializer(CentralInstantAsStringSerializer, CoreUnitSurrogate.serializer()),
                value.coreUnits.associate { it.timestamp to CoreUnitSurrogate(it.open, it.high, it.low, it.close, it.volume) }
            )
        }

    override fun deserialize(decoder: Decoder): CoreIntraday =
        CoreIntraday(decoder.decodeSerializableValue(CoreIntradaySurrogate.serializer()))

}

internal object CoreDailySerializer : KSerializer<CoreDaily> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CoreDaily") {
        element<CoreDailyMetaData>("Meta Data")
        element<Map<Instant, CoreDailySurrogate>>("Time Series (Daily)")
    }

    override fun serialize(encoder: Encoder, value: CoreDaily): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CoreDailyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CoreUnitSurrogate.serializer()),
                value.coreUnits.associate { it.timestamp to CoreUnitSurrogate(it.open, it.high, it.low, it.close, it.volume) }
            )
        }

    override fun deserialize(decoder: Decoder): CoreDaily =
        CoreDaily(decoder.decodeSerializableValue(CoreDailySurrogate.serializer()))
}

internal object CoreDailyAdjustedSerializer : KSerializer<CoreDailyAdjusted> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CoreDailyAdjusted") {
        element<CoreDailyAdjustedMetaData>("Meta Data")
        element<Map<Instant, CoreUnitAdjustedDailySurrogate>>("Time Series (Daily)")
    }

    override fun serialize(encoder: Encoder, value: CoreDailyAdjusted): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CoreDailyAdjustedMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CoreUnitAdjustedDailySurrogate.serializer()),
                value.adjustedCoreUnits.associate { it.timestamp to CoreUnitAdjustedDailySurrogate(it.open, it.high, it.low, it.close, it.adjustedClose, it.volume, it.dividendAmount, it.splitCoefficient) }
            )
        }

    override fun deserialize(decoder: Decoder): CoreDailyAdjusted =
        CoreDailyAdjusted(decoder.decodeSerializableValue(CoreDailyAdjustedSurrogate.serializer()))
}

internal object CoreWeeklySerializer : KSerializer<CoreWeekly> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CoreWeekly") {
        element<CoreWeeklyMetaData>("Meta Data")
        element<Map<Instant, CoreWeeklySurrogate>>("Weekly Time Series")
    }

    override fun serialize(encoder: Encoder, value: CoreWeekly): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CoreWeeklyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CoreUnitSurrogate.serializer()),
                value.coreUnits.associate { it.timestamp to CoreUnitSurrogate(it.open, it.high, it.low, it.close, it.volume) }
            )
        }

    override fun deserialize(decoder: Decoder): CoreWeekly =
        CoreWeekly(decoder.decodeSerializableValue(CoreWeeklySurrogate.serializer()))
}

internal object CoreWeeklyAdjustedSerializer : KSerializer<CoreWeeklyAdjusted> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CoreWeeklyAdjusted") {
        element<CoreWeeklyAdjustedMetaData>("Meta Data")
        element<Map<Instant, CoreUnitAdjustedSurrogate>>("Weekly Adjusted Time Series")
    }

    override fun serialize(encoder: Encoder, value: CoreWeeklyAdjusted): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CoreWeeklyAdjustedMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CoreUnitAdjustedSurrogate.serializer()),
                value.adjustedCoreUnits.associate { it.timestamp to CoreUnitAdjustedSurrogate(it.open, it.high, it.low, it.close, it.adjustedClose, it.volume, it.dividendAmount, it.splitCoefficient) }
            )
        }

    override fun deserialize(decoder: Decoder): CoreWeeklyAdjusted =
        CoreWeeklyAdjusted(decoder.decodeSerializableValue(CoreWeeklyAdjustedSurrogate.serializer()))
}

internal object CoreMonthlySerializer : KSerializer<CoreMonthly> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CoreMonthly") {
        element<CoreMonthlyMetaData>("Meta Data")
        element<Map<Instant, CoreMonthlySurrogate>>("Monthly Time Series")
    }

    override fun serialize(encoder: Encoder, value: CoreMonthly): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CoreMonthlyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CoreUnitSurrogate.serializer()),
                value.coreUnits.associate { it.timestamp to CoreUnitSurrogate(it.open, it.high, it.low, it.close, it.volume) }
            )
        }

    override fun deserialize(decoder: Decoder): CoreMonthly =
        CoreMonthly(decoder.decodeSerializableValue(CoreMonthlySurrogate.serializer()))
}

internal object CoreMonthlyAdjustedSerializer : KSerializer<CoreMonthlyAdjusted> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CoreMonthlyAdjusted") {
        element<CoreMonthlyAdjustedMetaData>("Meta Data")
        element<Map<Instant, CoreUnitAdjustedSurrogate>>("Monthly Adjusted Time Series")
    }

    override fun serialize(encoder: Encoder, value: CoreMonthlyAdjusted): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, CoreMonthlyAdjustedMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, CoreUnitAdjustedSurrogate.serializer()),
                value.adjustedCoreUnits.associate { it.timestamp to CoreUnitAdjustedSurrogate(it.open, it.high, it.low, it.close, it.adjustedClose, it.volume, it.dividendAmount, it.splitCoefficient) }
            )
        }

    override fun deserialize(decoder: Decoder): CoreMonthlyAdjusted =
        CoreMonthlyAdjusted(decoder.decodeSerializableValue(CoreMonthlyAdjustedSurrogate.serializer()))
}

internal object QuoteEndpointSerializer : KSerializer<QuoteEndpoint> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(String.serializer(), QuoteEndpointSurrogate.serializer()).descriptor

    override fun serialize(encoder: Encoder, value: QuoteEndpoint): Unit =
        encoder.encodeSerializableValue(MapEntrySerializer(String.serializer(), QuoteEndpointSurrogate.serializer()), ("Global Quote" to QuoteEndpointSurrogate(value)).toEntry())

    override fun deserialize(decoder: Decoder): QuoteEndpoint =
        QuoteEndpoint(decoder.decodeSerializableValue(MapEntrySerializer(String.serializer(), QuoteEndpointSurrogate.serializer())).value)
}

internal object TickerSearchUnitListSerializer : KSerializer<List<TickerSearchUnit>> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(String.serializer(), ListSerializer(TickerSearchUnit.serializer())).descriptor

    override fun serialize(encoder: Encoder, value: List<TickerSearchUnit>): Unit =
        encoder.encodeSerializableValue(MapEntrySerializer(String.serializer(), ListSerializer(TickerSearchUnit.serializer())), ("bestMatches" to value).toEntry())

    override fun deserialize(decoder: Decoder): List<TickerSearchUnit> =
        decoder.decodeSerializableValue(MapEntrySerializer(String.serializer(), ListSerializer(TickerSearchUnit.serializer()))).value

}