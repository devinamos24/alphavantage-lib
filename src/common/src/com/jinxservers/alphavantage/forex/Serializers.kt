package com.jinxservers.alphavantage.forex

import com.jinxservers.alphavantage.forex.response.*
import com.jinxservers.alphavantage.forex.response.ForexUnitSurrogate
import com.jinxservers.alphavantage.util.CentralInstantAsStringSerializer
import com.jinxservers.alphavantage.util.CentralInstantWithoutTimeAsStringSerializer
import com.jinxservers.alphavantage.util.toEntry
import kotlinx.datetime.Instant
import kotlinx.serialization.*
import kotlinx.serialization.builtins.MapEntrySerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.JsonNames

internal object CurrencyExchangeRateSerializer : KSerializer<CurrencyExchangeRate> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(String.serializer(), GeneratedCurrencyExchangeRateSerializer).descriptor

    override fun serialize(encoder: Encoder, value: CurrencyExchangeRate) {
        encoder.encodeSerializableValue(
            MapEntrySerializer(String.serializer(), GeneratedCurrencyExchangeRateSerializer),
            ("Realtime Currency Exchange Rate" to value).toEntry()
        )
    }

    override fun deserialize(decoder: Decoder): CurrencyExchangeRate {
        return decoder.decodeSerializableValue(MapEntrySerializer(String.serializer(), GeneratedCurrencyExchangeRateSerializer)).value
    }
}

// TODO: Use this instead when it is stable
//@OptIn(ExperimentalSerializationApi::class)
//@Serializer(forClass = CurrencyExchangeRate::class)
//internal object GeneratedCurrencyExchangeRateSerializer

private object GeneratedCurrencyExchangeRateSerializer : KSerializer<CurrencyExchangeRate> {
    override val descriptor: SerialDescriptor = CurrencyExchangeRateSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: CurrencyExchangeRate) {
        val surrogate = CurrencyExchangeRateSurrogate(value)
        encoder.encodeSerializableValue(CurrencyExchangeRateSurrogate.serializer(), surrogate)
    }

    override fun deserialize(decoder: Decoder): CurrencyExchangeRate {
        val surrogate = decoder.decodeSerializableValue(CurrencyExchangeRateSurrogate.serializer())
        return CurrencyExchangeRate(
            surrogate.fromCurrencyCode,
            surrogate.fromCurrencyName,
            surrogate.toCurrencyCode,
            surrogate.toCurrencyName,
            surrogate.exchangeRate,
            surrogate.lastRefreshed,
            surrogate.timeZone,
            surrogate.bidPrice,
            surrogate.askPrice
        )
    }
}

internal object ForexUnitSerializer : KSerializer<ForexUnit> {
    override val descriptor: SerialDescriptor = MapEntrySerializer(CentralInstantAsStringSerializer, ForexUnitSurrogate.serializer()).descriptor

    override fun serialize(encoder: Encoder, value: ForexUnit) {
        val surrogate = ForexUnitSurrogate(value.open, value.high, value.low, value.close)
        encoder.encodeSerializableValue(MapEntrySerializer(CentralInstantAsStringSerializer, ForexUnitSurrogate.serializer()), (value.timestamp to surrogate).toEntry())
    }

    override fun deserialize(decoder: Decoder): ForexUnit {
        val data: Pair<Instant, ForexUnitSurrogate> = decoder.decodeSerializableValue(
            MapEntrySerializer(
                CentralInstantAsStringSerializer, ForexUnitSurrogate.serializer())
        ).toPair()
        return ForexUnit(data.first, data.second)
    }
}

internal object ForexIntradaySerializer : KSerializer<ForexIntraday> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ForexIntraday") {
        element<ForexIntradayMetaData>("Meta Data")
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (1min)", isOptional = true)
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (5min)", isOptional = true)
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (15min)", isOptional = true)
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (30min)", isOptional = true)
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (60min)", isOptional = true)
    }

    override fun serialize(encoder: Encoder, value: ForexIntraday): Unit =
        encoder.encodeStructure(descriptor) {
            val descriptorIndex = when(value.metaData.interval.substringBefore("m")) {
                "1" -> 1
                "5" -> 2
                "15" -> 3
                "30" -> 4
                "60" -> 5
                else -> error("Interval not supported by this serializer")
            }
            encodeSerializableElement(descriptor, 0, ForexIntradayMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                descriptorIndex,
                MapSerializer(CentralInstantAsStringSerializer, ForexUnitSurrogate.serializer()),
                value.forexUnits.associate { it.timestamp to ForexUnitSurrogate(it.open, it.high, it.low, it.close) }
            )
        }

    override fun deserialize(decoder: Decoder): ForexIntraday =
        ForexIntraday(decoder.decodeSerializableValue(ForexIntradaySurrogate.serializer()))
}

internal object ForexDailySerializer : KSerializer<ForexDaily> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ForexDaily") {
        element<ForexIntradayMetaData>("Meta Data")
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (Daily)")
    }

    override fun serialize(encoder: Encoder, value: ForexDaily): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, ForexDailyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, ForexUnitSurrogate.serializer()),
                value.forexUnits.associate { it.timestamp to ForexUnitSurrogate(it.open, it.high, it.low, it.close) }
            )
        }

    override fun deserialize(decoder: Decoder): ForexDaily =
        ForexDaily(decoder.decodeSerializableValue(ForexDailySurrogate.serializer()))
}

internal object ForexWeeklySerializer : KSerializer<ForexWeekly> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ForexWeekly") {
        element<ForexIntradayMetaData>("Meta Data")
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (Weekly)")
    }

    override fun serialize(encoder: Encoder, value: ForexWeekly): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, ForexWeeklyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, ForexUnitSurrogate.serializer()),
                value.forexUnits.associate { it.timestamp to ForexUnitSurrogate(it.open, it.high, it.low, it.close) }
            )
        }

    override fun deserialize(decoder: Decoder): ForexWeekly =
        ForexWeekly(decoder.decodeSerializableValue(ForexWeeklySurrogate.serializer()))
}

internal object ForexMonthlySerializer : KSerializer<ForexMonthly> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ForexMonthly") {
        element<ForexIntradayMetaData>("Meta Data")
        element<Map<Instant, ForexIntradaySurrogate>>("Time Series FX (Monthly)")
    }

    override fun serialize(encoder: Encoder, value: ForexMonthly): Unit =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, ForexMonthlyMetaData.serializer(), value.metaData)
            encodeSerializableElement(
                descriptor,
                1,
                MapSerializer(CentralInstantWithoutTimeAsStringSerializer, ForexUnitSurrogate.serializer()),
                value.forexUnits.associate { it.timestamp to ForexUnitSurrogate(it.open, it.high, it.low, it.close) }
            )
        }

    override fun deserialize(decoder: Decoder): ForexMonthly =
        ForexMonthly(decoder.decodeSerializableValue(ForexMonthlySurrogate.serializer()))
}