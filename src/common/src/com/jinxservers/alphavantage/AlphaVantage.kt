package com.jinxservers.alphavantage

import com.jinxservers.alphavantage.commodity.request.CommodityRequest
import com.jinxservers.alphavantage.core.request.CoreRequest
import com.jinxservers.alphavantage.crypto.request.CryptoRequest
import com.jinxservers.alphavantage.economic.request.EconomicRequest
import com.jinxservers.alphavantage.forex.request.DemoForexRequest
import com.jinxservers.alphavantage.forex.request.ForexRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * Base class for storing an api [key], and a [client] for retrieving api data.
 *
 * @param clientModifications modifications to the client used for retrieving api data.
 */

public sealed class AlphaVantageBase(
    private val key: String,
    clientModifications: HttpClient.() -> Unit
) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    init {
        client.clientModifications()
    }

    /**
     * Accepts a [request] and asynchronously retrieves an object ([T]) from the alpha vantage api.
     */
     internal suspend inline fun <reified T : Any> request(request: Request<T>): T {
        request.urlBuilder.apply {
            protocol = URLProtocol.HTTPS
            host = "www.alphavantage.co"
            path("query")
            parameters.append("apikey", key)
        }
        try {
            return client.get(request.urlBuilder.build()).body()
        } catch (e: Exception) {
            e.printStackTrace()
            throw AlphaVantageException("Client failed to get correct data")
        }
    }
}

/**
 * Class used
 */
public class AlphaVantage(
    key: String,
    clientModifications: HttpClient.() -> Unit = {}
) : AlphaVantageBase(key, clientModifications) {

    public fun core(): CoreRequest = CoreRequest(this)

    public fun forex(): ForexRequest = ForexRequest(this)

    public fun crypto(): CryptoRequest = CryptoRequest(this)

    public fun commodity(): CommodityRequest = CommodityRequest(this)

    public fun economic(): EconomicRequest = EconomicRequest(this)
}



public class AlphaVantageDemo(
    clientModifications: HttpClient.() -> Unit = {}
) : AlphaVantageBase("demo", clientModifications) {

    public fun forex(): DemoForexRequest = DemoForexRequest(this)

}