# Alpha Vantage API Wrapper Kotlin/Multiplatform

This library allows developers to make suspending api calls with minimal code.

## Example

The following snippet of code shows just how little code is required to get started.

```Kotlin
val alphaVantage = AlphaVantage("api-key")
val intradayDataIBM = alphaVantage.core().getIntraday("IBM", ShortInterval.FIVE_MINUTES)
```

There are also customization options for the client depending on the developer's needs.
The example below shows how to configure proxy settings for the built-in client

```Kotlin
val alphaVantage = AlphaVantage("api-key") { client ->
    engine {
        proxy = ProxyBuilder.http("http://sample-proxy-server:3128")
    }
}
```

Under the hood, this library uses Ktor to make http requests. 
That means this wrapper supports any client customization options available through Ktor.
You could even [make your own plugin](https://ktor.io/docs/client-custom-plugins.html) to further customize your client.

## Installation

### Maven

Add the following dependency to your ```pom.xml``` file:
```xml
<dependency>
    <groupId>com.jinxservers</groupId>
    <artifactId>alphavantage-lib</artifactId>
    <version>VERSION</version>
</dependency>
```

### Gradle

Add the following to your gradle build script:

```Kotlin
implementation("com.jinxservers:alphavantage-lib:VERSION")
```

### Notes

This wrapper is still very young, if you find any issues or bugs please submit an issue.

