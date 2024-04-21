package com.jinxservers.alphavantage.technicalIndicators

import com.jinxservers.alphavantage.technicalIndicators.response.TechnicalIndicatorMetaData
import com.jinxservers.alphavantage.technicalIndicators.response.TechnicalIndicatorObject
import com.jinxservers.alphavantage.technicalIndicators.response.TechnicalIndicatorUnit
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
    fun testTechnicalIndicatorObjectSerializer() {

        val technicalIndicatorObjectString = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1: Symbol\": \"IBM\",\n" +
                "        \"2: Indicator\": \"Simple Moving Average (SMA)\",\n" +
                "        \"3: Last Refreshed\": \"2024-04-19\",\n" +
                "        \"4: Interval\": \"weekly\",\n" +
                "        \"5: Time Period\": 10,\n" +
                "        \"6: Series Type\": \"open\",\n" +
                "        \"7: Time Zone\": \"US/Eastern\"\n" +
                "    },\n" +
                "    \"Technical Analysis: SMA\": {\n" +
                "        \"2024-04-19\": {\n" +
                "            \"SMA\": \"188.8760\"\n" +
                "        },\n" +
                "        \"2024-04-12\": {\n" +
                "            \"SMA\": \"188.7045\"\n" +
                "        }\n" +
                "    }\n" +
                "}"

        val technicalIndicatorObject = TechnicalIndicatorObject(
            TechnicalIndicatorMetaData(
                symbol = "IBM",
                indicator = "Simple Moving Average (SMA)",
                lastRefreshed = "2024-04-19T00:00:00Z".toInstant(),
                interval = "weekly",
                timePeriod = 10,
                seriesType = "open",
                timeZone = "US/Eastern"
            ),
            listOf(
                TechnicalIndicatorUnit(
                    timestamp = "2024-04-19T00:00:00Z".toInstant(),
                    value = 188.8760
                ),
                TechnicalIndicatorUnit(
                    timestamp = "2024-04-12T00:00:00Z".toInstant(),
                    value = 188.7045
                )
            )
        )

        val technicalIndicatorObjectFromString: TechnicalIndicatorObject = json.decodeFromString(technicalIndicatorObjectString)
        val technicalIndicatorObjectToString: String = json.encodeToString(TechnicalIndicatorObject.serializer(), technicalIndicatorObjectFromString)
        assertEquals(technicalIndicatorObject, technicalIndicatorObjectFromString, "Decoded TechnicalIndicatorObject does not match given TechnicalIndicatorObject")
        assertEquals(technicalIndicatorObjectString, technicalIndicatorObjectToString, "Encoded TechnicalIndicatorObject String does not match given TechnicalIndicatorObject String")
    }

}