package com.jinxservers.alphavantage.forex

import com.jinxservers.alphavantage.forex.response.*
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
    fun testCurrencyExchangeRateSerializer() {

        val currencyExchangeRateString: String = "{\n" +
                "    \"Realtime Currency Exchange Rate\": {\n" +
                "        \"1. From_Currency Code\": \"USD\",\n" +
                "        \"2. From_Currency Name\": \"United States Dollar\",\n" +
                "        \"3. To_Currency Code\": \"JPY\",\n" +
                "        \"4. To_Currency Name\": \"Japanese Yen\",\n" +
                "        \"5. Exchange Rate\": \"149.85900000\",\n" +
                "        \"6. Last Refreshed\": \"2023-10-02 23:20:02\",\n" +
                "        \"7. Time Zone\": \"UTC\",\n" +
                "        \"8. Bid Price\": \"149.85450000\",\n" +
                "        \"9. Ask Price\": \"149.86570000\"\n" +
                "    }\n" +
                "}"
        val currencyExchangeRateObject = CurrencyExchangeRate("USD", "United States Dollar", "JPY", "Japanese Yen", 149.85900000, "2023-10-02T23:20:02Z".toInstant(), "UTC", 149.85450000, 149.86570000)
        val currencyExchangeRateFromString: CurrencyExchangeRate = json.decodeFromString(currencyExchangeRateString)
        val currencyExchangeRateToString: String = json.encodeToString(CurrencyExchangeRate.serializer(), currencyExchangeRateFromString)
        assertEquals(currencyExchangeRateObject, currencyExchangeRateFromString, "Decoded CurrencyExchangeRate does not match given CurrencyExchangeRate")
        assertEquals(currencyExchangeRateString, currencyExchangeRateToString, "Encoded CurrencyExchangeRate String does not match given CurrencyExchangeRate String")
    }

    @Test
    fun testForexIntradaySerializer() {

        val forexIntradayString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"FX Intraday (5min) Time Series\",\n" +
                "        \"2. From Symbol\": \"EUR\",\n" +
                "        \"3. To Symbol\": \"USD\",\n" +
                "        \"4. Last Refreshed\": \"2023-10-12 17:25:00\",\n" +
                "        \"5. Interval\": \"5min\",\n" +
                "        \"6. Output Size\": \"Compact\",\n" +
                "        \"7. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series FX (5min)\": {\n" +
                "        \"2023-10-12 17:25:00\": {\n" +
                "            \"1. open\": \"1.05397\",\n" +
                "            \"2. high\": \"1.05425\",\n" +
                "            \"3. low\": \"1.05396\",\n" +
                "            \"4. close\": \"1.05418\"\n" +
                "        },\n" +
                "        \"2023-10-12 17:10:00\": {\n" +
                "            \"1. open\": \"1.05458\",\n" +
                "            \"2. high\": \"1.05461\",\n" +
                "            \"3. low\": \"1.05393\",\n" +
                "            \"4. close\": \"1.05393\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val forexIntradayMetaData = ForexIntradayMetaData(
            "FX Intraday (5min) Time Series",
            "EUR",
            "USD",
            "2023-10-12T17:25:00Z".toInstant(),
            "5min",
            "Compact",
            "UTC"
        )
        val forexUnits: List<ForexUnit> = listOf(
            ForexUnit("2023-10-12T17:25:00Z".toInstant(), 1.05397, 1.05425, 1.05396, 1.05418),
            ForexUnit("2023-10-12T17:10:00Z".toInstant(), 1.05458, 1.05461, 1.05393, 1.05393)
        )

        val forexIntradayObject = ForexIntraday(forexIntradayMetaData, forexUnits)
        val forexIntradayFromString: ForexIntraday = json.decodeFromString(forexIntradayString)
        val forexIntradayToString: String = json.encodeToString(ForexIntraday.serializer(), forexIntradayFromString)
        assertEquals(forexIntradayObject, forexIntradayFromString, "Decoded ForexIntraday does not match given ForexIntraday")
        assertEquals(forexIntradayString, forexIntradayToString, "Encoded ForexIntraday String does not match given ForexIntraday String")
    }

    @Test
    fun testForexDailySerializer() {

        val forexDailyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Forex Daily Prices (open, high, low, close)\",\n" +
                "        \"2. From Symbol\": \"EUR\",\n" +
                "        \"3. To Symbol\": \"USD\",\n" +
                "        \"4. Output Size\": \"Compact\",\n" +
                "        \"5. Last Refreshed\": \"2023-10-13 21:55:00\",\n" +
                "        \"6. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series FX (Daily)\": {\n" +
                "        \"2023-10-13\": {\n" +
                "            \"1. open\": \"1.05370\",\n" +
                "            \"2. high\": \"1.05584\",\n" +
                "            \"3. low\": \"1.04952\",\n" +
                "            \"4. close\": \"1.05046\"\n" +
                "        },\n" +
                "        \"2023-10-12\": {\n" +
                "            \"1. open\": \"1.06191\",\n" +
                "            \"2. high\": \"1.06397\",\n" +
                "            \"3. low\": \"1.05240\",\n" +
                "            \"4. close\": \"1.05297\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val forexDailyMetaData = ForexDailyMetaData(
            "Forex Daily Prices (open, high, low, close)",
            "EUR",
            "USD",
            "Compact",
            "2023-10-13T21:55:00Z".toInstant(),
            "UTC"
        )
        val forexUnits: List<ForexUnit> = listOf(
            ForexUnit("2023-10-13T00:00:00Z".toInstant(), 1.05370, 1.05584, 1.04952, 1.05046),
            ForexUnit("2023-10-12T00:00:00Z".toInstant(), 1.06191, 1.06397, 1.05240, 1.05297)
        )

        val forexDailyObject = ForexDaily(forexDailyMetaData, forexUnits)
        val forexDailyFromString: ForexDaily = json.decodeFromString(forexDailyString)
        val forexDailyToString: String = json.encodeToString(ForexDaily.serializer(), forexDailyFromString)
        assertEquals(forexDailyObject, forexDailyFromString, "Decoded ForexDaily does not match given ForexDaily")
        assertEquals(forexDailyString, forexDailyToString, "Encoded ForexDaily String does not match given ForexDaily String")
    }

    @Test
    fun testForexWeeklySerializer() {

        val forexWeeklyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Forex Weekly Prices (open, high, low, close)\",\n" +
                "        \"2. From Symbol\": \"EUR\",\n" +
                "        \"3. To Symbol\": \"USD\",\n" +
                "        \"4. Last Refreshed\": \"2023-10-13 21:55:00\",\n" +
                "        \"5. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series FX (Weekly)\": {\n" +
                "        \"2023-10-13\": {\n" +
                "            \"1. open\": \"1.05545\",\n" +
                "            \"2. high\": \"1.06397\",\n" +
                "            \"3. low\": \"1.04952\",\n" +
                "            \"4. close\": \"1.05046\"\n" +
                "        },\n" +
                "        \"2023-10-06\": {\n" +
                "            \"1. open\": \"1.05676\",\n" +
                "            \"2. high\": \"1.06000\",\n" +
                "            \"3. low\": \"1.04480\",\n" +
                "            \"4. close\": \"1.05857\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val forexWeeklyMetaData = ForexWeeklyMetaData(
            "Forex Weekly Prices (open, high, low, close)",
            "EUR",
            "USD",
            "2023-10-13T21:55:00Z".toInstant(),
            "UTC"
        )
        val forexUnits: List<ForexUnit> = listOf(
            ForexUnit("2023-10-13T00:00:00Z".toInstant(), 1.05545, 1.06397, 1.04952, 1.05046),
            ForexUnit("2023-10-06T00:00:00Z".toInstant(), 1.05676, 1.06000, 1.04480, 1.05857)
        )

        val forexWeeklyObject = ForexWeekly(forexWeeklyMetaData, forexUnits)
        val forexWeeklyFromString: ForexWeekly = json.decodeFromString(forexWeeklyString)
        val forexWeeklyToString: String = json.encodeToString(ForexWeekly.serializer(), forexWeeklyFromString)
        assertEquals(forexWeeklyObject, forexWeeklyFromString, "Decoded ForexWeekly does not match given ForexWeekly")
        assertEquals(forexWeeklyString, forexWeeklyToString, "Encoded ForexWeekly String does not match given ForexWeekly String")
    }

    @Test
    fun testForexMonthlySerializer() {

        val forexMonthlyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Forex Monthly Prices (open, high, low, close)\",\n" +
                "        \"2. From Symbol\": \"EUR\",\n" +
                "        \"3. To Symbol\": \"USD\",\n" +
                "        \"4. Last Refreshed\": \"2023-10-13 21:55:00\",\n" +
                "        \"5. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series FX (Monthly)\": {\n" +
                "        \"2023-10-13\": {\n" +
                "            \"1. open\": \"1.05676\",\n" +
                "            \"2. high\": \"1.06397\",\n" +
                "            \"3. low\": \"1.04480\",\n" +
                "            \"4. close\": \"1.05046\"\n" +
                "        },\n" +
                "        \"2023-09-29\": {\n" +
                "            \"1. open\": \"1.08414\",\n" +
                "            \"2. high\": \"1.08819\",\n" +
                "            \"3. low\": \"1.04878\",\n" +
                "            \"4. close\": \"1.05709\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val forexMonthlyMetaData = ForexMonthlyMetaData(
            "Forex Monthly Prices (open, high, low, close)",
            "EUR",
            "USD",
            "2023-10-13T21:55:00Z".toInstant(),
            "UTC"
        )
        val forexUnits: List<ForexUnit> = listOf(
            ForexUnit("2023-10-13T00:00:00Z".toInstant(), 1.05676, 1.06397, 1.04480, 1.05046),
            ForexUnit("2023-09-29T00:00:00Z".toInstant(), 1.08414, 1.08819, 1.04878, 1.05709)
        )

        val forexMonthlyObject = ForexMonthly(forexMonthlyMetaData, forexUnits)
        val forexMonthlyFromString: ForexMonthly = json.decodeFromString(forexMonthlyString)
        val forexMonthlyToString: String = json.encodeToString(ForexMonthly.serializer(), forexMonthlyFromString)
        assertEquals(forexMonthlyObject, forexMonthlyFromString, "Decoded ForexMonthly does not match given ForexMonthly")
        assertEquals(forexMonthlyString, forexMonthlyToString, "Encoded ForexMonthly String does not match given ForexMonthly String")
    }
}