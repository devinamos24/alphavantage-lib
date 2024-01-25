package com.jinxservers.alphavantage.core

import com.jinxservers.alphavantage.core.response.*
import kotlinx.datetime.LocalTime
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
    fun testCoreIntradaySerializer() {

        val coreIntradayString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Intraday (5min) open, high, low, close prices and volume\",\n" +
                "        \"2. Symbol\": \"IBM\",\n" +
                "        \"3. Last Refreshed\": \"2024-01-26 19:55:00\",\n" +
                "        \"4. Interval\": \"5min\",\n" +
                "        \"5. Output Size\": \"Compact\",\n" +
                "        \"6. Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Time Series (5min)\": {\n" +
                "        \"2024-01-26 19:55:00\": {\n" +
                "            \"1. open\": \"187.3900\",\n" +
                "            \"2. high\": \"187.3900\",\n" +
                "            \"3. low\": \"187.1200\",\n" +
                "            \"4. close\": \"187.3900\",\n" +
                "            \"5. volume\": \"475\"\n" +
                "        },\n" +
                "        \"2024-01-26 19:50:00\": {\n" +
                "            \"1. open\": \"187.1700\",\n" +
                "            \"2. high\": \"187.3900\",\n" +
                "            \"3. low\": \"187.1100\",\n" +
                "            \"4. close\": \"187.3800\",\n" +
                "            \"5. volume\": \"13\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val metaData = CoreIntradayMetaData(
            information = "Intraday (5min) open, high, low, close prices and volume",
            symbol = "IBM",
            lastRefreshed = "2024-01-26T19:55:00Z".toInstant(),
            interval = "5min",
            outputSize = "Compact",
            timeZone = "US/Eastern"
        )

        val coreUnits: List<CoreUnit> = listOf(
            CoreUnit(
                timestamp = "2024-01-26T19:55:00Z".toInstant(),
                open = 187.3900,
                high = 187.3900,
                low = 187.1200,
                close = 187.3900,
                volume = 475
            ),
            CoreUnit(
                timestamp = "2024-01-26T19:50:00Z".toInstant(),
                open = 187.1700,
                high = 187.3900,
                low = 187.1100,
                close = 187.3800,
                volume = 13
            )
        )

        val coreIntraday = CoreIntraday(metaData, coreUnits)
        val coreIntradayFromString: CoreIntraday = json.decodeFromString(coreIntradayString)
        val coreIntradayToString: String = json.encodeToString(CoreIntraday.serializer(), coreIntradayFromString)
        assertEquals(coreIntraday, coreIntradayFromString, "Decoded CoreIntraday does not match given CoreIntraday")
        assertEquals(coreIntradayString, coreIntradayToString, "Encoded CoreIntraday String does not match given CoreIntraday String")
    }

    @Test
    fun testCoreDailySerializer() {

        val coreDailyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Daily Prices (open, high, low, close) and Volumes\",\n" +
                "        \"2. Symbol\": \"IBM\",\n" +
                "        \"3. Last Refreshed\": \"2024-01-29\",\n" +
                "        \"4. Output Size\": \"Compact\",\n" +
                "        \"5. Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Time Series (Daily)\": {\n" +
                "        \"2024-01-29\": {\n" +
                "            \"1. open\": \"187.4500\",\n" +
                "            \"2. high\": \"189.4600\",\n" +
                "            \"3. low\": \"186.0500\",\n" +
                "            \"4. close\": \"187.1700\",\n" +
                "            \"5. volume\": \"6106074\"\n" +
                "        },\n" +
                "        \"2024-01-26\": {\n" +
                "            \"1. open\": \"191.3100\",\n" +
                "            \"2. high\": \"192.3896\",\n" +
                "            \"3. low\": \"186.1600\",\n" +
                "            \"4. close\": \"187.4200\",\n" +
                "            \"5. volume\": \"9895941\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val metaData = CoreDailyMetaData(
            information = "Daily Prices (open, high, low, close) and Volumes",
            symbol = "IBM",
            lastRefreshed = "2024-01-29T00:00:00Z".toInstant(),
            outputSize = "Compact",
            timeZone = "US/Eastern"
        )

        val coreUnits: List<CoreUnit> = listOf(
            CoreUnit(
                timestamp = "2024-01-29T00:00:00Z".toInstant(),
                open = 187.4500,
                high = 189.4600,
                low = 186.0500,
                close = 187.1700,
                volume = 6106074
            ),
            CoreUnit(
                timestamp = "2024-01-26T00:00:00Z".toInstant(),
                open = 191.3100,
                high = 192.3896,
                low = 186.1600,
                close = 187.4200,
                volume = 9895941
            )
        )

        val coreDaily = CoreDaily(metaData, coreUnits)
        val coreDailyFromString: CoreDaily = json.decodeFromString(coreDailyString)
        val coreDailyToString: String = json.encodeToString(CoreDaily.serializer(), coreDailyFromString)
        assertEquals(coreDaily, coreDailyFromString, "Decoded CoreDaily does not match given CoreDaily")
        assertEquals(coreDailyString, coreDailyToString, "Encoded CoreDaily String does not match given CoreDaily String")
    }

    @Test
    fun testCoreDailyAdjustedSerializer() {

        val coreDailyAdjustedString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Daily Time Series with Splits and Dividend Events\",\n" +
                "        \"2. Symbol\": \"IBM\",\n" +
                "        \"3. Last Refreshed\": \"2024-01-29\",\n" +
                "        \"4. Output Size\": \"Compact\",\n" +
                "        \"5. Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Time Series (Daily)\": {\n" +
                "        \"2024-01-29\": {\n" +
                "            \"1. open\": \"187.46\",\n" +
                "            \"2. high\": \"189.46\",\n" +
                "            \"3. low\": \"186.05\",\n" +
                "            \"4. close\": \"187.14\",\n" +
                "            \"5. adjusted close\": \"187.14\",\n" +
                "            \"6. volume\": \"6041115\",\n" +
                "            \"7. dividend amount\": \"0.0000\",\n" +
                "            \"8. split coefficient\": \"1.0\"\n" +
                "        },\n" +
                "        \"2024-01-26\": {\n" +
                "            \"1. open\": \"191.31\",\n" +
                "            \"2. high\": \"192.3896\",\n" +
                "            \"3. low\": \"186.16\",\n" +
                "            \"4. close\": \"187.42\",\n" +
                "            \"5. adjusted close\": \"187.42\",\n" +
                "            \"6. volume\": \"9895941\",\n" +
                "            \"7. dividend amount\": \"0.0000\",\n" +
                "            \"8. split coefficient\": \"1.0\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val metaData = CoreDailyAdjustedMetaData(
            information = "Daily Time Series with Splits and Dividend Events",
            symbol = "IBM",
            lastRefreshed = "2024-01-29T00:00:00Z".toInstant(),
            outputSize = "Compact",
            timeZone = "US/Eastern"
        )

        val adjustedCoreUnits: List<CoreUnitAdjusted> = listOf(
            CoreUnitAdjusted(
                timestamp = "2024-01-29T00:00:00Z".toInstant(),
                open = 187.46,
                high = 189.46,
                low = 186.05,
                close = 187.14,
                adjustedClose = 187.14,
                volume = 6041115,
                dividendAmount = 0.0000,
                splitCoefficient = 1.0
            ),
            CoreUnitAdjusted(
                timestamp = "2024-01-26T00:00:00Z".toInstant(),
                open = 191.31,
                high = 192.3896,
                low = 186.16,
                close = 187.42,
                adjustedClose = 187.42,
                volume = 9895941,
                dividendAmount = 0.0000,
                splitCoefficient = 1.0
            )
        )

        val coreDailyAdjusted = CoreDailyAdjusted(metaData, adjustedCoreUnits)
        val coreDailyAdjustedFromString: CoreDailyAdjusted = json.decodeFromString(coreDailyAdjustedString)
        val coreDailyToString: String = json.encodeToString(CoreDailyAdjusted.serializer(), coreDailyAdjustedFromString)
        assertEquals(coreDailyAdjusted, coreDailyAdjustedFromString, "Decoded CoreDailyAdjusted does not match given CoreDailyAdjusted")
        assertEquals(coreDailyAdjustedString, coreDailyToString, "Encoded CoreDailyAdjusted String does not match given CoreDailyAdjusted String")
    }

    @Test
    fun testCoreWeeklySerializer() {

        val coreWeeklyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Weekly Prices (open, high, low, close) and Volumes\",\n" +
                "        \"2. Symbol\": \"IBM\",\n" +
                "        \"3. Last Refreshed\": \"2024-01-29\",\n" +
                "        \"4. Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Weekly Time Series\": {\n" +
                "        \"2024-01-29\": {\n" +
                "            \"1. open\": \"187.4600\",\n" +
                "            \"2. high\": \"189.4600\",\n" +
                "            \"3. low\": \"186.0500\",\n" +
                "            \"4. close\": \"187.1400\",\n" +
                "            \"5. volume\": \"6041115\"\n" +
                "        },\n" +
                "        \"2024-01-26\": {\n" +
                "            \"1. open\": \"172.8200\",\n" +
                "            \"2. high\": \"196.9000\",\n" +
                "            \"3. low\": \"172.4000\",\n" +
                "            \"4. close\": \"187.4200\",\n" +
                "            \"5. volume\": \"56232762\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val metaData = CoreWeeklyMetaData(
            information = "Weekly Prices (open, high, low, close) and Volumes",
            symbol = "IBM",
            lastRefreshed = "2024-01-29T00:00:00Z".toInstant(),
            timeZone = "US/Eastern"
        )

        val coreUnits: List<CoreUnit> = listOf(
            CoreUnit(
                timestamp = "2024-01-29T00:00:00Z".toInstant(),
                open = 187.4600,
                high = 189.4600,
                low = 186.0500,
                close = 187.1400,
                volume = 6041115
            ),
            CoreUnit(
                timestamp = "2024-01-26T00:00:00Z".toInstant(),
                open = 172.8200,
                high = 196.9000,
                low = 172.4000,
                close = 187.4200,
                volume = 56232762
            )
        )

        val coreWeekly = CoreWeekly(metaData, coreUnits)
        val coreWeeklyFromString: CoreWeekly = json.decodeFromString(coreWeeklyString)
        val coreWeeklyToString: String = json.encodeToString(CoreWeekly.serializer(), coreWeeklyFromString)
        assertEquals(coreWeekly, coreWeeklyFromString, "Decoded CoreWeekly does not match given CoreWeekly")
        assertEquals(coreWeeklyString, coreWeeklyToString, "Encoded CoreWeekly String does not match given CoreWeekly String")
    }

    @Test
    fun testCoreWeeklyAdjustedSerializer() {

        val coreWeeklyAdjustedString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Weekly Adjusted Prices and Volumes\",\n" +
                "        \"2. Symbol\": \"IBM\",\n" +
                "        \"3. Last Refreshed\": \"2024-01-29\",\n" +
                "        \"4. Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Weekly Adjusted Time Series\": {\n" +
                "        \"2024-01-29\": {\n" +
                "            \"1. open\": \"187.4600\",\n" +
                "            \"2. high\": \"189.4600\",\n" +
                "            \"3. low\": \"186.0500\",\n" +
                "            \"4. close\": \"187.1400\",\n" +
                "            \"5. adjusted close\": \"187.1400\",\n" +
                "            \"6. volume\": \"6041115\",\n" +
                "            \"7. dividend amount\": \"0.0000\"\n" +
                "        },\n" +
                "        \"2024-01-26\": {\n" +
                "            \"1. open\": \"172.8200\",\n" +
                "            \"2. high\": \"196.9000\",\n" +
                "            \"3. low\": \"172.4000\",\n" +
                "            \"4. close\": \"187.4200\",\n" +
                "            \"5. adjusted close\": \"187.4200\",\n" +
                "            \"6. volume\": \"56232762\",\n" +
                "            \"7. dividend amount\": \"0.0000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val metaData = CoreWeeklyAdjustedMetaData(
            information = "Weekly Adjusted Prices and Volumes",
            symbol = "IBM",
            lastRefreshed = "2024-01-29T00:00:00Z".toInstant(),
            timeZone = "US/Eastern"
        )

        val adjustedCoreUnits: List<CoreUnitAdjusted> = listOf(
            CoreUnitAdjusted(
                timestamp = "2024-01-29T00:00:00Z".toInstant(),
                open = 187.4600,
                high = 189.4600,
                low = 186.0500,
                close = 187.1400,
                adjustedClose = 187.1400,
                volume = 6041115,
                dividendAmount = 0.0000,
            ),
            CoreUnitAdjusted(
                timestamp = "2024-01-26T00:00:00Z".toInstant(),
                open = 172.8200,
                high = 196.9000,
                low = 172.4000,
                close = 187.4200,
                adjustedClose = 187.4200,
                volume = 56232762,
                dividendAmount = 0.0000,
            )
        )

        val coreWeeklyAdjusted = CoreWeeklyAdjusted(metaData, adjustedCoreUnits)
        val coreWeeklyAdjustedFromString: CoreWeeklyAdjusted = json.decodeFromString(coreWeeklyAdjustedString)
        val coreWeeklyToString: String = json.encodeToString(CoreWeeklyAdjusted.serializer(), coreWeeklyAdjustedFromString)
        assertEquals(coreWeeklyAdjusted, coreWeeklyAdjustedFromString, "Decoded CoreWeeklyAdjusted does not match given CoreWeeklyAdjusted")
        assertEquals(coreWeeklyAdjustedString, coreWeeklyToString, "Encoded CoreWeeklyAdjusted String does not match given CoreWeeklyAdjusted String")
    }

    @Test
    fun testCoreMonthlySerializer() {

        val coreMonthlyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Monthly Prices (open, high, low, close) and Volumes\",\n" +
                "        \"2. Symbol\": \"IBM\",\n" +
                "        \"3. Last Refreshed\": \"2024-01-29\",\n" +
                "        \"4. Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Monthly Time Series\": {\n" +
                "        \"2024-01-29\": {\n" +
                "            \"1. open\": \"162.8300\",\n" +
                "            \"2. high\": \"196.9000\",\n" +
                "            \"3. low\": \"157.8850\",\n" +
                "            \"4. close\": \"187.1400\",\n" +
                "            \"5. volume\": \"114603651\"\n" +
                "        },\n" +
                "        \"2023-12-29\": {\n" +
                "            \"1. open\": \"158.4100\",\n" +
                "            \"2. high\": \"166.3400\",\n" +
                "            \"3. low\": \"158.0000\",\n" +
                "            \"4. close\": \"163.5500\",\n" +
                "            \"5. volume\": \"87358302\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val metaData = CoreMonthlyMetaData(
            information = "Monthly Prices (open, high, low, close) and Volumes",
            symbol = "IBM",
            lastRefreshed = "2024-01-29T00:00:00Z".toInstant(),
            timeZone = "US/Eastern"
        )

        val coreUnits: List<CoreUnit> = listOf(
            CoreUnit(
                timestamp = "2024-01-29T00:00:00Z".toInstant(),
                open = 162.8300,
                high = 196.9000,
                low = 157.8850,
                close = 187.1400,
                volume = 114603651
            ),
            CoreUnit(
                timestamp = "2023-12-29T00:00:00Z".toInstant(),
                open = 158.4100,
                high = 166.3400,
                low = 158.0000,
                close = 163.5500,
                volume = 87358302
            )
        )

        val coreMonthly = CoreMonthly(metaData, coreUnits)
        val coreMonthlyFromString: CoreMonthly = json.decodeFromString(coreMonthlyString)
        val coreMonthlyToString: String = json.encodeToString(CoreMonthly.serializer(), coreMonthlyFromString)
        assertEquals(coreMonthly, coreMonthlyFromString, "Decoded CoreMonthly does not match given CoreMonthly")
        assertEquals(coreMonthlyString, coreMonthlyToString, "Encoded CoreMonthly String does not match given CoreMonthly String")
    }

    @Test
    fun testCoreMonthlyAdjustedSerializer() {

        val coreMonthlyAdjustedString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Monthly Adjusted Prices and Volumes\",\n" +
                "        \"2. Symbol\": \"IBM\",\n" +
                "        \"3. Last Refreshed\": \"2024-01-29\",\n" +
                "        \"4. Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Monthly Adjusted Time Series\": {\n" +
                "        \"2024-01-29\": {\n" +
                "            \"1. open\": \"162.8300\",\n" +
                "            \"2. high\": \"196.9000\",\n" +
                "            \"3. low\": \"157.8850\",\n" +
                "            \"4. close\": \"187.1400\",\n" +
                "            \"5. adjusted close\": \"187.1400\",\n" +
                "            \"6. volume\": \"114603651\",\n" +
                "            \"7. dividend amount\": \"0.0000\"\n" +
                "        },\n" +
                "        \"2023-12-29\": {\n" +
                "            \"1. open\": \"158.4100\",\n" +
                "            \"2. high\": \"166.3400\",\n" +
                "            \"3. low\": \"158.0000\",\n" +
                "            \"4. close\": \"163.5500\",\n" +
                "            \"5. adjusted close\": \"163.5500\",\n" +
                "            \"6. volume\": \"87358302\",\n" +
                "            \"7. dividend amount\": \"0.0000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val metaData = CoreMonthlyAdjustedMetaData(
            information = "Monthly Adjusted Prices and Volumes",
            symbol = "IBM",
            lastRefreshed = "2024-01-29T00:00:00Z".toInstant(),
            timeZone = "US/Eastern"
        )

        val adjustedCoreUnits: List<CoreUnitAdjusted> = listOf(
            CoreUnitAdjusted(
                timestamp = "2024-01-29T00:00:00Z".toInstant(),
                open = 162.8300,
                high = 196.9000,
                low = 157.8850,
                close = 187.1400,
                adjustedClose = 187.1400,
                volume = 114603651,
                dividendAmount = 0.0000,
            ),
            CoreUnitAdjusted(
                timestamp = "2023-12-29T00:00:00Z".toInstant(),
                open = 158.4100,
                high = 166.3400,
                low = 158.0000,
                close = 163.5500,
                adjustedClose = 163.5500,
                volume = 87358302,
                dividendAmount = 0.0000,
            )
        )

        val coreMonthlyAdjusted = CoreMonthlyAdjusted(metaData, adjustedCoreUnits)
        val coreMonthlyAdjustedFromString: CoreMonthlyAdjusted = json.decodeFromString(coreMonthlyAdjustedString)
        val coreMonthlyAdjustedToString: String = json.encodeToString(CoreMonthlyAdjusted.serializer(), coreMonthlyAdjustedFromString)
        assertEquals(coreMonthlyAdjusted, coreMonthlyAdjustedFromString, "Decoded CoreMonthlyAdjusted does not match given CoreMonthlyAdjusted")
        assertEquals(coreMonthlyAdjustedString, coreMonthlyAdjustedToString, "Encoded CoreMonthlyAdjusted String does not match given CoreMonthlyAdjusted String")
    }

    @Test
    fun testQuoteEndpointSerializer() {

        val quoteEndpointString: String = "{\n" +
                "    \"Global Quote\": {\n" +
                "        \"01. symbol\": \"IBM\",\n" +
                "        \"02. open\": \"187.4600\",\n" +
                "        \"03. high\": \"189.4600\",\n" +
                "        \"04. low\": \"186.0500\",\n" +
                "        \"05. price\": \"187.1400\",\n" +
                "        \"06. volume\": \"6041115\",\n" +
                "        \"07. latest trading day\": \"2024-01-29\",\n" +
                "        \"08. previous close\": \"187.4200\",\n" +
                "        \"09. change\": \"-0.2800\",\n" +
                "        \"10. change percent\": \"-0.1494%\"\n" +
                "    }\n" +
                "}"

        val quoteEndpoint = QuoteEndpoint(
            symbol = "IBM",
            open = 187.4600,
            high = 189.4600,
            low = 186.0500,
            price = 187.1400,
            volume = 6041115,
            latestTradingDay = "2024-01-29T00:00:00Z".toInstant(),
            previousClose = 187.4200,
            change = -0.2800,
            changePercent = -0.001494
        )

        val quoteEndpointFromString: QuoteEndpoint = json.decodeFromString(quoteEndpointString)
        val quoteEndpointToString: String = json.encodeToString(QuoteEndpoint.serializer(), quoteEndpointFromString)
        assertEquals(quoteEndpoint, quoteEndpointFromString, "Decoded QuoteEndpoint does not match given QuoteEndpoint")
        assertEquals(quoteEndpointString, quoteEndpointToString, "Encoded QuoteEndpoint String does not match given QuoteEndpoint String")
    }

    @Test
    fun testTickerSearchSerializer() {

        val tickerSearchString: String = "{\n" +
                "    \"bestMatches\": [\n" +
                "        {\n" +
                "            \"1. symbol\": \"TSCO.LON\",\n" +
                "            \"2. name\": \"Tesco PLC\",\n" +
                "            \"3. type\": \"Equity\",\n" +
                "            \"4. region\": \"United Kingdom\",\n" +
                "            \"5. marketOpen\": \"08:00\",\n" +
                "            \"6. marketClose\": \"16:30\",\n" +
                "            \"7. timezone\": \"UTC+01\",\n" +
                "            \"8. currency\": \"GBX\",\n" +
                "            \"9. matchScore\": \"0.7273\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"1. symbol\": \"TSCDF\",\n" +
                "            \"2. name\": \"Tesco plc\",\n" +
                "            \"3. type\": \"Equity\",\n" +
                "            \"4. region\": \"United States\",\n" +
                "            \"5. marketOpen\": \"09:30\",\n" +
                "            \"6. marketClose\": \"16:00\",\n" +
                "            \"7. timezone\": \"UTC-04\",\n" +
                "            \"8. currency\": \"USD\",\n" +
                "            \"9. matchScore\": \"0.7143\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"

        val tickerSearch = listOf(
            TickerSearchUnit(
                symbol = "TSCO.LON",
                name = "Tesco PLC",
                type = "Equity",
                region = "United Kingdom",
                marketOpenTime = LocalTime(8, 0),
                marketCloseTime = LocalTime(16, 30),
                timezone = "UTC+01",
                currency = "GBX",
                matchScore = 0.7273
            ),
            TickerSearchUnit(
                symbol = "TSCDF",
                name = "Tesco plc",
                type = "Equity",
                region = "United States",
                marketOpenTime = LocalTime(9, 30),
                marketCloseTime = LocalTime(16, 0),
                timezone = "UTC-04",
                currency = "USD",
                matchScore = 0.7143
            )
        )

        val tickerSearchFromString: List<TickerSearchUnit> = json.decodeFromString(TickerSearchUnitListSerializer ,tickerSearchString)
        val tickerSearchToString: String = json.encodeToString(TickerSearchUnitListSerializer, tickerSearchFromString)
        assertEquals(tickerSearch, tickerSearchFromString, "Decoded TickerSearchUnitList does not match given TickerSearchUnitList")
        assertEquals(tickerSearchString, tickerSearchToString, "Encoded TickerSearchUnitList String does not match given TickerSearchUnitList String")
    }

    @Test
    fun testGlobalMarketStatusSerializer() {

        val globalMarketStatusString: String = "{\n" +
                "    \"endpoint\": \"Global Market Open & Close Status\",\n" +
                "    \"markets\": [\n" +
                "        {\n" +
                "            \"market_type\": \"Equity\",\n" +
                "            \"region\": \"United States\",\n" +
                "            \"primary_exchanges\": \"NASDAQ, NYSE, AMEX, BATS\",\n" +
                "            \"local_open\": \"09:30\",\n" +
                "            \"local_close\": \"16:15\",\n" +
                "            \"current_status\": \"closed\",\n" +
                "            \"notes\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"market_type\": \"Equity\",\n" +
                "            \"region\": \"Canada\",\n" +
                "            \"primary_exchanges\": \"Toronto, Toronto Ventures\",\n" +
                "            \"local_open\": \"09:30\",\n" +
                "            \"local_close\": \"16:00\",\n" +
                "            \"current_status\": \"closed\",\n" +
                "            \"notes\": \"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"

        val globalMarketStatus = GlobalMarketStatus(
            endpoint = "Global Market Open & Close Status",
            markets = listOf(
                MarketUnit(
                    marketType = "Equity",
                    region = "United States",
                    primaryExchanges = "NASDAQ, NYSE, AMEX, BATS",
                    localOpen = LocalTime(9, 30),
                    localClose = LocalTime(16, 15),
                    currentStatus = "closed",
                    notes = ""
                ),
                MarketUnit(
                    marketType = "Equity",
                    region = "Canada",
                    primaryExchanges = "Toronto, Toronto Ventures",
                    localOpen = LocalTime(9, 30),
                    localClose = LocalTime(16, 0),
                    currentStatus = "closed",
                    notes = ""
                )
            )
        )

        val globalMarketStatusFromString: GlobalMarketStatus = json.decodeFromString(globalMarketStatusString)
        val globalMarketStatusToString: String = json.encodeToString(GlobalMarketStatus.serializer(), globalMarketStatusFromString)
        assertEquals(globalMarketStatus, globalMarketStatusFromString, "Decoded GlobalMarketStatus does not match given GlobalMarketStatus")
        assertEquals(globalMarketStatusString, globalMarketStatusToString, "Encoded GlobalMarketStatus String does not match given GlobalMarketStatus String")
    }
}