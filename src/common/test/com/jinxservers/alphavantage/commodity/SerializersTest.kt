package com.jinxservers.alphavantage.commodity

import com.jinxservers.alphavantage.commodity.response.CommodityObject
import com.jinxservers.alphavantage.commodity.response.CommodityUnit
import com.jinxservers.alphavantage.crypto.response.CryptoIntradayMetaData
import com.jinxservers.alphavantage.crypto.response.CryptoUnit
import com.jinxservers.alphavantage.forex.response.CurrencyExchangeRate
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
    fun testCommodityObjectSerializer() {

        val commodityObjectString: String = "{\n" +
                "    \"name\": \"Henry Hub Natural Gas Spot Price\",\n" +
                "    \"interval\": \"monthly\",\n" +
                "    \"unit\": \"dollars per million BTU\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"date\": \"2023-09-01\",\n" +
                "            \"value\": \"2.64\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2023-08-01\",\n" +
                "            \"value\": \".\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"

        val name = "Henry Hub Natural Gas Spot Price"
        val interval = "monthly"
        val unit = "dollars per million BTU"
        val data: List<CommodityUnit> = listOf(
            CommodityUnit("2023-09-01T00:00:00Z".toInstant(), 2.64),
            CommodityUnit("2023-08-01T00:00:00Z".toInstant(), null)
        )

        val commodityObject = CommodityObject(name, interval, unit, data)
        val commodityObjectFromString: CommodityObject = json.decodeFromString(commodityObjectString)
        val commodityObjectToString: String = json.encodeToString(CommodityObject.serializer(), commodityObjectFromString)
        assertEquals(commodityObject, commodityObjectFromString, "Decoded CommodityObject does not match given CommodityObject")
        assertEquals(commodityObjectString, commodityObjectToString, "Encoded CommodityObject String does not match given CommodityObject String")
    }
}