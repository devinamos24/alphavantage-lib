package com.jinxservers.alphavantage.alphaIntelligence

import com.jinxservers.alphavantage.alphaIntelligence.response.*
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
    fun testMarketNewsUnitSerializer() {

        val marketNewsUnitString: String = "{\n" +
                "    \"title\": \"Warren Buffett's Hold And Exit Strategies, And What That Means For Apple\",\n" +
                "    \"url\": \"https://www.investors.com/research/apple-stock-warren-buffett-sp500-magnificent-seven-berkshire-hathaway/\",\n" +
                "    \"time_published\": \"20240319T191255\",\n" +
                "    \"authors\": [\n" +
                "        \"Investor's Business Daily\",\n" +
                "        \"VIDYA RAMAKRISHNAN\"\n" +
                "    ],\n" +
                "    \"summary\": \"A few weeks ago, it was reported Warren Buffett's Berkshire Hathaway ( BRKB ) sold a small chunk of Apple ( AAPL ) stock among other issues during the fourth quarter that ended Dec. 31.\",\n" +
                "    \"banner_image\": \"https://www.investors.com/wp-content/uploads/2024/03/Stock-WarrenBuffett-smile-shut.jpg\",\n" +
                "    \"source\": \"Investors Business Daily\",\n" +
                "    \"category_within_source\": \"n/a\",\n" +
                "    \"source_domain\": \"www.investors.com\",\n" +
                "    \"topics\": [\n" +
                "        {\n" +
                "            \"topic\": \"Financial Markets\",\n" +
                "            \"relevance_score\": \"0.999956\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"topic\": \"Manufacturing\",\n" +
                "            \"relevance_score\": \"0.333333\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"overall_sentiment_score\": 0.119642,\n" +
                "    \"overall_sentiment_label\": \"Neutral\",\n" +
                "    \"ticker_sentiment\": [\n" +
                "        {\n" +
                "            \"ticker\": \"AAPL\",\n" +
                "            \"relevance_score\": \"0.739953\",\n" +
                "            \"ticker_sentiment_score\": \"0.070764\",\n" +
                "            \"ticker_sentiment_label\": \"Neutral\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"ticker\": \"KO\",\n" +
                "            \"relevance_score\": \"0.162255\",\n" +
                "            \"ticker_sentiment_score\": \"0.230381\",\n" +
                "            \"ticker_sentiment_label\": \"Somewhat-Bullish\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"

        val marketNewsUnit = MarketNewsUnit(
            title = "Warren Buffett's Hold And Exit Strategies, And What That Means For Apple",
            url = "https://www.investors.com/research/apple-stock-warren-buffett-sp500-magnificent-seven-berkshire-hathaway/",
            timePublished = "2024-03-19T19:12:55Z".toInstant(),
            authors = listOf(
                "Investor's Business Daily",
                "VIDYA RAMAKRISHNAN"
            ),
            summary = "A few weeks ago, it was reported Warren Buffett's Berkshire Hathaway ( BRKB ) sold a small chunk of Apple ( AAPL ) stock among other issues during the fourth quarter that ended Dec. 31.",
            bannerImage = "https://www.investors.com/wp-content/uploads/2024/03/Stock-WarrenBuffett-smile-shut.jpg",
            source = "Investors Business Daily",
            categoryWithinSource = "n/a",
            sourceDomain = "www.investors.com",
            topics = listOf(
                TopicUnit(
                    topic = "Financial Markets",
                    relevanceScore = 0.999956
                ),
                TopicUnit(
                    topic = "Manufacturing",
                    relevanceScore = 0.333333
                )
            ),
            overallSentimentScore = 0.119642,
            overallSentimentLabel = "Neutral",
            tickerSentiment = listOf(
                TickerSentimentUnit(
                    ticker = "AAPL",
                    relevanceScore = 0.739953,
                    tickerSentimentScore = 0.070764,
                    tickerSentimentLabel = "Neutral"
                ),
                TickerSentimentUnit(
                    ticker = "KO",
                    relevanceScore = 0.162255,
                    tickerSentimentScore = 0.230381,
                    tickerSentimentLabel = "Somewhat-Bullish"
                )
            )
        )

        val marketNewsUnitFromString: MarketNewsUnit = json.decodeFromString(marketNewsUnitString)
        val marketNewsUnitToString: String = json.encodeToString(MarketNewsUnit.serializer(), marketNewsUnitFromString)
        assertEquals(marketNewsUnit, marketNewsUnitFromString, "Decoded MarketNewsUnit does not match given MarketNewsUnit")
        assertEquals(marketNewsUnitString, marketNewsUnitToString, "Encoded MarketNewsUnit String does not match given MarketNewsUnit String")

    }

    @Test
    fun testMarketNewsSerializer() {

        val marketNewsString: String = "{\n" +
                "    \"items\": \"50\",\n" +
                "    \"sentiment_score_definition\": \"x <= -0.35: Bearish; -0.35 < x <= -0.15: Somewhat-Bearish; -0.15 < x < 0.15: Neutral; 0.15 <= x < 0.35: Somewhat_Bullish; x >= 0.35: Bullish\",\n" +
                "    \"relevance_score_definition\": \"0 < x <= 1, with a higher score indicating higher relevance.\",\n" +
                "    \"feed\": [\n" +
                "        {\n" +
                "            \"title\": \"Warren Buffett's Hold And Exit Strategies, And What That Means For Apple\",\n" +
                "            \"url\": \"https://www.investors.com/research/apple-stock-warren-buffett-sp500-magnificent-seven-berkshire-hathaway/\",\n" +
                "            \"time_published\": \"20240319T191255\",\n" +
                "            \"authors\": [\n" +
                "                \"Investor's Business Daily\",\n" +
                "                \"VIDYA RAMAKRISHNAN\"\n" +
                "            ],\n" +
                "            \"summary\": \"A few weeks ago, it was reported Warren Buffett's Berkshire Hathaway ( BRKB ) sold a small chunk of Apple ( AAPL ) stock among other issues during the fourth quarter that ended Dec. 31.\",\n" +
                "            \"banner_image\": \"https://www.investors.com/wp-content/uploads/2024/03/Stock-WarrenBuffett-smile-shut.jpg\",\n" +
                "            \"source\": \"Investors Business Daily\",\n" +
                "            \"category_within_source\": \"n/a\",\n" +
                "            \"source_domain\": \"www.investors.com\",\n" +
                "            \"topics\": [\n" +
                "                {\n" +
                "                    \"topic\": \"Financial Markets\",\n" +
                "                    \"relevance_score\": \"0.999956\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"topic\": \"Manufacturing\",\n" +
                "                    \"relevance_score\": \"0.333333\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"overall_sentiment_score\": 0.119642,\n" +
                "            \"overall_sentiment_label\": \"Neutral\",\n" +
                "            \"ticker_sentiment\": [\n" +
                "                {\n" +
                "                    \"ticker\": \"AAPL\",\n" +
                "                    \"relevance_score\": \"0.739953\",\n" +
                "                    \"ticker_sentiment_score\": \"0.070764\",\n" +
                "                    \"ticker_sentiment_label\": \"Neutral\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"ticker\": \"KO\",\n" +
                "                    \"relevance_score\": \"0.162255\",\n" +
                "                    \"ticker_sentiment_score\": \"0.230381\",\n" +
                "                    \"ticker_sentiment_label\": \"Somewhat-Bullish\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\": \"Apple's AI Focus Could Crimp Profit Margins, Analyst Says\",\n" +
                "            \"url\": \"https://www.investors.com/news/technology/artificial-intelligence-news-apple-ai-focus-profit-margins/\",\n" +
                "            \"time_published\": \"20240319T185435\",\n" +
                "            \"authors\": [\n" +
                "                \"PATRICK SEITZ\",\n" +
                "                \"Investor's Business Daily\"\n" +
                "            ],\n" +
                "            \"summary\": \"Apple's ( AAPL ) recent scramble to catch up to other tech firms in the field of artificial intelligence likely will pressure its profit margins, an investment firm says.\",\n" +
                "            \"banner_image\": \"https://www.investors.com/wp-content/uploads/2024/03/Stock-Apple-Gangnam-02-company.jpg\",\n" +
                "            \"source\": \"Investors Business Daily\",\n" +
                "            \"category_within_source\": \"n/a\",\n" +
                "            \"source_domain\": \"www.investors.com\",\n" +
                "            \"topics\": [\n" +
                "                {\n" +
                "                    \"topic\": \"Financial Markets\",\n" +
                "                    \"relevance_score\": \"0.108179\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"topic\": \"Manufacturing\",\n" +
                "                    \"relevance_score\": \"0.5\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"overall_sentiment_score\": 0.11242,\n" +
                "            \"overall_sentiment_label\": \"Neutral\",\n" +
                "            \"ticker_sentiment\": [\n" +
                "                {\n" +
                "                    \"ticker\": \"MSFT\",\n" +
                "                    \"relevance_score\": \"0.147818\",\n" +
                "                    \"ticker_sentiment_score\": \"0.0\",\n" +
                "                    \"ticker_sentiment_label\": \"Neutral\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"ticker\": \"GOOG\",\n" +
                "                    \"relevance_score\": \"0.147818\",\n" +
                "                    \"ticker_sentiment_score\": \"0.052633\",\n" +
                "                    \"ticker_sentiment_label\": \"Neutral\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}"

        val marketNews = MarketNews(
            items = 50,
            sentimentScoreDefinition = "x <= -0.35: Bearish; -0.35 < x <= -0.15: Somewhat-Bearish; -0.15 < x < 0.15: Neutral; 0.15 <= x < 0.35: Somewhat_Bullish; x >= 0.35: Bullish",
            relevanceScoreDefinition = "0 < x <= 1, with a higher score indicating higher relevance.",
            feed = listOf(
                MarketNewsUnit(
                    title = "Warren Buffett's Hold And Exit Strategies, And What That Means For Apple",
                    url = "https://www.investors.com/research/apple-stock-warren-buffett-sp500-magnificent-seven-berkshire-hathaway/",
                    timePublished = "2024-03-19T19:12:55Z".toInstant(),
                    authors = listOf(
                        "Investor's Business Daily",
                        "VIDYA RAMAKRISHNAN"
                    ),
                    summary = "A few weeks ago, it was reported Warren Buffett's Berkshire Hathaway ( BRKB ) sold a small chunk of Apple ( AAPL ) stock among other issues during the fourth quarter that ended Dec. 31.",
                    bannerImage = "https://www.investors.com/wp-content/uploads/2024/03/Stock-WarrenBuffett-smile-shut.jpg",
                    source = "Investors Business Daily",
                    categoryWithinSource = "n/a",
                    sourceDomain = "www.investors.com",
                    topics = listOf(
                        TopicUnit(
                            topic = "Financial Markets",
                            relevanceScore = 0.999956
                        ),
                        TopicUnit(
                            topic = "Manufacturing",
                            relevanceScore = 0.333333
                        )
                    ),
                    overallSentimentScore = 0.119642,
                    overallSentimentLabel = "Neutral",
                    tickerSentiment = listOf(
                        TickerSentimentUnit(
                            ticker = "AAPL",
                            relevanceScore = 0.739953,
                            tickerSentimentScore = 0.070764,
                            tickerSentimentLabel = "Neutral"
                        ),
                        TickerSentimentUnit(
                            ticker = "KO",
                            relevanceScore = 0.162255,
                            tickerSentimentScore = 0.230381,
                            tickerSentimentLabel = "Somewhat-Bullish"
                        )
                    )
                ),
                MarketNewsUnit(
                    title = "Apple's AI Focus Could Crimp Profit Margins, Analyst Says",
                    url = "https://www.investors.com/news/technology/artificial-intelligence-news-apple-ai-focus-profit-margins/",
                    timePublished = "2024-03-19T18:54:35Z".toInstant(),
                    authors = listOf(
                        "PATRICK SEITZ",
                        "Investor's Business Daily"
                    ),
                    summary = "Apple's ( AAPL ) recent scramble to catch up to other tech firms in the field of artificial intelligence likely will pressure its profit margins, an investment firm says.",
                    bannerImage = "https://www.investors.com/wp-content/uploads/2024/03/Stock-Apple-Gangnam-02-company.jpg",
                    source = "Investors Business Daily",
                    categoryWithinSource = "n/a",
                    sourceDomain = "www.investors.com",
                    topics = listOf(
                        TopicUnit(
                            topic = "Financial Markets",
                            relevanceScore = 0.108179
                        ),
                        TopicUnit(
                            topic = "Manufacturing",
                            relevanceScore = 0.5
                        )
                    ),
                    overallSentimentScore = 0.11242,
                    overallSentimentLabel = "Neutral",
                    tickerSentiment = listOf(
                        TickerSentimentUnit(
                            ticker = "MSFT",
                            relevanceScore = 0.147818,
                            tickerSentimentScore = 0.0,
                            tickerSentimentLabel = "Neutral"
                        ),
                        TickerSentimentUnit(
                            ticker = "GOOG",
                            relevanceScore = 0.147818,
                            tickerSentimentScore = 0.052633,
                            tickerSentimentLabel = "Neutral"
                        )
                    )
                )
            )
        )

        val marketNewsFromString: MarketNews = json.decodeFromString(marketNewsString)
        val marketNewsToString: String = json.encodeToString(MarketNews.serializer(), marketNewsFromString)
        assertEquals(marketNews, marketNewsFromString, "Decoded MarketNews does not match given MarketNews")
        assertEquals(marketNewsString, marketNewsToString, "Encoded MarketNews String does not match given MarketNews String")

    }

    @Test
    fun testTradeTickerUnitSerializer() {

        val tradeTickerUnitString = "{\n" +
                "    \"ticker\": \"ARTLW\",\n" +
                "    \"price\": \"0.0721\",\n" +
                "    \"change_amount\": \"0.0611\",\n" +
                "    \"change_percentage\": \"555.4545%\",\n" +
                "    \"volume\": \"21649\"\n" +
                "}"

        val tradeTickerUnit = TradeTickerUnit(
            ticker = "ARTLW",
            price = 0.0721,
            changeAmount = 0.0611,
            changePercentage = 5.554545,
            volume = 21649
        )

        val tradeTickerUnitFromString: TradeTickerUnit = json.decodeFromString(tradeTickerUnitString)
        val tradeTickerUnitToString: String = json.encodeToString(TradeTickerUnit.serializer(), tradeTickerUnitFromString)
        assertEquals(tradeTickerUnit, tradeTickerUnitFromString, "Decoded TradeTickerUnit does not match given TradeTickerUnit")
        assertEquals(tradeTickerUnitString, tradeTickerUnitToString, "Encoded TradeTickerUnit does not match given TradeTickerUnit String")

    }

    @Test
    fun testTopTickersSerializer() {

        val topTickersString = "{\n" +
                "    \"metadata\": \"Top gainers, losers, and most actively traded US tickers\",\n" +
                "    \"last_updated\": \"2024-03-18 16:15:59 US/Eastern\",\n" +
                "    \"top_gainers\": [\n" +
                "        {\n" +
                "            \"ticker\": \"ARTLW\",\n" +
                "            \"price\": \"0.0721\",\n" +
                "            \"change_amount\": \"0.0611\",\n" +
                "            \"change_percentage\": \"555.4545%\",\n" +
                "            \"volume\": \"21649\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"ticker\": \"YSG\",\n" +
                "            \"price\": \"2.26\",\n" +
                "            \"change_amount\": \"1.77\",\n" +
                "            \"change_percentage\": \"361.2245%\",\n" +
                "            \"volume\": \"693943\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"top_losers\": [\n" +
                "        {\n" +
                "            \"ticker\": \"GMM\",\n" +
                "            \"price\": \"0.9703\",\n" +
                "            \"change_amount\": \"-1.9697\",\n" +
                "            \"change_percentage\": \"-66.9966%\",\n" +
                "            \"volume\": \"5457159\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"ticker\": \"PETVW\",\n" +
                "            \"price\": \"0.05\",\n" +
                "            \"change_amount\": \"-0.1\",\n" +
                "            \"change_percentage\": \"-66.6667%\",\n" +
                "            \"volume\": \"21001\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"most_actively_traded\": [\n" +
                "        {\n" +
                "            \"ticker\": \"FSR\",\n" +
                "            \"price\": \"0.1468\",\n" +
                "            \"change_amount\": \"-0.0279\",\n" +
                "            \"change_percentage\": \"-15.9702%\",\n" +
                "            \"volume\": \"286865487\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"ticker\": \"VERB\",\n" +
                "            \"price\": \"0.36\",\n" +
                "            \"change_amount\": \"-0.0822\",\n" +
                "            \"change_percentage\": \"-18.5889%\",\n" +
                "            \"volume\": \"244942434\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"

        val topTickers = TopTickers(
            metadata = "Top gainers, losers, and most actively traded US tickers",
            lastUpdated = "2024-03-18T16:15:59Z".toInstant(),
            topGainers = listOf(
                TradeTickerUnit(
                    ticker = "ARTLW",
                    price = 0.0721,
                    changeAmount = 0.0611,
                    changePercentage = 5.554545,
                    volume = 21649
                ),
                TradeTickerUnit(
                    ticker = "YSG",
                    price = 2.26,
                    changeAmount = 1.77,
                    changePercentage = 3.612245,
                    volume = 693943
                )
            ),
            topLosers = listOf(
                TradeTickerUnit(
                    ticker = "GMM",
                    price = 0.9703,
                    changeAmount = -1.9697,
                    changePercentage = -0.669966,
                    volume = 5457159
                ),
                TradeTickerUnit(
                    ticker = "PETVW",
                    price = 0.05,
                    changeAmount = -0.1,
                    changePercentage = -0.666667,
                    volume = 21001
                )
            ),
            mostActivelyTraded = listOf(
                TradeTickerUnit(
                    ticker = "FSR",
                    price = 0.1468,
                    changeAmount = -0.0279,
                    changePercentage = -0.159702,
                    volume = 286865487
                ),
                TradeTickerUnit(
                    ticker = "VERB",
                    price = 0.36,
                    changeAmount = -0.0822,
                    changePercentage = -0.185889,
                    volume = 244942434
                )
            )
        )

        val topTickersFromString: TopTickers = json.decodeFromString(topTickersString)
        val topTickersToString: String = json.encodeToString(TopTickers.serializer(), topTickersFromString)
        assertEquals(topTickers, topTickersFromString, "Decoded TopTickers does not match given TopTickers")
        assertEquals(topTickersString, topTickersToString, "Encoded TopTickers does not match given TopTickers String")

    }

    @Test
    fun testAdvancedAnalyticsFixedWindowSerializer() {
        //TODO: Implement Test
    }

    @Test
    fun testAdvancedAnalyticsSlidingWindowSerializer() {
        //TODO: Implement Test
    }

}