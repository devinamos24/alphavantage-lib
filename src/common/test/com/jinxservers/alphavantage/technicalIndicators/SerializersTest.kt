package com.jinxservers.alphavantage.technicalIndicators

import com.jinxservers.alphavantage.technicalIndicators.response.*
import kotlinx.datetime.toInstant
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializersTest {

    private val json = Json {
        prettyPrint = true
        isLenient = true
    }

    @Test
    fun testSMASerializer() {

        val smaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Simple Moving Average (SMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-29\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: SMA\": {\n" +
                "        \"2024-04-29\": {\n" +
                "            \"SMA\": \"186.5070\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"SMA\": \"188.5310\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val sma = SMA(
            SMAMetaData(
                symbol = "IBM",
                indicator = "Simple Moving Average (SMA)",
                lastRefreshed = "2024-04-29T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern"
            ),
            listOf(
                SMAUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    sma = 186.5070,
                ),
                SMAUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    sma = 188.5310
                )
            )
        )

        val smaFromString: SMA = json.decodeFromString(smaString)
        val smaToString: String = json.encodeToString(SMA.serializer(), smaFromString)
        assertEquals(sma, smaFromString, "Decoded SMA does not match given SMA")
        assertEquals(smaString, smaToString, "Encoded SMA String does not match given SMA String")
    }

    @Test
    fun testEMASerializer() {

        val emaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Exponential Moving Average (EMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: EMA\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"EMA\": \"182.1485\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"EMA\": \"185.4260\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val ema = EMA(
            EMAMetaData(
                symbol = "IBM",
                indicator = "Exponential Moving Average (EMA)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern"
            ),
            listOf(
                EMAUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    ema = 182.1485,
                ),
                EMAUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    ema = 185.4260
                )
            )
        )

        val emaFromString: EMA = json.decodeFromString(emaString)
        val emaToString: String = json.encodeToString(EMA.serializer(), emaFromString)
        assertEquals(ema, emaFromString, "Decoded EMA does not match given EMA")
        assertEquals(emaString, emaToString, "Encoded EMA String does not match given EMA String")
    }

    @Test
    fun testWMASerializer() {

        val wmaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Weighted Moving Average (WMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: WMA\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"WMA\": \"184.1778\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"WMA\": \"188.0198\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val wma = WMA(
            WMAMetaData(
                symbol = "IBM",
                indicator = "Weighted Moving Average (WMA)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern"
            ),
            listOf(
                WMAUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    wma = 184.1778,
                ),
                WMAUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    wma = 188.0198
                )
            )
        )

        val wmaFromString: WMA = json.decodeFromString(wmaString)
        val wmaToString: String = json.encodeToString(WMA.serializer(), wmaFromString)
        assertEquals(wma, wmaFromString, "Decoded WMA does not match given WMA")
        assertEquals(wmaString, wmaToString, "Encoded WMA String does not match given WMA String")
    }

    @Test
    fun testDEMASerializer() {

        val demaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Double Exponential Moving Average (DEMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: DEMA\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"DEMA\": \"183.3156\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"DEMA\": \"190.1298\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val dema = DEMA(
            DEMAMetaData(
                symbol = "IBM",
                indicator = "Double Exponential Moving Average (DEMA)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern"
            ),
            listOf(
                DEMAUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    dema = 183.3156,
                ),
                DEMAUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    dema = 190.1298
                )
            )
        )

        val demaFromString: DEMA = json.decodeFromString(demaString)
        val demaToString: String = json.encodeToString(DEMA.serializer(), demaFromString)
        assertEquals(dema, demaFromString, "Decoded DEMA does not match given DEMA")
        assertEquals(demaString, demaToString, "Encoded DEMA String does not match given DEMA String")
    }

    @Test
    fun testTEMASerializer() {

        val temaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Triple Exponential Moving Average (TEMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: TEMA\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"TEMA\": \"177.5279\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"TEMA\": \"186.5928\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val tema = TEMA(
            TEMAMetaData(
                symbol = "IBM",
                indicator = "Triple Exponential Moving Average (TEMA)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern"
            ),
            listOf(
                TEMAUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    tema = 177.5279,
                ),
                TEMAUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    tema = 186.5928
                )
            )
        )

        val temaFromString: TEMA = json.decodeFromString(temaString)
        val temaToString: String = json.encodeToString(TEMA.serializer(), temaFromString)
        assertEquals(tema, temaFromString, "Decoded TEMA does not match given TEMA")
        assertEquals(temaString, temaToString, "Encoded TEMA String does not match given TEMA String")
    }

    @Test
    fun testTRIMASerializer() {

        val trimaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Triangular Exponential Moving Average (TRIMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: TRIMA\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"TRIMA\": \"188.6820\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"TRIMA\": \"189.7863\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val trima = TRIMA(
            TRIMAMetaData(
                symbol = "IBM",
                indicator = "Triangular Exponential Moving Average (TRIMA)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                TRIMAUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    trima = 188.6820,
                ),
                TRIMAUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    trima = 189.7863
                )
            )
        )

        val trimaFromString: TRIMA = json.decodeFromString(trimaString)
        val trimaToString: String = json.encodeToString(TRIMA.serializer(), trimaFromString)
        assertEquals(trima, trimaFromString, "Decoded TRIMA does not match given TRIMA")
        assertEquals(trimaString, trimaToString, "Encoded TRIMA String does not match given TRIMA String")
    }

    @Test
    fun testKAMASerializer() {

        val kamaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Kaufman Adaptive Moving Average (KAMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: KAMA\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"KAMA\": \"184.3248\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"KAMA\": \"187.0988\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val kama = KAMA(
            KAMAMetaData(
                symbol = "IBM",
                indicator = "Kaufman Adaptive Moving Average (KAMA)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                KAMAUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    kama = 184.3248,
                ),
                KAMAUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    kama = 187.0988
                )
            )
        )

        val kamaFromString: KAMA = json.decodeFromString(kamaString)
        val kamaToString: String = json.encodeToString(KAMA.serializer(), kamaFromString)
        assertEquals(kama, kamaFromString, "Decoded KAMA does not match given KAMA")
        assertEquals(kamaString, kamaToString, "Encoded KAMA String does not match given KAMA String")
    }

    @Test
    fun testMAMASerializer() {

        val mamaString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"MESA Adaptive Moving Average (MAMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: Fast Limit\": 0.02,\n" +
                "        \"5.2: Slow Limit\": 0.01,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MAMA\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MAMA\": \"170.1305\",\n" +
                "            \"FAMA\": \"142.2279\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"MAMA\": \"170.2109\",\n" +
                "            \"FAMA\": \"141.9460\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val mama = MAMA(
            MAMAMetaData(
                symbol = "IBM",
                indicator = "MESA Adaptive Moving Average (MAMA)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastLimit = 0.02,
                slowLimit = 0.01,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MAMAUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    mama = 170.1305,
                    fama = 142.2279
                ),
                MAMAUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    mama = 170.2109,
                    fama = 141.9460
                )
            )
        )

        val mamaFromString: MAMA = json.decodeFromString(mamaString)
        val mamaToString: String = json.encodeToString(MAMA.serializer(), mamaFromString)
        assertEquals(mama, mamaFromString, "Decoded MAMA does not match given MAMA")
        assertEquals(mamaString, mamaToString, "Encoded MAMA String does not match given MAMA String")
    }

    @Test
    fun testVWAPSerializer() {

        val vwapString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Volume Weighted Average Price (VWAP)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-29 19:45:00\",\n" +
                "        \"4: Interval\": \"15min\",\n" +
                "        \"5: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: VWAP\": {\n" +
                "        \"2024-04-29 19:45\": {\n" +
                "            \"VWAP\": \"167.1025\"\n" +
                "        },\n" +
                "        \"2024-04-29 19:30\": {\n" +
                "            \"VWAP\": \"167.1024\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val vwap = VWAP(
            VWAPMetaData(
                symbol = "IBM",
                indicator = "Volume Weighted Average Price (VWAP)",
                lastRefreshed = "2024-04-29T19:45:00Z".toInstant(),
                interval = "15min",
                timeZone = "US/Eastern"
            ),
            listOf(
                VWAPUnit(
                    timestamp = "2024-04-29T19:45:00Z".toInstant(),
                    vwap = 167.1025,
                ),
                VWAPUnit(
                    timestamp = "2024-04-29T19:30:00Z".toInstant(),
                    vwap = 167.1024,
                )
            )
        )

        val vwapFromString: VWAP = json.decodeFromString(vwapString)
        val vwapToString: String = json.encodeToString(VWAP.serializer(), vwapFromString)
        assertEquals(vwap, vwapFromString, "Decoded VWAP does not match given VWAP")
        assertEquals(vwapString, vwapToString, "Encoded VWAP String does not match given VWAP String")
    }

    @Test
    fun testT3Serializer() {

        val t3String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Triple Exponential Moving Average (T3)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Volume Factor (vFactor)\": 0.7,\n" +
                "        \"7: Series Type\": \"open\",\n" +
                "        \"8: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: T3\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"T3\": \"192.2966\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"T3\": \"192.2405\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val t3 = T3(
            T3MetaData(
                symbol = "IBM",
                indicator = "Triple Exponential Moving Average (T3)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                volumeFactor = 0.7,
                seriesType = "open",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                T3Unit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    t3 = 192.2966,
                ),
                T3Unit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    t3 = 192.2405,
                )
            )
        )

        val t3FromString: T3 = json.decodeFromString(t3String)
        val t3ToString: String = json.encodeToString(T3.serializer(), t3FromString)
        assertEquals(t3, t3FromString, "Decoded T3 does not match given T3")
        assertEquals(t3String, t3ToString, "Encoded T3 String does not match given T3 String")
    }

    @Test
    fun testMACDSerializer() {

        val macdString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Moving Average Convergence/Divergence (MACD)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: Fast Period\": 12,\n" +
                "        \"5.2: Slow Period\": 26,\n" +
                "        \"5.3: Signal Period\": 9,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MACD\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MACD\": \"-5.1827\",\n" +
                "            \"MACD_Signal\": \"-3.0730\",\n" +
                "            \"MACD_Hist\": \"-2.1097\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"MACD\": \"-4.6126\",\n" +
                "            \"MACD_Signal\": \"-2.5456\",\n" +
                "            \"MACD_Hist\": \"-2.0670\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val macd = MACD(
            MACDMetaData(
                symbol = "IBM",
                indicator = "Moving Average Convergence/Divergence (MACD)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastPeriod = 12,
                slowPeriod = 26,
                signalPeriod = 9,
                seriesType = "open",
                timeZone = "US/Eastern"
            ),
            listOf(
                MACDUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    macd = -5.1827,
                    macdSignal = -3.0730,
                    macdHist = -2.1097
                ),
                MACDUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    macd = -4.6126,
                    macdSignal = -2.5456,
                    macdHist = -2.0670
                )
            )
        )

        val macdFromString: MACD = json.decodeFromString(macdString)
        val macdToString: String = json.encodeToString(MACD.serializer(), macdFromString)
        assertEquals(macd, macdFromString, "Decoded MACD does not match given MACD")
        assertEquals(macdString, macdToString, "Encoded MACD String does not match given MACD String")
    }

    @Test
    fun testMACDEXTSerializer() {

        val macdextString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"MACD with Controllable MA Type (MACDEXT)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: Fast Period\": 12,\n" +
                "        \"5.2: Slow Period\": 26,\n" +
                "        \"5.3: Signal Period\": 9,\n" +
                "        \"5.4: Fast MA Type\": 0,\n" +
                "        \"5.5: Slow MA Type\": 0,\n" +
                "        \"5.6: Signal MA Type\": 0,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MACDEXT\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MACD\": \"-5.8081\",\n" +
                "            \"MACD_Signal\": \"-3.7796\",\n" +
                "            \"MACD_Hist\": \"-2.0285\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"MACD\": \"-5.3301\",\n" +
                "            \"MACD_Signal\": \"-3.4069\",\n" +
                "            \"MACD_Hist\": \"-1.9232\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val macdext = MACDEXT(
            MACDEXTMetaData(
                symbol = "IBM",
                indicator = "MACD with Controllable MA Type (MACDEXT)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastPeriod = 12,
                slowPeriod = 26,
                signalPeriod = 9,
                fastMAType = 0,
                slowMAType = 0,
                signalMAType = 0,
                seriesType = "open",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MACDEXTUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    macd = -5.8081,
                    macdSignal = -3.7796,
                    macdHist = -2.0285
                ),
                MACDEXTUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    macd = -5.3301,
                    macdSignal = -3.4069,
                    macdHist = -1.9232
                )
            )
        )

        val macdextFromString: MACDEXT = json.decodeFromString(macdextString)
        val macdextToString: String = json.encodeToString(MACDEXT.serializer(), macdextFromString)
        assertEquals(macdext, macdextFromString, "Decoded MACDEXT does not match given MACDEXT")
        assertEquals(macdextString, macdextToString, "Encoded MACDEXT String does not match given MACDEXT String")
    }

    @Test
    fun testSTOCHSerializer() {

        val stochString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Stochastic (STOCH)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: FastK Period\": 5,\n" +
                "        \"5.2: SlowK Period\": 3,\n" +
                "        \"5.3: SlowK MA Type\": 0,\n" +
                "        \"5.4: SlowD Period\": 3,\n" +
                "        \"5.5: SlowD MA Type\": 0,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: STOCH\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"SlowK\": \"7.3239\",\n" +
                "            \"SlowD\": \"18.9665\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"SlowK\": \"11.3740\",\n" +
                "            \"SlowD\": \"34.6405\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val stoch = STOCH(
            STOCHMetaData(
                symbol = "IBM",
                indicator = "Stochastic (STOCH)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastKPeriod = 5,
                slowKPeriod = 3,
                slowKMAType = 0,
                slowDPeriod = 3,
                slowDMAType = 0,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                STOCHUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    slowK = 7.3239,
                    slowD = 18.9665
                ),
                STOCHUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    slowK = 11.3740,
                    slowD = 34.6405
                )
            )
        )

        val stochFromString: STOCH = json.decodeFromString(stochString)
        val stochToString: String = json.encodeToString(STOCH.serializer(), stochFromString)
        assertEquals(stoch, stochFromString, "Decoded STOCH does not match given STOCH")
        assertEquals(stochString, stochToString, "Encoded STOCH String does not match given STOCH String")
    }

    @Test
    fun testSTOCHFSerializer() {

        val stochfString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Stochastic Fast (STOCHF)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: FastK Period\": 5,\n" +
                "        \"5.2: FastD Period\": 3,\n" +
                "        \"5.3: FastD MA Type\": 0,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: STOCHF\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"FastK\": \"4.9371\",\n" +
                "            \"FastD\": \"7.3239\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"FastK\": \"9.3060\",\n" +
                "            \"FastD\": \"11.3740\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val stochf = STOCHF(
            STOCHFMetaData(
                symbol = "IBM",
                indicator = "Stochastic Fast (STOCHF)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastKPeriod = 5,
                fastDPeriod = 3,
                fastDMAType = 0,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                STOCHFUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    fastK = 4.9371,
                    fastD = 7.3239
                ),
                STOCHFUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    fastK = 9.3060,
                    fastD = 11.3740
                )
            )
        )

        val stochfFromString: STOCHF = json.decodeFromString(stochfString)
        val stochfToString: String = json.encodeToString(STOCHF.serializer(), stochfFromString)
        assertEquals(stochf, stochfFromString, "Decoded STOCHF does not match given STOCHF")
        assertEquals(stochfString, stochfToString, "Encoded STOCHF String does not match given STOCHF String")
    }

    @Test
    fun testRSISerializer() {

        val rsiString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Relative Strength Index (RSI)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: RSI\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"RSI\": \"36.4046\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"RSI\": \"56.1337\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val rsi = RSI(
            RSIMetaData(
                symbol = "IBM",
                indicator = "Relative Strength Index (RSI)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                RSIUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    rsi = 36.4046,
                ),
                RSIUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    rsi = 56.1337,
                )
            )
        )

        val rsiFromString: RSI = json.decodeFromString(rsiString)
        val rsiToString: String = json.encodeToString(RSI.serializer(), rsiFromString)
        assertEquals(rsi, rsiFromString, "Decoded RSI does not match given RSI")
        assertEquals(rsiString, rsiToString, "Encoded RSI String does not match given RSI String")
    }

    @Test
    fun testSTOCHRSISerializer() {

        val stochrsiString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Stochastic Relative Strength Index (STOCHRSI)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-29\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6.1: FastK Period\": 6,\n" +
                "        \"6.2: FastD Period\": 3,\n" +
                "        \"6.3: FastD MA Type\": 1,\n" +
                "        \"7: Series Type\": \"close\",\n" +
                "        \"8: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: STOCHRSI\": {\n" +
                "        \"2024-04-29\": {\n" +
                "            \"FastK\": \"3.7857\",\n" +
                "            \"FastD\": \"12.2252\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"FastK\": \"0.0000\",\n" +
                "            \"FastD\": \"20.6646\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val stochrsi = STOCHRSI(
            STOCHRSIMetaData(
                symbol = "IBM",
                indicator = "Stochastic Relative Strength Index (STOCHRSI)",
                lastRefreshed = "2024-04-29T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                fastKPeriod = 6,
                fastDPeriod = 3,
                fastDMAType = 1,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                STOCHRSIUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    fastK = 3.7857,
                    fastD = 12.2252
                ),
                STOCHRSIUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    fastK = 0.0000,
                    fastD = 20.6646
                )
            )
        )

        val stochrsiFromString: STOCHRSI = json.decodeFromString(stochrsiString)
        val stochrsiToString: String = json.encodeToString(STOCHRSI.serializer(), stochrsiFromString)
        assertEquals(stochrsi, stochrsiFromString, "Decoded STOCHRSI does not match given STOCHRSI")
        assertEquals(stochrsiString, stochrsiToString, "Encoded STOCHRSI String does not match given STOCHRSI String")
    }

    @Test
    fun testWILLRSerializer() {

        val willrString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Williams' %R (WILLR)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: WILLR\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"WILLR\": \"-95.1621\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"WILLR\": \"-91.1721\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val willr = WILLR(
            WILLRMetaData(
                symbol = "IBM",
                indicator = "Williams' %R (WILLR)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                WILLRUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    willr = -95.1621,
                ),
                WILLRUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    willr = -91.1721,
                )
            )
        )

        val willrFromString: WILLR = json.decodeFromString(willrString)
        val willrToString: String = json.encodeToString(WILLR.serializer(), willrFromString)
        assertEquals(willr, willrFromString, "Decoded WILLR does not match given WILLR")
        assertEquals(willrString, willrToString, "Encoded WILLR String does not match given WILLR String")
    }

    @Test
    fun testADXSerializer() {

        val adxString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Average Directional Movement Index (ADX)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: ADX\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"ADX\": \"42.4207\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"ADX\": \"38.7946\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val adx = ADX(
            ADXMetaData(
                symbol = "IBM",
                indicator = "Average Directional Movement Index (ADX)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ADXUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    adx = 42.4207,
                ),
                ADXUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    adx = 38.7946,
                )
            )
        )

        val adxFromString: ADX = json.decodeFromString(adxString)
        val adxToString: String = json.encodeToString(ADX.serializer(), adxFromString)
        assertEquals(adx, adxFromString, "Decoded ADX does not match given ADX")
        assertEquals(adxString, adxToString, "Encoded ADX String does not match given ADX String")
    }

    @Test
    fun testADXRSerializer() {

        val adxrString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Average Directional Movement Index Rating (ADXR)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: ADXR\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"ADXR\": \"30.3991\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"ADXR\": \"28.1846\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val adxr = ADXR(
            ADXRMetaData(
                symbol = "IBM",
                indicator = "Average Directional Movement Index Rating (ADXR)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ADXRUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    adxr = 30.3991,
                ),
                ADXRUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    adxr = 28.1846,
                )
            )
        )

        val adxrFromString: ADXR = json.decodeFromString(adxrString)
        val adxrToString: String = json.encodeToString(ADXR.serializer(), adxrFromString)
        assertEquals(adxr, adxrFromString, "Decoded ADXR does not match given ADXR")
        assertEquals(adxrString, adxrToString, "Encoded ADXR String does not match given ADXR String")
    }

    @Test
    fun testAPOSerializer() {

        val apoString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Absolute Price Oscillator (APO)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: Fast Period\": 10,\n" +
                "        \"5.2: Slow Period\": 26,\n" +
                "        \"5.3: MA Type\": 1,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: APO\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"APO\": \"-6.3636\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"APO\": \"-5.6876\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val apo = APO(
            APOMetaData(
                symbol = "IBM",
                indicator = "Absolute Price Oscillator (APO)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastPeriod = 10,
                slowPeriod = 26,
                maType = 1,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                APOUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    apo = -6.3636,
                ),
                APOUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    apo = -5.6876,
                )
            )
        )

        val apoFromString: APO = json.decodeFromString(apoString)
        val apoToString: String = json.encodeToString(APO.serializer(), apoFromString)
        assertEquals(apo, apoFromString, "Decoded APO does not match given APO")
        assertEquals(apoString, apoToString, "Encoded APO String does not match given APO String")
    }

    @Test
    fun testPPOSerializer() {

        val ppoString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Percentage Price Oscillator (PPO)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: Fast Period\": 10,\n" +
                "        \"5.2: Slow Period\": 26,\n" +
                "        \"5.3: MA Type\": 1,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: PPO\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"PPO\": \"-3.5178\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"PPO\": \"-3.1238\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val ppo = PPO(
            PPOMetaData(
                symbol = "IBM",
                indicator = "Percentage Price Oscillator (PPO)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastPeriod = 10,
                slowPeriod = 26,
                maType = 1,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                PPOUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    ppo = -3.5178,
                ),
                PPOUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    ppo = -3.1238,
                )
            )
        )

        val ppoFromString: PPO = json.decodeFromString(ppoString)
        val ppoToString: String = json.encodeToString(PPO.serializer(), ppoFromString)
        assertEquals(ppo, ppoFromString, "Decoded PPO does not match given PPO")
        assertEquals(ppoString, ppoToString, "Encoded PPO String does not match given PPO String")
    }

    @Test
    fun testMOMSerializer() {

        val momString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Momentum (MOM)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MOM\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MOM\": \"-17.5500\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"MOM\": \"-13.8200\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val mom = MOM(
            MOMMetaData(
                symbol = "IBM",
                indicator = "Momentum (MOM)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MOMUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    mom = -17.5500,
                ),
                MOMUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    mom = -13.8200,
                )
            )
        )

        val momFromString: MOM = json.decodeFromString(momString)
        val momToString: String = json.encodeToString(MOM.serializer(), momFromString)
        assertEquals(mom, momFromString, "Decoded MOM does not match given MOM")
        assertEquals(momString, momToString, "Encoded MOM String does not match given MOM String")
    }

    @Test
    fun testBOPSerializer() {

        val bopString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Balance Of Power (BOP)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: BOP\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"BOP\": \"-0.1934\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"BOP\": \"0.0150\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val bop = BOP(
            BOPMetaData(
                symbol = "IBM",
                indicator = "Balance Of Power (BOP)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                BOPUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    bop = -0.1934,
                ),
                BOPUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    bop = 0.0150,
                )
            )
        )

        val bopFromString: BOP = json.decodeFromString(bopString)
        val bopToString: String = json.encodeToString(BOP.serializer(), bopFromString)
        assertEquals(bop, bopFromString, "Decoded BOP does not match given BOP")
        assertEquals(bopString, bopToString, "Encoded BOP String does not match given BOP String")
    }

    @Test
    fun testCCISerializer() {

        val cciString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Commodity Channel Index (CCI)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: CCI\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"CCI\": \"-94.9154\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"CCI\": \"-115.8780\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val cci = CCI(
            CCIMetaData(
                symbol = "IBM",
                indicator = "Commodity Channel Index (CCI)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                CCIUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    cci = -94.9154,
                ),
                CCIUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    cci = -115.8780,
                )
            )
        )

        val cciFromString: CCI = json.decodeFromString(cciString)
        val cciToString: String = json.encodeToString(CCI.serializer(), cciFromString)
        assertEquals(cci, cciFromString, "Decoded CCI does not match given CCI")
        assertEquals(cciString, cciToString, "Encoded CCI String does not match given CCI String")
    }

    @Test
    fun testCMOSerializer() {

        val cmoString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Chande Momentum Oscillator (CMO)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: CMO\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"CMO\": \"-28.6154\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"CMO\": \"-26.9141\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val cmo = CMO(
            CMOMetaData(
                symbol = "IBM",
                indicator = "Chande Momentum Oscillator (CMO)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                CMOUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    cmo = -28.6154,
                ),
                CMOUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    cmo = -26.9141,
                )
            )
        )

        val cmoFromString: CMO = json.decodeFromString(cmoString)
        val cmoToString: String = json.encodeToString(CMO.serializer(), cmoFromString)
        assertEquals(cmo, cmoFromString, "Decoded CMO does not match given CMO")
        assertEquals(cmoString, cmoToString, "Encoded CMO String does not match given CMO String")
    }

    @Test
    fun testROCSerializer() {

        val rocString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Rate of change : ((price/prevPrice)-1)*100\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: ROC\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"ROC\": \"-10.5104\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"ROC\": \"-10.9305\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val roc = ROC(
            ROCMetaData(
                symbol = "IBM",
                indicator = "Rate of change : ((price/prevPrice)-1)*100",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ROCUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    roc = -10.5104,
                ),
                ROCUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    roc = -10.9305,
                )
            )
        )

        val rocFromString: ROC = json.decodeFromString(rocString)
        val rocToString: String = json.encodeToString(ROC.serializer(), rocFromString)
        assertEquals(roc, rocFromString, "Decoded ROC does not match given ROC")
        assertEquals(rocString, rocToString, "Encoded ROC String does not match given ROC String")
    }

    @Test
    fun testROCRSerializer() {

        val rocrString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Rate of change ratio: (price/prevPrice)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: ROCR\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"ROCR\": \"0.9045\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"ROCR\": \"0.9238\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val rocr = ROCR(
            ROCRMetaData(
                symbol = "IBM",
                indicator = "Rate of change ratio: (price/prevPrice)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ROCRUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    rocr = 0.9045,
                ),
                ROCRUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    rocr = 0.9238,
                )
            )
        )

        val rocrFromString: ROCR = json.decodeFromString(rocrString)
        val rocrToString: String = json.encodeToString(ROCR.serializer(), rocrFromString)
        assertEquals(rocr, rocrFromString, "Decoded ROCR does not match given ROCR")
        assertEquals(rocrString, rocrToString, "Encoded ROCR String does not match given ROCR String")
    }

    @Test
    fun testAROONSerializer() {

        val aroonString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Aroon (AROON)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 14,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: AROON\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"Aroon Down\": \"100.0000\",\n" +
                "            \"Aroon Up\": \"0.0000\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"Aroon Down\": \"85.7143\",\n" +
                "            \"Aroon Up\": \"0.0000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val aroon = AROON(
            AROONMetaData(
                symbol = "IBM",
                indicator = "Aroon (AROON)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 14,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                AROONUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    aroonDown = 100.0000,
                    aroonUp = 0.0000
                ),
                AROONUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    aroonDown = 85.7143,
                    aroonUp = 0.0000
                )
            )
        )

        val aroonFromString: AROON = json.decodeFromString(aroonString)
        val aroonToString: String = json.encodeToString(AROON.serializer(), aroonFromString)
        assertEquals(aroon, aroonFromString, "Decoded AROON does not match given AROON")
        assertEquals(aroonString, aroonToString, "Encoded AROON String does not match given AROON String")
    }

    @Test
    fun testAROONOSCSerializer() {

        val aroonoscString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Aroon Oscillator (AROONOSC)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: AROONOSC\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"AROONOSC\": \"-100.0000\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"AROONOSC\": \"-80.0000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val aroonosc = AROONOSC(
            AROONOSCMetaData(
                symbol = "IBM",
                indicator = "Aroon Oscillator (AROONOSC)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                AROONOSCUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    aroonosc = -100.0000,
                ),
                AROONOSCUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    aroonosc = -80.0000,
                )
            )
        )

        val aroonoscFromString: AROONOSC = json.decodeFromString(aroonoscString)
        val aroonoscToString: String = json.encodeToString(AROONOSC.serializer(), aroonoscFromString)
        assertEquals(aroonosc, aroonoscFromString, "Decoded AROONOSC does not match given AROONOSC")
        assertEquals(aroonoscString, aroonoscToString, "Encoded AROONOSC String does not match given AROONOSC String")
    }

    @Test
    fun testMFISerializer() {

        val mfiString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Money Flow Index (MFI)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MFI\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MFI\": \"24.3090\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"MFI\": \"23.5182\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val mfi = MFI(
            MFIMetaData(
                symbol = "IBM",
                indicator = "Money Flow Index (MFI)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MFIUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    mfi = 24.3090,
                ),
                MFIUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    mfi = 23.5182,
                )
            )
        )

        val mfiFromString: MFI = json.decodeFromString(mfiString)
        val mfiToString: String = json.encodeToString(MFI.serializer(), mfiFromString)
        assertEquals(mfi, mfiFromString, "Decoded MFI does not match given MFI")
        assertEquals(mfiString, mfiToString, "Encoded MFI String does not match given MFI String")
    }

    @Test
    fun testTRIXSerializer() {

        val trixString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"1-day Rate-Of-Change (ROC) of a Triple Smooth EMA (TRIX)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: TRIX\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"TRIX\": \"-0.4588\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"TRIX\": \"-0.3948\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val trix = TRIX(
            TRIXMetaData(
                symbol = "IBM",
                indicator = "1-day Rate-Of-Change (ROC) of a Triple Smooth EMA (TRIX)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                TRIXUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    trix = -0.4588,
                ),
                TRIXUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    trix = -0.3948,
                )
            )
        )

        val trixFromString: TRIX = json.decodeFromString(trixString)
        val trixToString: String = json.encodeToString(TRIX.serializer(), trixFromString)
        assertEquals(trix, trixFromString, "Decoded TRIX does not match given TRIX")
        assertEquals(trixString, trixToString, "Encoded TRIX String does not match given TRIX String")
    }

    @Test
    fun testULTOSCSerializer() {

        val ultoscString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Ultimate Oscillator (ULTOSC)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: Time Period 1\": 8,\n" +
                "        \"5.2: Time Period 2\": 14,\n" +
                "        \"5.3: Time Period 3\": 28,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: ULTOSC\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"ULTOSC\": \"37.5068\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"ULTOSC\": \"37.1788\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val ultosc = ULTOSC(
            ULTOSCMetaData(
                symbol = "IBM",
                indicator = "Ultimate Oscillator (ULTOSC)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod1 = 8,
                timePeriod2 = 14,
                timePeriod3 = 28,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ULTOSCUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    ultosc = 37.5068,
                ),
                ULTOSCUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    ultosc = 37.1788,
                )
            )
        )

        val ultoscFromString: ULTOSC = json.decodeFromString(ultoscString)
        val ultoscToString: String = json.encodeToString(ULTOSC.serializer(), ultoscFromString)
        assertEquals(ultosc, ultoscFromString, "Decoded ULTOSC does not match given ULTOSC")
        assertEquals(ultoscString, ultoscToString, "Encoded ULTOSC String does not match given ULTOSC String")
    }

    @Test
    fun testDXSerializer() {

        val dxString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Directional Movement Index (DX)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: DX\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"DX\": \"75.0558\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"DX\": \"73.7613\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val dx = DX(
            DXMetaData(
                symbol = "IBM",
                indicator = "Directional Movement Index (DX)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                DXUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    dx = 75.0558,
                ),
                DXUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    dx = 73.7613,
                )
            )
        )

        val dxFromString: DX = json.decodeFromString(dxString)
        val dxToString: String = json.encodeToString(DX.serializer(), dxFromString)
        assertEquals(dx, dxFromString, "Decoded DX does not match given DX")
        assertEquals(dxString, dxToString, "Encoded DX String does not match given DX String")
    }

    @Test
    fun testMINUSDISerializer() {

        val minusdiString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Minus Directional Indicator (MINUS_DI)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MINUS_DI\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MINUS_DI\": \"29.1749\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"MINUS_DI\": \"29.7994\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val minusdi = MINUSDI(
            MINUSDIMetaData(
                symbol = "IBM",
                indicator = "Minus Directional Indicator (MINUS_DI)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MINUSDIUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    minusdi = 29.1749,
                ),
                MINUSDIUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    minusdi = 29.7994,
                )
            )
        )

        val minusdiFromString: MINUSDI = json.decodeFromString(minusdiString)
        val minusdiToString: String = json.encodeToString(MINUSDI.serializer(), minusdiFromString)
        assertEquals(minusdi, minusdiFromString, "Decoded MINUSDI does not match given MINUSDI")
        assertEquals(minusdiString, minusdiToString, "Encoded MINUSDI String does not match given MINUSDI String")
    }

    @Test
    fun testPLUSDISerializer() {

        val plusdiString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Plus Directional Indicator (PLUS_DI)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: PLUS_DI\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"PLUS_DI\": \"5.6721\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"PLUS_DI\": \"5.9736\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val plusdi = PLUSDI(
            PLUSDIMetaData(
                symbol = "IBM",
                indicator = "Plus Directional Indicator (PLUS_DI)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                PLUSDIUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    plusdi = 5.6721,
                ),
                PLUSDIUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    plusdi = 5.9736,
                )
            )
        )

        val plusdiFromString: PLUSDI = json.decodeFromString(plusdiString)
        val plusdiToString: String = json.encodeToString(PLUSDI.serializer(), plusdiFromString)
        assertEquals(plusdi, plusdiFromString, "Decoded PLUSDI does not match given PLUSDI")
        assertEquals(plusdiString, plusdiToString, "Encoded PLUSDI String does not match given PLUSDI String")
    }

    @Test
    fun testMINUSDMSerializer() {

        val minusdmString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Minus Directional Movement (MINUS_DM)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MINUS_DM\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MINUS_DM\": \"17.1106\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"MINUS_DM\": \"17.9401\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val minusdm = MINUSDM(
            MINUSDMMetaData(
                symbol = "IBM",
                indicator = "Minus Directional Movement (MINUS_DM)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MINUSDMUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    minusdm = 17.1106,
                ),
                MINUSDMUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    minusdm = 17.9401,
                )
            )
        )

        val minusdmFromString: MINUSDM = json.decodeFromString(minusdmString)
        val minusdmToString: String = json.encodeToString(MINUSDM.serializer(), minusdmFromString)
        assertEquals(minusdm, minusdmFromString, "Decoded MINUSDM does not match given MINUSDM")
        assertEquals(minusdmString, minusdmToString, "Encoded MINUSDM String does not match given MINUSDM String")
    }

    @Test
    fun testPLUSDMSerializer() {

        val plusdmString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Plus Directional Movement (PLUS_DM)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: PLUS_DM\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"PLUS_DM\": \"2.4381\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"PLUS_DM\": \"2.7090\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val plusdm = PLUSDM(
            PLUSDMMetaData(
                symbol = "IBM",
                indicator = "Plus Directional Movement (PLUS_DM)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                PLUSDMUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    plusdm = 2.4381,
                ),
                PLUSDMUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    plusdm = 2.7090,
                )
            )
        )

        val plusdmFromString: PLUSDM = json.decodeFromString(plusdmString)
        val plusdmToString: String = json.encodeToString(PLUSDM.serializer(), plusdmFromString)
        assertEquals(plusdm, plusdmFromString, "Decoded PLUSDM does not match given PLUSDM")
        assertEquals(plusdmString, plusdmToString, "Encoded PLUSDM String does not match given PLUSDM String")
    }

    @Test
    fun testBBANDSSerializer() {

        val bbandsString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Bollinger Bands (BBANDS)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 5,\n" +
                "        \"6.1: Deviation multiplier for upper band\": 3,\n" +
                "        \"6.2: Deviation multiplier for lower band\": 3,\n" +
                "        \"6.3: MA Type\": 0,\n" +
                "        \"7: Series Type\": \"close\",\n" +
                "        \"8: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: BBANDS\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"Real Upper Band\": \"204.4248\",\n" +
                "            \"Real Middle Band\": \"177.2640\",\n" +
                "            \"Real Lower Band\": \"150.1032\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"Real Upper Band\": \"207.4015\",\n" +
                "            \"Real Middle Band\": \"182.2160\",\n" +
                "            \"Real Lower Band\": \"157.0305\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val bbands = BBANDS(
            BBANDSMetaData(
                symbol = "IBM",
                indicator = "Bollinger Bands (BBANDS)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 5,
                upperBandDeviationMultiplier = 3,
                lowerBandDeviationMultiplier = 3,
                maType = 0,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                BBANDSUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    realUpperBand = 204.4248,
                    realMiddleBand = 177.2640,
                    realLowerBand = 150.1032
                ),
                BBANDSUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    realUpperBand = 207.4015,
                    realMiddleBand = 182.2160,
                    realLowerBand = 157.0305
                )
            )
        )

        val bbandsFromString: BBANDS = json.decodeFromString(bbandsString)
        val bbandsToString: String = json.encodeToString(BBANDS.serializer(), bbandsFromString)
        assertEquals(bbands, bbandsFromString, "Decoded BBANDS does not match given BBANDS")
        assertEquals(bbandsString, bbandsToString, "Encoded BBANDS String does not match given BBANDS String")
    }

    @Test
    fun testMIDPOINTSerializer() {

        val midpointString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"MidPoint over period (MIDPOINT)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"close\",\n" +
                "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MIDPOINT\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MIDPOINT\": \"175.1500\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"MIDPOINT\": \"175.6150\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val midpoint = MIDPOINT(
            MIDPOINTMetaData(
                symbol = "IBM",
                indicator = "MidPoint over period (MIDPOINT)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MIDPOINTUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    midpoint = 175.1500,
                ),
                MIDPOINTUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    midpoint = 175.6150,
                )
            )
        )

        val midpointFromString: MIDPOINT = json.decodeFromString(midpointString)
        val midpointToString: String = json.encodeToString(MIDPOINT.serializer(), midpointFromString)
        assertEquals(midpoint, midpointFromString, "Decoded MIDPOINT does not match given MIDPOINT")
        assertEquals(midpointString, midpointToString, "Encoded MIDPOINT String does not match given MIDPOINT String")
    }

    @Test
    fun testMIDPRICESerializer() {

        val midpriceString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Midpoint Price over period (MIDPRICE)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: MIDPRICE\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"MIDPRICE\": \"174.9703\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"MIDPRICE\": \"175.6850\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val midprice = MIDPRICE(
            MIDPRICEMetaData(
                symbol = "IBM",
                indicator = "Midpoint Price over period (MIDPRICE)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                MIDPRICEUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    midprice = 174.9703,
                ),
                MIDPRICEUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    midprice = 175.6850,
                )
            )
        )

        val midpriceFromString: MIDPRICE = json.decodeFromString(midpriceString)
        val midpriceToString: String = json.encodeToString(MIDPRICE.serializer(), midpriceFromString)
        assertEquals(midprice, midpriceFromString, "Decoded MIDPRICE does not match given MIDPRICE")
        assertEquals(midpriceString, midpriceToString, "Encoded MIDPRICE String does not match given MIDPRICE String")
    }

    @Test
    fun testSARSerializer() {

        val sarString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Parabolic SAR (SAR)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5.1: Acceleration\": 0.05,\n" +
                "        \"5.2: Maximum\": 0.25,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: SAR\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"SAR\": \"187.4800\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"SAR\": \"192.1467\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val sar = SAR(
            SARMetaData(
                symbol = "IBM",
                indicator = "Parabolic SAR (SAR)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                acceleration = 0.05,
                maximum = 0.25,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                SARUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    sar = 187.4800,
                ),
                SARUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    sar = 192.1467,
                )
            )
        )

        val sarFromString: SAR = json.decodeFromString(sarString)
        val sarToString: String = json.encodeToString(SAR.serializer(), sarFromString)
        assertEquals(sar, sarFromString, "Decoded SAR does not match given SAR")
        assertEquals(sarString, sarToString, "Encoded SAR String does not match given SAR String")
    }

    @Test
    fun testTRANGESerializer() {

        val trangeString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"True Range (TRANGE)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: TRANGE\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"TRANGE\": \"2.1695\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"TRANGE\": \"1.9950\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val trange = TRANGE(
            TRANGEMetaData(
                symbol = "IBM",
                indicator = "True Range (TRANGE)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                TRANGEUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    trange = 2.1695,
                ),
                TRANGEUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    trange = 1.9950,
                )
            )
        )

        val trangeFromString: TRANGE = json.decodeFromString(trangeString)
        val trangeToString: String = json.encodeToString(TRANGE.serializer(), trangeFromString)
        assertEquals(trange, trangeFromString, "Decoded TRANGE does not match given TRANGE")
        assertEquals(trangeString, trangeToString, "Encoded TRANGE String does not match given TRANGE String")
    }

    @Test
    fun testATRSerializer() {

        val atrString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Average True Range (ATR)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Period\": 14,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: ATR\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"ATR\": \"4.1144\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"ATR\": \"4.2640\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val atr = ATR(
            ATRMetaData(
                symbol = "IBM",
                indicator = "Average True Range (ATR)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timePeriod = 14,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ATRUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    atr = 4.1144,
                ),
                ATRUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    atr = 4.2640,
                )
            )
        )

        val atrFromString: ATR = json.decodeFromString(atrString)
        val atrToString: String = json.encodeToString(ATR.serializer(), atrFromString)
        assertEquals(atr, atrFromString, "Decoded ATR does not match given ATR")
        assertEquals(atrString, atrToString, "Encoded ATR String does not match given ATR String")
    }

    @Test
    fun testNATRSerializer() {

        val natrString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Normalized Average True Range (NATR)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 14,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: NATR\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"NATR\": \"4.4109\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"NATR\": \"4.5875\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val natr = NATR(
            NATRMetaData(
                symbol = "IBM",
                indicator = "Normalized Average True Range (NATR)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 14,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                NATRUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    natr = 4.4109,
                ),
                NATRUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    natr = 4.5875,
                )
            )
        )

        val natrFromString: NATR = json.decodeFromString(natrString)
        val natrToString: String = json.encodeToString(NATR.serializer(), natrFromString)
        assertEquals(natr, natrFromString, "Decoded NATR does not match given NATR")
        assertEquals(natrString, natrToString, "Encoded NATR String does not match given NATR String")
    }

    @Test
    fun testADSerializer() {

        val adString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Chaikin A/D Line\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: Chaikin A/D\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"Chaikin A/D\": \"514293141.5316\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"Chaikin A/D\": \"512771690.9794\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val ad = AD(
            ADMetaData(
                symbol = "IBM",
                indicator = "Chaikin A/D Line",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ADUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    ad = 514293141.5316,
                ),
                ADUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    ad = 512771690.9794,
                )
            )
        )

        val adFromString: AD = json.decodeFromString(adString)
        val adToString: String = json.encodeToString(AD.serializer(), adFromString)
        assertEquals(ad, adFromString, "Decoded AD does not match given AD")
        assertEquals(adString, adToString, "Encoded AD String does not match given AD String")
    }

    @Test
    fun testADOSCSerializer() {

        val adoscString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Chaikin A/D Oscillator (ADOSC)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5.1: FastK Period\": 5,\n" +
                "        \"5.2: SlowK Period\": 10,\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: ADOSC\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"ADOSC\": \"1931993.7161\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"ADOSC\": \"1519473.9108\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val adosc = ADOSC(
            ADOSCMetaData(
                symbol = "IBM",
                indicator = "Chaikin A/D Oscillator (ADOSC)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                fastKPeriod = 5,
                slowKPeriod = 10,
                timeZone = "US/Eastern Time"
            ),
            listOf(
                ADOSCUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    adosc = 1931993.7161,
                ),
                ADOSCUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    adosc = 1519473.9108,
                )
            )
        )

        val adoscFromString: ADOSC = json.decodeFromString(adoscString)
        val adoscToString: String = json.encodeToString(ADOSC.serializer(), adoscFromString)
        assertEquals(adosc, adoscFromString, "Decoded ADOSC does not match given ADOSC")
        assertEquals(adoscString, adoscToString, "Encoded ADOSC String does not match given ADOSC String")
    }

    @Test
    fun testOBVSerializer() {

        val obvString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"On Balance Volume (OBV)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: OBV\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"OBV\": \"1155323319.0000\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"OBV\": \"1166598295.0000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val obv = OBV(
            OBVMetaData(
                symbol = "IBM",
                indicator = "On Balance Volume (OBV)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                OBVUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    obv = 1155323319.0000,
                ),
                OBVUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    obv = 1166598295.0000,
                )
            )
        )

        val obvFromString: OBV = json.decodeFromString(obvString)
        val obvToString: String = json.encodeToString(OBV.serializer(), obvFromString)
        assertEquals(obv, obvFromString, "Decoded OBV does not match given OBV")
        assertEquals(obvString, obvToString, "Encoded OBV String does not match given OBV String")
    }

    @Test
    fun testHTTRENDLINESerializer() {

        val httrendlineString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Hilbert Transform - Instantaneous Trendline (HT_TRENDLINE)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Series Type\": \"close\",\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: HT_TRENDLINE\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"HT_TRENDLINE\": \"183.8733\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"HT_TRENDLINE\": \"184.8343\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val httrendline = HTTRENDLINE(
            HTTRENDLINEMetaData(
                symbol = "IBM",
                indicator = "Hilbert Transform - Instantaneous Trendline (HT_TRENDLINE)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                HTTRENDLINEUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    httrendline = 183.8733,
                ),
                HTTRENDLINEUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    httrendline = 184.8343,
                )
            )
        )

        val httrendlineFromString: HTTRENDLINE = json.decodeFromString(httrendlineString)
        val httrendlineToString: String = json.encodeToString(HTTRENDLINE.serializer(), httrendlineFromString)
        assertEquals(httrendline, httrendlineFromString, "Decoded HTTRENDLINE does not match given HTTRENDLINE")
        assertEquals(httrendlineString, httrendlineToString, "Encoded HTTRENDLINE String does not match given HTTRENDLINE String")
    }

    @Test
    fun testHTSINESerializer() {

        val htsineString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Hilbert Transform - SineWave (HT_SINE)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Series Type\": \"close\",\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: HT_SINE\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"SINE\": \"-0.2623\",\n" +
                "            \"LEAD SINE\": \"0.4968\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"SINE\": \"-0.2316\",\n" +
                "            \"LEAD SINE\": \"0.5241\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val htsine = HTSINE(
            HTSINEMetaData(
                symbol = "IBM",
                indicator = "Hilbert Transform - SineWave (HT_SINE)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                HTSINEUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    sine = -0.2623,
                    leadSine = 0.4968
                ),
                HTSINEUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    sine = -0.2316,
                    leadSine = 0.5241
                )
            )
        )

        val htsineFromString: HTSINE = json.decodeFromString(htsineString)
        val htsineToString: String = json.encodeToString(HTSINE.serializer(), htsineFromString)
        assertEquals(htsine, htsineFromString, "Decoded HTSINE does not match given HTSINE")
        assertEquals(htsineString, htsineToString, "Encoded HTSINE String does not match given HTSINE String")
    }

    @Test
    fun testHTTRENDMODESerializer() {

        val httrendmodeString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Hilbert Transform - Trend vs Cycle Mode (HT_TRENDMODE)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Series Type\": \"close\",\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: HT_TRENDMODE\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"TRENDMODE\": \"1\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"TRENDMODE\": \"1\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val httrendmode = HTTRENDMODE(
            HTTRENDMODEMetaData(
                symbol = "IBM",
                indicator = "Hilbert Transform - Trend vs Cycle Mode (HT_TRENDMODE)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                HTTRENDMODEUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    trendmode = 1,
                ),
                HTTRENDMODEUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    trendmode = 1,
                )
            )
        )

        val httrendmodeFromString: HTTRENDMODE = json.decodeFromString(httrendmodeString)
        val httrendmodeToString: String = json.encodeToString(HTTRENDMODE.serializer(), httrendmodeFromString)
        assertEquals(httrendmode, httrendmodeFromString, "Decoded HTTRENDMODE does not match given HTTRENDMODE")
        assertEquals(httrendmodeString, httrendmodeToString, "Encoded HTTRENDMODE String does not match given HTTRENDMODE String")
    }

    @Test
    fun testHTDCPERIODSerializer() {

        val htdcperiodString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Hilbert Transform - Dominant Cycle Period (HT_DCPERIOD)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Series Type\": \"close\",\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: HT_DCPERIOD\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"DCPERIOD\": \"24.0665\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"DCPERIOD\": \"23.8796\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val htdcperiod = HTDCPERIOD(
            HTDCPERIODMetaData(
                symbol = "IBM",
                indicator = "Hilbert Transform - Dominant Cycle Period (HT_DCPERIOD)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                HTDCPERIODUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    dcperiod = 24.0665,
                ),
                HTDCPERIODUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    dcperiod = 23.8796,
                )
            )
        )

        val htdcperiodFromString: HTDCPERIOD = json.decodeFromString(htdcperiodString)
        val htdcperiodToString: String = json.encodeToString(HTDCPERIOD.serializer(), htdcperiodFromString)
        assertEquals(htdcperiod, htdcperiodFromString, "Decoded HTDCPERIOD does not match given HTDCPERIOD")
        assertEquals(htdcperiodString, htdcperiodToString, "Encoded HTDCPERIOD String does not match given HTDCPERIOD String")
    }

    @Test
    fun testHTDCPHASESerializer() {

        val htdcphaseString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Hilbert Transform - Dominant Cycle Phase (HT_DCPHASE)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"daily\",\n" +
                "        \"5: Series Type\": \"close\",\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: HT_DCPHASE\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"HT_DCPHASE\": \"-15.2092\"\n" +
                "        },\n" +
                "        \"2024-04-29\": {\n" +
                "            \"HT_DCPHASE\": \"-13.3916\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val htdcphase = HTDCPHASE(
            HTDCPHASEMetaData(
                symbol = "IBM",
                indicator = "Hilbert Transform - Dominant Cycle Phase (HT_DCPHASE)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "daily",
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                HTDCPHASEUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    htdcphase = -15.2092,
                ),
                HTDCPHASEUnit(
                    timestamp = "2024-04-29T00:00:00Z".toInstant(),
                    htdcphase = -13.3916,
                )
            )
        )

        val htdcphaseFromString: HTDCPHASE = json.decodeFromString(htdcphaseString)
        val htdcphaseToString: String = json.encodeToString(HTDCPHASE.serializer(), htdcphaseFromString)
        assertEquals(htdcphase, htdcphaseFromString, "Decoded HTDCPHASE does not match given HTDCPHASE")
        assertEquals(htdcphaseString, htdcphaseToString, "Encoded HTDCPHASE String does not match given HTDCPHASE String")
    }

    @Test
    fun testHTPHASORSerializer() {

        val htphasorString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Hilbert Transform - Phasor Components (HT_PHASOR)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-30\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Series Type\": \"close\",\n" +
                "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
                "    },\n" +
                "    \"Technical Analysis: HT_PHASOR\": {\n" +
                "        \"2024-04-30\": {\n" +
                "            \"PHASE\": \"-0.0262\",\n" +
                "            \"QUADRATURE\": \"-13.7655\"\n" +
                "        },\n" +
                "        \"2024-04-26\": {\n" +
                "            \"PHASE\": \"2.2107\",\n" +
                "            \"QUADRATURE\": \"-14.4489\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val htphasor = HTPHASOR(
            HTPHASORMetaData(
                symbol = "IBM",
                indicator = "Hilbert Transform - Phasor Components (HT_PHASOR)",
                lastRefreshed = "2024-04-30T00:00:00Z".toInstant(),
                interval = "weekly",
                seriesType = "close",
                timeZone = "US/Eastern Time"
            ),
            listOf(
                HTPHASORUnit(
                    timestamp = "2024-04-30T00:00:00Z".toInstant(),
                    phase = -0.0262,
                    quadrature = -13.7655
                ),
                HTPHASORUnit(
                    timestamp = "2024-04-26T00:00:00Z".toInstant(),
                    phase = 2.2107,
                    quadrature = -14.4489
                )
            )
        )

        val htphasorFromString: HTPHASOR = json.decodeFromString(htphasorString)
        val htphasorToString: String = json.encodeToString(HTPHASOR.serializer(), htphasorFromString)
        assertEquals(htphasor, htphasorFromString, "Decoded HTPHASOR does not match given HTPHASOR")
        assertEquals(htphasorString, htphasorToString, "Encoded HTPHASOR String does not match given HTPHASOR String")
    }
}