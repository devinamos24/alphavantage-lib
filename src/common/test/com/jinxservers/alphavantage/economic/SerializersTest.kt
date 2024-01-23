package com.jinxservers.alphavantage.economic

import com.jinxservers.alphavantage.economic.response.EconomicObject
import com.jinxservers.alphavantage.economic.response.EconomicUnit
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
    fun testEconomicObjectSerializer() {

        val economicObjectString: String = "{\n" +
                "    \"name\": \"Real Gross Domestic Product\",\n" +
                "    \"interval\": \"annual\",\n" +
                "    \"unit\": \"billions of dollars\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"date\": \"2022-01-01\",\n" +
                "            \"value\": \"21822.037\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-01\",\n" +
                "            \"value\": \".\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"

        val name = "Real Gross Domestic Product"
        val interval = "annual"
        val unit = "billions of dollars"
        val data: List<EconomicUnit> = listOf(
            EconomicUnit("2022-01-01T00:00:00Z".toInstant(), 21822.037),
            EconomicUnit("2021-01-01T00:00:00Z".toInstant(), null)
        )

        val economicObject = EconomicObject(name, interval, unit, data)
        val economicObjectFromString: EconomicObject = json.decodeFromString(economicObjectString)
        val economicObjectToString: String = json.encodeToString(EconomicObject.serializer(), economicObjectFromString)
        assertEquals(economicObject, economicObjectFromString, "Decoded EconomicObject does not match given EconomicObject")
        assertEquals(economicObjectString, economicObjectToString, "Encoded EconomicObject String does not match given EconomicObject String")
    }
}