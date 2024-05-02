package com.jinxservers.alphavantage.technicalIndicators.request

import com.jinxservers.alphavantage.AlphaVantage
import com.jinxservers.alphavantage.technicalIndicators.response.*
import com.jinxservers.alphavantage.util.MAType
import com.jinxservers.alphavantage.util.SeriesType
import com.jinxservers.alphavantage.util.ShortInterval
import com.jinxservers.alphavantage.util.ShortLongInterval

internal val monthCompatibleIntervals = listOf("1min", "5min", "15min", "30min", "60min")

public class TechnicalIndicatorRequest internal constructor(
    private val alphaVantage: AlphaVantage
) {

    public suspend fun getSMA(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): SMA {
        return alphaVantage.request(BasicIndicatorSeriesRequest("SMA", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getEMA(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): EMA {
        return alphaVantage.request(BasicIndicatorSeriesRequest("EMA", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getWMA(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): WMA {
        return alphaVantage.request(BasicIndicatorSeriesRequest("WMA", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getDEMA(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): DEMA {
        return alphaVantage.request(BasicIndicatorSeriesRequest("DEMA", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getTEMA(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): TEMA {
        return alphaVantage.request(BasicIndicatorSeriesRequest("TEMA", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getTRIMA(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): TRIMA {
        return alphaVantage.request(BasicIndicatorSeriesRequest("TRIMA", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getKAMA(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): KAMA {
        return alphaVantage.request(BasicIndicatorSeriesRequest("KAMA", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getMAMA(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType, fastLimit: Double? = null, slowLimit: Double? = null): MAMA {
        return alphaVantage.request(MAMARequest(symbol, interval, month, seriesType, fastLimit, slowLimit))
    }

    public suspend fun getVWAP(symbol: String, interval: ShortInterval, month: String? = null): VWAP {
        return alphaVantage.request(VWAPRequest(symbol, interval, month))
    }

    public suspend fun getT3(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): T3 {
        return alphaVantage.request(BasicIndicatorSeriesRequest("T3", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getMACD(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType, fastPeriod: Int? = null, slowPeriod: Int? = null, signalPeriod: Int? = null): MACD {
        return alphaVantage.request(MACDRequest(symbol, interval, month, seriesType, fastPeriod, slowPeriod, signalPeriod))
    }

    public suspend fun getMACDEXT(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType, fastPeriod: Int? = null, slowPeriod: Int? = null, signalPeriod: Int? = null, fastMAType: MAType? = null, slowMAType: MAType? = null, signalMAType: MAType? = null): MACDEXT {
        return alphaVantage.request(MACDEXTRequest(symbol, interval, month, seriesType, fastPeriod, slowPeriod, signalPeriod, fastMAType, slowMAType, signalMAType))
    }

    public suspend fun getSTOCH(symbol: String, interval: ShortLongInterval, month: String? = null, fastKPeriod: Int? = null, slowKPeriod: Int? = null, slowDPeriod: Int? = null, slowKMAType: MAType? = null, slowDMAType: MAType? = null): STOCH {
        return alphaVantage.request(STOCHRequest(symbol, interval, month, fastKPeriod, slowKPeriod, slowDPeriod, slowKMAType, slowDMAType))
    }

    public suspend fun getSTOCHF(symbol: String, interval: ShortLongInterval, month: String? = null, fastKPeriod: Int? = null, fastDPeriod: Int? = null, fastDMAType: MAType? = null): STOCHF {
        return alphaVantage.request(STOCHFRequest(symbol, interval, month, fastKPeriod, fastDPeriod, fastDMAType))
    }

    public suspend fun getRSI(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): RSI {
        return alphaVantage.request(BasicIndicatorSeriesRequest("RSI", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getSTOCHRSI(symbol: String, interval: ShortLongInterval, month: String? = null, fastKPeriod: Int? = null, fastDPeriod: Int? = null, fastDMAType: MAType? = null): STOCHRSI {
        return alphaVantage.request(STOCHRSIRequest(symbol, interval, month, fastKPeriod, fastDPeriod, fastDMAType))
    }

    public suspend fun getWILLR(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): WILLR {
        return alphaVantage.request(BasicIndicatorRequest("WILLR", symbol, interval, month, timePeriod))
    }

    public suspend fun getADX(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): ADX {
        return alphaVantage.request(BasicIndicatorRequest("ADX", symbol, interval, month, timePeriod))
    }

    public suspend fun getADXR(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): ADXR {
        return alphaVantage.request(BasicIndicatorRequest("ADXR", symbol, interval, month, timePeriod))
    }

    public suspend fun getAPO(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType, fastPeriod: Int? = null, slowPeriod: Int? = null, maType: MAType? = null): APO {
        return alphaVantage.request(APORequest(symbol, interval, month, seriesType, fastPeriod, slowPeriod, maType))
    }

    public suspend fun getPPO(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType, fastPeriod: Int? = null, slowPeriod: Int? = null, maType: MAType? = null): PPO {
        return alphaVantage.request(PPORequest(symbol, interval, month, seriesType, fastPeriod, slowPeriod, maType))
    }

    public suspend fun getMOM(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): MOM {
        return alphaVantage.request(BasicIndicatorSeriesRequest("MOM", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getBOP(symbol: String, interval: ShortLongInterval, month: String? = null): BOP {
        return alphaVantage.request(BasicIndicatorNoTimePeriodRequest("BOP", symbol, interval, month))
    }

    public suspend fun getCCI(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): CCI {
        return alphaVantage.request(BasicIndicatorRequest("CCI", symbol, interval, month, timePeriod))
    }

    public suspend fun getCMO(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): CMO {
        return alphaVantage.request(BasicIndicatorSeriesRequest("CMO", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getROC(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): ROC {
        return alphaVantage.request(BasicIndicatorSeriesRequest("ROC", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getROCR(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): ROCR {
        return alphaVantage.request(BasicIndicatorSeriesRequest("ROCR", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getAROON(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): AROON {
        return alphaVantage.request(BasicIndicatorRequest("AROON", symbol, interval, month, timePeriod))
    }

    public suspend fun getAROONOSC(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): AROONOSC {
        return alphaVantage.request(BasicIndicatorRequest("AROONOSC", symbol, interval, month, timePeriod))
    }

    public suspend fun getMFI(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): MFI {
        return alphaVantage.request(BasicIndicatorRequest("MFI", symbol, interval, month, timePeriod))
    }

    public suspend fun getTRIX(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): TRIX {
        return alphaVantage.request(BasicIndicatorSeriesRequest("TRIX", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getULTOSC(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod1: Int? = null, timePeriod2: Int? = null, timePeriod3: Int? = null): ULTOSC {
        return alphaVantage.request(ULTOSCRequest(symbol, interval, month, timePeriod1, timePeriod2, timePeriod3))
    }

    public suspend fun getDX(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): DX {
        return alphaVantage.request(BasicIndicatorRequest("DX", symbol, interval, month, timePeriod))
    }

    public suspend fun getMINUSDI(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): MINUSDI {
        return alphaVantage.request(BasicIndicatorRequest("MINUS_DI", symbol, interval, month, timePeriod))
    }

    public suspend fun getPLUSDI(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): PLUSDI {
        return alphaVantage.request(BasicIndicatorRequest("PLUS_DI", symbol, interval, month, timePeriod))
    }

    public suspend fun getMINUSDM(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): MINUSDM {
        return alphaVantage.request(BasicIndicatorRequest("MINUS_DM", symbol, interval, month, timePeriod))
    }

    public suspend fun getPLUSDM(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): PLUSDM {
        return alphaVantage.request(BasicIndicatorRequest("PLUS_DM", symbol, interval, month, timePeriod))
    }

    public suspend fun getBBANDS(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType, nbDevUp: Int? = null, nbDevDn: Int? = null, maType: MAType? = null): BBANDS {
        return alphaVantage.request(BBANDSRequest(symbol, interval, month, timePeriod, seriesType, nbDevUp, nbDevDn, maType))
    }

    public suspend fun getMIDPOINT(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): MIDPOINT {
        return alphaVantage.request(BasicIndicatorSeriesRequest("MIDPOINT", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getMIDPRICE(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int, seriesType: SeriesType): MIDPRICE {
        return alphaVantage.request(BasicIndicatorSeriesRequest("MIDPRICE", symbol, interval, month, timePeriod, seriesType))
    }

    public suspend fun getSAR(symbol: String, interval: ShortLongInterval, month: String? = null, acceleration: Double? = null, maximum: Double? = null): SAR {
        return alphaVantage.request(SARRequest(symbol, interval, month, acceleration, maximum))
    }

    public suspend fun getTRANGE(symbol: String, interval: ShortLongInterval, month: String? = null): TRANGE {
        return alphaVantage.request(BasicIndicatorNoTimePeriodRequest("TRANGE", symbol, interval, month))
    }

    public suspend fun getATR(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): ATR {
        return alphaVantage.request(BasicIndicatorRequest("ATR", symbol, interval, month, timePeriod))
    }

    public suspend fun getNATR(symbol: String, interval: ShortLongInterval, month: String? = null, timePeriod: Int): NATR {
        return alphaVantage.request(BasicIndicatorRequest("NATR", symbol, interval, month, timePeriod))
    }

    public suspend fun getAD(symbol: String, interval: ShortLongInterval, month: String? = null): AD {
        return alphaVantage.request(BasicIndicatorNoTimePeriodRequest("AD", symbol, interval, month))
    }

    public suspend fun getADOSC(symbol: String, interval: ShortLongInterval, month: String? = null, fastPeriod: Int? = null, slowPeriod: Int? = null): ADOSC {
        return alphaVantage.request(ADOSCRequest(symbol, interval, month, fastPeriod, slowPeriod))
    }

    public suspend fun getOBV(symbol: String, interval: ShortLongInterval, month: String? = null): OBV {
        return alphaVantage.request(BasicIndicatorNoTimePeriodRequest("OBV", symbol, interval, month))
    }

    public suspend fun getHTTRENDLINE(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType): HTTRENDLINE {
        return alphaVantage.request(GenericHTRequest("HT_TRENDLINE", symbol, interval, month, seriesType))
    }

    public suspend fun getHTSINE(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType): HTSINE {
        return alphaVantage.request(GenericHTRequest("HT_SINE", symbol, interval, month, seriesType))
    }

    public suspend fun getHTTRENDMODE(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType): HTTRENDMODE {
        return alphaVantage.request(GenericHTRequest("HT_TRENDMODE", symbol, interval, month, seriesType))
    }

    public suspend fun getHTDCPERIOD(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType): HTDCPERIOD {
        return alphaVantage.request(GenericHTRequest("HT_DCPERIOD", symbol, interval, month, seriesType))
    }

    public suspend fun getHTDCPHASE(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType): HTDCPHASE {
        return alphaVantage.request(GenericHTRequest("HT_DCPHASE", symbol, interval, month, seriesType))
    }

    public suspend fun getHTPHASOR(symbol: String, interval: ShortLongInterval, month: String? = null, seriesType: SeriesType): HTPHASOR {
        return alphaVantage.request(GenericHTRequest("HT_PHASOR", symbol, interval, month, seriesType))
    }
}