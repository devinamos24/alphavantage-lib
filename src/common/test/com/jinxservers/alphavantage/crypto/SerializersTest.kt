package com.jinxservers.alphavantage.crypto

import com.jinxservers.alphavantage.crypto.response.*
import kotlinx.datetime.toInstant
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializersTest {

    private val json = Json {
        prettyPrint = true
        isLenient = true
    }
    
    @Test
    fun testCryptoIntradaySerializer() {

        val cryptoIntradayString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Crypto Intraday (5min) Time Series\",\n" +
                "        \"2. Digital Currency Code\": \"ETH\",\n" +
                "        \"3. Digital Currency Name\": \"Ethereum\",\n" +
                "        \"4. Market Code\": \"USD\",\n" +
                "        \"5. Market Name\": \"United States Dollar\",\n" +
                "        \"6. Last Refreshed\": \"2023-10-14 01:10:00\",\n" +
                "        \"7. Interval\": \"5min\",\n" +
                "        \"8. Output Size\": \"Compact\",\n" +
                "        \"9. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series Crypto (5min)\": {\n" +
                "        \"2023-10-14 01:10:00\": {\n" +
                "            \"1. open\": \"1551.53000\",\n" +
                "            \"2. high\": \"1551.56000\",\n" +
                "            \"3. low\": \"1551.53000\",\n" +
                "            \"4. close\": \"1551.56000\",\n" +
                "            \"5. volume\": 1\n" +
                "        },\n" +
                "        \"2023-10-14 01:05:00\": {\n" +
                "            \"1. open\": \"1553.07000\",\n" +
                "            \"2. high\": \"1553.35000\",\n" +
                "            \"3. low\": \"1551.33000\",\n" +
                "            \"4. close\": \"1551.53000\",\n" +
                "            \"5. volume\": 313\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val cryptoIntradayMetaData = CryptoIntradayMetaData(
            "Crypto Intraday (5min) Time Series",
            "ETH",
            "Ethereum",
            "USD",
            "United States Dollar",
            "2023-10-14T01:10:00Z".toInstant(),
            "5min",
            "Compact",
            "UTC"
        )
        val cryptoUnits: List<CryptoUnit> = listOf(
            CryptoUnit("2023-10-14T01:10:00Z".toInstant(), 1551.53000, 1551.56000, 1551.53000, 1551.56000, 1),
            CryptoUnit("2023-10-14T01:05:00Z".toInstant(), 1553.07000, 1553.35000, 1551.33000, 1551.53000, 313)
        )

        val cryptoIntradayObject = CryptoIntraday(cryptoIntradayMetaData, cryptoUnits)
        val cryptoIntradayFromString: CryptoIntraday = json.decodeFromString(cryptoIntradayString)
        val cryptoIntradayToString: String = json.encodeToString(CryptoIntraday.serializer(), cryptoIntradayFromString)
        assertEquals(cryptoIntradayObject, cryptoIntradayFromString, "Decoded CryptoIntraday does not match given CryptoIntraday")
        assertEquals(cryptoIntradayString, cryptoIntradayToString, "Encoded CryptoIntraday String does not match given CryptoIntraday String")
    }

    @Test
    fun testCryptoDailySerializer() {

        val cryptoDailyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Daily Prices and Volumes for Digital Currency\",\n" +
                "        \"2. Digital Currency Code\": \"BTC\",\n" +
                "        \"3. Digital Currency Name\": \"Bitcoin\",\n" +
                "        \"4. Market Code\": \"CNY\",\n" +
                "        \"5. Market Name\": \"Chinese Yuan\",\n" +
                "        \"6. Last Refreshed\": \"2023-10-14 00:00:00\",\n" +
                "        \"7. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series (Digital Currency Daily)\": {\n" +
                "        \"2023-10-14\": {\n" +
                "            \"1a. open (CNY)\": \"196208.10660000\",\n" +
                "            \"1b. open (USD)\": \"26862.00000000\",\n" +
                "            \"2a. high (CNY)\": \"196386.33152000\",\n" +
                "            \"2b. high (USD)\": \"26886.40000000\",\n" +
                "            \"3a. low (CNY)\": \"196138.64270700\",\n" +
                "            \"3b. low (USD)\": \"26852.49000000\",\n" +
                "            \"4a. close (CNY)\": \"196282.31828800\",\n" +
                "            \"4b. close (USD)\": \"26872.16000000\",\n" +
                "            \"5. volume\": \"396.88958000\",\n" +
                "            \"6. market cap (USD)\": \"396.88958000\"\n" +
                "        },\n" +
                "        \"2023-10-13\": {\n" +
                "            \"1a. open (CNY)\": \"195460.36540900\",\n" +
                "            \"1b. open (USD)\": \"26759.63000000\",\n" +
                "            \"2a. high (CNY)\": \"198165.65900000\",\n" +
                "            \"2b. high (USD)\": \"27130.00000000\",\n" +
                "            \"3a. low (CNY)\": \"194915.24550000\",\n" +
                "            \"3b. low (USD)\": \"26685.00000000\",\n" +
                "            \"4a. close (CNY)\": \"196208.10660000\",\n" +
                "            \"4b. close (USD)\": \"26862.00000000\",\n" +
                "            \"5. volume\": \"24115.76499000\",\n" +
                "            \"6. market cap (USD)\": \"24115.76499000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val cryptoDailyMetaData = CryptoDailyMetaData(
            "Daily Prices and Volumes for Digital Currency",
            "BTC",
            "Bitcoin",
            "CNY",
            "Chinese Yuan",
            "2023-10-14T00:00:00Z".toInstant(),
            "UTC"
        )
        val cryptoUnits: List<CryptoUnit> = listOf(
            CryptoUnit(
                "2023-10-14T00:00:00Z".toInstant(),
                196208.10660000,
                26862.00000000,
                196386.33152000,
                26886.40000000,
                196138.64270700,
                26852.49000000,
                196282.31828800,
                26872.16000000,
                396.88958000,
                396.88958000
            ),
            CryptoUnit(
                "2023-10-13T00:00:00Z".toInstant(),
                195460.36540900,
                26759.63000000,
                198165.65900000,
                27130.00000000,
                194915.24550000,
                26685.00000000,
                196208.10660000,
                26862.00000000,
                24115.76499000,
                24115.76499000
            )
        )

        val cryptoDailyObject = CryptoDaily(cryptoDailyMetaData, cryptoUnits)
        println(cryptoDailyObject)
        val cryptoDailyFromString: CryptoDaily = json.decodeFromString(cryptoDailyString)
        val cryptoDailyToString: String = json.encodeToString(CryptoDailySerializer, cryptoDailyFromString)
        assertEquals(cryptoDailyObject, cryptoDailyFromString, "Decoded CryptoDaily does not match given CryptoDaily")
        assertEquals(cryptoDailyString, cryptoDailyToString, "Encoded CryptoDaily String does not match given CryptoDaily String")
    }

    @Test
    fun testCryptoWeeklySerializer() {

        val cryptoWeeklyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Weekly Prices and Volumes for Digital Currency\",\n" +
                "        \"2. Digital Currency Code\": \"BTC\",\n" +
                "        \"3. Digital Currency Name\": \"Bitcoin\",\n" +
                "        \"4. Market Code\": \"USD\",\n" +
                "        \"5. Market Name\": \"United States Dollar\",\n" +
                "        \"6. Last Refreshed\": \"2023-10-16 00:00:00\",\n" +
                "        \"7. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series (Digital Currency Weekly)\": {\n" +
                "        \"2023-10-16\": {\n" +
                "            \"1a. open (USD)\": \"27154.14000000\",\n" +
                "            \"1b. open (USD)\": \"27154.14000000\",\n" +
                "            \"2a. high (USD)\": \"27193.54000000\",\n" +
                "            \"2b. high (USD)\": \"27193.54000000\",\n" +
                "            \"3a. low (USD)\": \"27128.13000000\",\n" +
                "            \"3b. low (USD)\": \"27128.13000000\",\n" +
                "            \"4a. close (USD)\": \"27168.36000000\",\n" +
                "            \"4b. close (USD)\": \"27168.36000000\",\n" +
                "            \"5. volume\": \"724.18873000\",\n" +
                "            \"6. market cap (USD)\": \"724.18873000\"\n" +
                "        },\n" +
                "        \"2023-10-15\": {\n" +
                "            \"1a. open (USD)\": \"27917.06000000\",\n" +
                "            \"1b. open (USD)\": \"27917.06000000\",\n" +
                "            \"2a. high (USD)\": \"27987.93000000\",\n" +
                "            \"2b. high (USD)\": \"27987.93000000\",\n" +
                "            \"3a. low (USD)\": \"26538.66000000\",\n" +
                "            \"3b. low (USD)\": \"26538.66000000\",\n" +
                "            \"4a. close (USD)\": \"27154.15000000\",\n" +
                "            \"4b. close (USD)\": \"27154.15000000\",\n" +
                "            \"5. volume\": \"164897.39085000\",\n" +
                "            \"6. market cap (USD)\": \"164897.39085000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val cryptoWeeklyMetaData = CryptoWeeklyMetaData(
            "Weekly Prices and Volumes for Digital Currency",
            "BTC",
            "Bitcoin",
            "USD",
            "United States Dollar",
            "2023-10-16T00:00:00Z".toInstant(),
            "UTC"
        )
        val cryptoUnits: List<CryptoUnit> = listOf(
            CryptoUnit(
                "2023-10-16T00:00:00Z".toInstant(),
                27154.14000000,
                27154.14000000,
                27193.54000000,
                27193.54000000,
                27128.13000000,
                27128.13000000,
                27168.36000000,
                27168.36000000,
                724.18873000,
                724.18873000
            ),
            CryptoUnit(
                "2023-10-15T00:00:00Z".toInstant(),
                27917.06000000,
                27917.06000000,
                27987.93000000,
                27987.93000000,
                26538.66000000,
                26538.66000000,
                27154.15000000,
                27154.15000000,
                164897.39085000,
                164897.39085000
            )
        )

        val cryptoWeeklyObject = CryptoWeekly(cryptoWeeklyMetaData, cryptoUnits)
        println(cryptoWeeklyObject)
        val cryptoWeeklyFromString: CryptoWeekly = json.decodeFromString(cryptoWeeklyString)
        val cryptoWeeklyToString: String = json.encodeToString(CryptoWeeklySerializer, cryptoWeeklyFromString)
        assertEquals(cryptoWeeklyObject, cryptoWeeklyFromString, "Decoded CryptoWeekly does not match given CryptoWeekly")
        assertEquals(cryptoWeeklyString, cryptoWeeklyToString, "Encoded CryptoWeekly String does not match given CryptoWeekly String")
    }

    @Test
    fun testCryptoMonthlySerializer() {

        val cryptoMonthlyString: String = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"Monthly Prices and Volumes for Digital Currency\",\n" +
                "        \"2. Digital Currency Code\": \"BTC\",\n" +
                "        \"3. Digital Currency Name\": \"Bitcoin\",\n" +
                "        \"4. Market Code\": \"USD\",\n" +
                "        \"5. Market Name\": \"United States Dollar\",\n" +
                "        \"6. Last Refreshed\": \"2023-10-16 00:00:00\",\n" +
                "        \"7. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series (Digital Currency Monthly)\": {\n" +
                "        \"2023-10-16\": {\n" +
                "            \"1a. open (USD)\": \"26962.57000000\",\n" +
                "            \"1b. open (USD)\": \"26962.57000000\",\n" +
                "            \"2a. high (USD)\": \"28580.00000000\",\n" +
                "            \"2b. high (USD)\": \"28580.00000000\",\n" +
                "            \"3a. low (USD)\": \"26538.66000000\",\n" +
                "            \"3b. low (USD)\": \"26538.66000000\",\n" +
                "            \"4a. close (USD)\": \"27168.36000000\",\n" +
                "            \"4b. close (USD)\": \"27168.36000000\",\n" +
                "            \"5. volume\": \"407747.79064000\",\n" +
                "            \"6. market cap (USD)\": \"407747.79064000\"\n" +
                "        },\n" +
                "        \"2023-09-30\": {\n" +
                "            \"1a. open (USD)\": \"25940.77000000\",\n" +
                "            \"1b. open (USD)\": \"25940.77000000\",\n" +
                "            \"2a. high (USD)\": \"27483.57000000\",\n" +
                "            \"2b. high (USD)\": \"27483.57000000\",\n" +
                "            \"3a. low (USD)\": \"24901.00000000\",\n" +
                "            \"3b. low (USD)\": \"24901.00000000\",\n" +
                "            \"4a. close (USD)\": \"26962.56000000\",\n" +
                "            \"4b. close (USD)\": \"26962.56000000\",\n" +
                "            \"5. volume\": \"809329.04893000\",\n" +
                "            \"6. market cap (USD)\": \"809329.04893000\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val cryptoMonthlyMetaData = CryptoMonthlyMetaData(
            "Monthly Prices and Volumes for Digital Currency",
            "BTC",
            "Bitcoin",
            "USD",
            "United States Dollar",
            "2023-10-16T00:00:00Z".toInstant(),
            "UTC"
        )
        val cryptoUnits: List<CryptoUnit> = listOf(
            CryptoUnit(
                "2023-10-16T00:00:00Z".toInstant(),
                26962.57000000,
                26962.57000000,
                28580.00000000,
                28580.00000000,
                26538.66000000,
                26538.66000000,
                27168.36000000,
                27168.36000000,
                407747.79064000,
                407747.79064000
            ),
            CryptoUnit(
                "2023-09-30T00:00:00Z".toInstant(),
                25940.77000000,
                25940.77000000,
                27483.57000000,
                27483.57000000,
                24901.00000000,
                24901.00000000,
                26962.56000000,
                26962.56000000,
                809329.04893000,
                809329.04893000
            )
        )

        val cryptoMonthlyObject = CryptoMonthly(cryptoMonthlyMetaData, cryptoUnits)
        println(cryptoMonthlyObject)
        val cryptoMonthlyFromString: CryptoMonthly = json.decodeFromString(cryptoMonthlyString)
        val cryptoMonthlyToString: String = json.encodeToString(CryptoMonthlySerializer, cryptoMonthlyFromString)
        assertEquals(cryptoMonthlyObject, cryptoMonthlyFromString, "Decoded CryptoMonthly does not match given CryptoMonthly")
        assertEquals(cryptoMonthlyString, cryptoMonthlyToString, "Encoded CryptoMonthly String does not match given CryptoMonthly String")
    }
}