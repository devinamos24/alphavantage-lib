package com.jinxservers.alphavantage.technicalIndicators

import com.jinxservers.alphavantage.technicalIndicators.response.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object SMASerializer : KSerializer<SMA> {
    override val descriptor: SerialDescriptor = SMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: SMA) =
        encoder.encodeSerializableValue(SMASurrogate.serializer(), SMASurrogate(value))

    override fun deserialize(decoder: Decoder): SMA =
        SMA(decoder.decodeSerializableValue(SMASurrogate.serializer()))

}

internal object EMASerializer : KSerializer<EMA> {
    override val descriptor: SerialDescriptor = EMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: EMA) =
        encoder.encodeSerializableValue(EMASurrogate.serializer(), EMASurrogate(value))

    override fun deserialize(decoder: Decoder): EMA =
        EMA(decoder.decodeSerializableValue(EMASurrogate.serializer()))

}

internal object WMASerializer : KSerializer<WMA> {
    override val descriptor: SerialDescriptor = WMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: WMA) =
        encoder.encodeSerializableValue(WMASurrogate.serializer(), WMASurrogate(value))

    override fun deserialize(decoder: Decoder): WMA =
        WMA(decoder.decodeSerializableValue(WMASurrogate.serializer()))

}

internal object DEMASerializer : KSerializer<DEMA> {
    override val descriptor: SerialDescriptor = DEMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: DEMA) =
        encoder.encodeSerializableValue(DEMASurrogate.serializer(), DEMASurrogate(value))

    override fun deserialize(decoder: Decoder): DEMA =
        DEMA(decoder.decodeSerializableValue(DEMASurrogate.serializer()))

}

internal object TEMASerializer : KSerializer<TEMA> {
    override val descriptor: SerialDescriptor = TEMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: TEMA) =
        encoder.encodeSerializableValue(TEMASurrogate.serializer(), TEMASurrogate(value))

    override fun deserialize(decoder: Decoder): TEMA =
        TEMA(decoder.decodeSerializableValue(TEMASurrogate.serializer()))

}

internal object TRIMASerializer : KSerializer<TRIMA> {
    override val descriptor: SerialDescriptor = TRIMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: TRIMA) =
        encoder.encodeSerializableValue(TRIMASurrogate.serializer(), TRIMASurrogate(value))

    override fun deserialize(decoder: Decoder): TRIMA =
        TRIMA(decoder.decodeSerializableValue(TRIMASurrogate.serializer()))

}

internal object KAMASerializer : KSerializer<KAMA> {
    override val descriptor: SerialDescriptor = KAMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: KAMA) =
        encoder.encodeSerializableValue(KAMASurrogate.serializer(), KAMASurrogate(value))

    override fun deserialize(decoder: Decoder): KAMA =
        KAMA(decoder.decodeSerializableValue(KAMASurrogate.serializer()))

}

internal object MAMASerializer : KSerializer<MAMA> {
    override val descriptor: SerialDescriptor = MAMASurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MAMA) =
        encoder.encodeSerializableValue(MAMASurrogate.serializer(), MAMASurrogate(value))

    override fun deserialize(decoder: Decoder): MAMA =
        MAMA(decoder.decodeSerializableValue(MAMASurrogate.serializer()))

}

internal object VWAPSerializer : KSerializer<VWAP> {
    override val descriptor: SerialDescriptor = VWAPSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: VWAP) =
        encoder.encodeSerializableValue(VWAPSurrogate.serializer(), VWAPSurrogate(value))

    override fun deserialize(decoder: Decoder): VWAP =
        VWAP(decoder.decodeSerializableValue(VWAPSurrogate.serializer()))

}

internal object T3Serializer : KSerializer<T3> {
    override val descriptor: SerialDescriptor = T3Surrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: T3) =
        encoder.encodeSerializableValue(T3Surrogate.serializer(), T3Surrogate(value))

    override fun deserialize(decoder: Decoder): T3 =
        T3(decoder.decodeSerializableValue(T3Surrogate.serializer()))

}

internal object MACDSerializer : KSerializer<MACD> {
    override val descriptor: SerialDescriptor = MACDSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MACD) =
        encoder.encodeSerializableValue(MACDSurrogate.serializer(), MACDSurrogate(value))

    override fun deserialize(decoder: Decoder): MACD =
        MACD(decoder.decodeSerializableValue(MACDSurrogate.serializer()))

}

internal object MACDEXTSerializer : KSerializer<MACDEXT> {
    override val descriptor: SerialDescriptor = MACDEXTSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MACDEXT) =
        encoder.encodeSerializableValue(MACDEXTSurrogate.serializer(), MACDEXTSurrogate(value))

    override fun deserialize(decoder: Decoder): MACDEXT =
        MACDEXT(decoder.decodeSerializableValue(MACDEXTSurrogate.serializer()))

}

internal object STOCHSerializer : KSerializer<STOCH> {
    override val descriptor: SerialDescriptor = STOCHSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: STOCH) =
        encoder.encodeSerializableValue(STOCHSurrogate.serializer(), STOCHSurrogate(value))

    override fun deserialize(decoder: Decoder): STOCH =
        STOCH(decoder.decodeSerializableValue(STOCHSurrogate.serializer()))

}

internal object STOCHFSerializer : KSerializer<STOCHF> {
    override val descriptor: SerialDescriptor = STOCHFSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: STOCHF) =
        encoder.encodeSerializableValue(STOCHFSurrogate.serializer(), STOCHFSurrogate(value))

    override fun deserialize(decoder: Decoder): STOCHF =
        STOCHF(decoder.decodeSerializableValue(STOCHFSurrogate.serializer()))

}

internal object RSISerializer : KSerializer<RSI> {
    override val descriptor: SerialDescriptor = RSISurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: RSI) =
        encoder.encodeSerializableValue(RSISurrogate.serializer(), RSISurrogate(value))

    override fun deserialize(decoder: Decoder): RSI =
        RSI(decoder.decodeSerializableValue(RSISurrogate.serializer()))

}

internal object STOCHRSISerializer : KSerializer<STOCHRSI> {
    override val descriptor: SerialDescriptor = STOCHRSISurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: STOCHRSI) =
        encoder.encodeSerializableValue(STOCHRSISurrogate.serializer(), STOCHRSISurrogate(value))

    override fun deserialize(decoder: Decoder): STOCHRSI =
        STOCHRSI(decoder.decodeSerializableValue(STOCHRSISurrogate.serializer()))

}

internal object WILLRSerializer : KSerializer<WILLR> {
    override val descriptor: SerialDescriptor = WILLRSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: WILLR) =
        encoder.encodeSerializableValue(WILLRSurrogate.serializer(), WILLRSurrogate(value))

    override fun deserialize(decoder: Decoder): WILLR =
        WILLR(decoder.decodeSerializableValue(WILLRSurrogate.serializer()))

}

internal object ADXSerializer : KSerializer<ADX> {
    override val descriptor: SerialDescriptor = ADXSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ADX) =
        encoder.encodeSerializableValue(ADXSurrogate.serializer(), ADXSurrogate(value))

    override fun deserialize(decoder: Decoder): ADX =
        ADX(decoder.decodeSerializableValue(ADXSurrogate.serializer()))

}

internal object ADXRSerializer : KSerializer<ADXR> {
    override val descriptor: SerialDescriptor = ADXRSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ADXR) =
        encoder.encodeSerializableValue(ADXRSurrogate.serializer(), ADXRSurrogate(value))

    override fun deserialize(decoder: Decoder): ADXR =
        ADXR(decoder.decodeSerializableValue(ADXRSurrogate.serializer()))

}

internal object APOSerializer : KSerializer<APO> {
    override val descriptor: SerialDescriptor = APOSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: APO) =
        encoder.encodeSerializableValue(APOSurrogate.serializer(), APOSurrogate(value))

    override fun deserialize(decoder: Decoder): APO =
        APO(decoder.decodeSerializableValue(APOSurrogate.serializer()))

}

internal object PPOSerializer : KSerializer<PPO> {
    override val descriptor: SerialDescriptor = PPOSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: PPO) =
        encoder.encodeSerializableValue(PPOSurrogate.serializer(), PPOSurrogate(value))

    override fun deserialize(decoder: Decoder): PPO =
        PPO(decoder.decodeSerializableValue(PPOSurrogate.serializer()))

}

internal object MOMSerializer : KSerializer<MOM> {
    override val descriptor: SerialDescriptor = MOMSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MOM) =
        encoder.encodeSerializableValue(MOMSurrogate.serializer(), MOMSurrogate(value))

    override fun deserialize(decoder: Decoder): MOM =
        MOM(decoder.decodeSerializableValue(MOMSurrogate.serializer()))

}

internal object BOPSerializer : KSerializer<BOP> {
    override val descriptor: SerialDescriptor = BOPSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: BOP) =
        encoder.encodeSerializableValue(BOPSurrogate.serializer(), BOPSurrogate(value))

    override fun deserialize(decoder: Decoder): BOP =
        BOP(decoder.decodeSerializableValue(BOPSurrogate.serializer()))

}

internal object CCISerializer : KSerializer<CCI> {
    override val descriptor: SerialDescriptor = CCISurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: CCI) =
        encoder.encodeSerializableValue(CCISurrogate.serializer(), CCISurrogate(value))

    override fun deserialize(decoder: Decoder): CCI =
        CCI(decoder.decodeSerializableValue(CCISurrogate.serializer()))

}

internal object CMOSerializer : KSerializer<CMO> {
    override val descriptor: SerialDescriptor = CMOSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: CMO) =
        encoder.encodeSerializableValue(CMOSurrogate.serializer(), CMOSurrogate(value))

    override fun deserialize(decoder: Decoder): CMO =
        CMO(decoder.decodeSerializableValue(CMOSurrogate.serializer()))

}

internal object ROCSerializer : KSerializer<ROC> {
    override val descriptor: SerialDescriptor = ROCSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ROC) =
        encoder.encodeSerializableValue(ROCSurrogate.serializer(), ROCSurrogate(value))

    override fun deserialize(decoder: Decoder): ROC =
        ROC(decoder.decodeSerializableValue(ROCSurrogate.serializer()))

}

internal object ROCRSerializer : KSerializer<ROCR> {
    override val descriptor: SerialDescriptor = ROCRSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ROCR) =
        encoder.encodeSerializableValue(ROCRSurrogate.serializer(), ROCRSurrogate(value))

    override fun deserialize(decoder: Decoder): ROCR =
        ROCR(decoder.decodeSerializableValue(ROCRSurrogate.serializer()))

}

internal object AROONSerializer : KSerializer<AROON> {
    override val descriptor: SerialDescriptor = AROONSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: AROON) =
        encoder.encodeSerializableValue(AROONSurrogate.serializer(), AROONSurrogate(value))

    override fun deserialize(decoder: Decoder): AROON =
        AROON(decoder.decodeSerializableValue(AROONSurrogate.serializer()))

}

internal object AROONOSCSerializer : KSerializer<AROONOSC> {
    override val descriptor: SerialDescriptor = AROONOSCSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: AROONOSC) =
        encoder.encodeSerializableValue(AROONOSCSurrogate.serializer(), AROONOSCSurrogate(value))

    override fun deserialize(decoder: Decoder): AROONOSC =
        AROONOSC(decoder.decodeSerializableValue(AROONOSCSurrogate.serializer()))

}

internal object MFISerializer : KSerializer<MFI> {
    override val descriptor: SerialDescriptor = MFISurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MFI) =
        encoder.encodeSerializableValue(MFISurrogate.serializer(), MFISurrogate(value))

    override fun deserialize(decoder: Decoder): MFI =
        MFI(decoder.decodeSerializableValue(MFISurrogate.serializer()))

}

internal object TRIXSerializer : KSerializer<TRIX> {
    override val descriptor: SerialDescriptor = TRIXSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: TRIX) =
        encoder.encodeSerializableValue(TRIXSurrogate.serializer(), TRIXSurrogate(value))

    override fun deserialize(decoder: Decoder): TRIX =
        TRIX(decoder.decodeSerializableValue(TRIXSurrogate.serializer()))

}

internal object ULTOSCSerializer : KSerializer<ULTOSC> {
    override val descriptor: SerialDescriptor = ULTOSCSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ULTOSC) =
        encoder.encodeSerializableValue(ULTOSCSurrogate.serializer(), ULTOSCSurrogate(value))

    override fun deserialize(decoder: Decoder): ULTOSC =
        ULTOSC(decoder.decodeSerializableValue(ULTOSCSurrogate.serializer()))

}

internal object DXSerializer : KSerializer<DX> {
    override val descriptor: SerialDescriptor = DXSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: DX) =
        encoder.encodeSerializableValue(DXSurrogate.serializer(), DXSurrogate(value))

    override fun deserialize(decoder: Decoder): DX =
        DX(decoder.decodeSerializableValue(DXSurrogate.serializer()))

}

internal object MINUSDISerializer : KSerializer<MINUSDI> {
    override val descriptor: SerialDescriptor = MINUSDISurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MINUSDI) =
        encoder.encodeSerializableValue(MINUSDISurrogate.serializer(), MINUSDISurrogate(value))

    override fun deserialize(decoder: Decoder): MINUSDI =
        MINUSDI(decoder.decodeSerializableValue(MINUSDISurrogate.serializer()))

}

internal object PLUSDISerializer : KSerializer<PLUSDI> {
    override val descriptor: SerialDescriptor = PLUSDISurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: PLUSDI) =
        encoder.encodeSerializableValue(PLUSDISurrogate.serializer(), PLUSDISurrogate(value))

    override fun deserialize(decoder: Decoder): PLUSDI =
        PLUSDI(decoder.decodeSerializableValue(PLUSDISurrogate.serializer()))

}

internal object MINUSDMSerializer : KSerializer<MINUSDM> {
    override val descriptor: SerialDescriptor = MINUSDMSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MINUSDM) =
        encoder.encodeSerializableValue(MINUSDMSurrogate.serializer(), MINUSDMSurrogate(value))

    override fun deserialize(decoder: Decoder): MINUSDM =
        MINUSDM(decoder.decodeSerializableValue(MINUSDMSurrogate.serializer()))

}

internal object PLUSDMSerializer : KSerializer<PLUSDM> {
    override val descriptor: SerialDescriptor = PLUSDMSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: PLUSDM) =
        encoder.encodeSerializableValue(PLUSDMSurrogate.serializer(), PLUSDMSurrogate(value))

    override fun deserialize(decoder: Decoder): PLUSDM =
        PLUSDM(decoder.decodeSerializableValue(PLUSDMSurrogate.serializer()))

}

internal object BBANDSSerializer : KSerializer<BBANDS> {
    override val descriptor: SerialDescriptor = BBANDSSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: BBANDS) =
        encoder.encodeSerializableValue(BBANDSSurrogate.serializer(), BBANDSSurrogate(value))

    override fun deserialize(decoder: Decoder): BBANDS =
        BBANDS(decoder.decodeSerializableValue(BBANDSSurrogate.serializer()))

}

internal object MIDPOINTSerializer : KSerializer<MIDPOINT> {
    override val descriptor: SerialDescriptor = MIDPOINTSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MIDPOINT) =
        encoder.encodeSerializableValue(MIDPOINTSurrogate.serializer(), MIDPOINTSurrogate(value))

    override fun deserialize(decoder: Decoder): MIDPOINT =
        MIDPOINT(decoder.decodeSerializableValue(MIDPOINTSurrogate.serializer()))

}

internal object MIDPRICESerializer : KSerializer<MIDPRICE> {
    override val descriptor: SerialDescriptor = MIDPRICESurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MIDPRICE) =
        encoder.encodeSerializableValue(MIDPRICESurrogate.serializer(), MIDPRICESurrogate(value))

    override fun deserialize(decoder: Decoder): MIDPRICE =
        MIDPRICE(decoder.decodeSerializableValue(MIDPRICESurrogate.serializer()))

}

internal object SARSerializer : KSerializer<SAR> {
    override val descriptor: SerialDescriptor = SARSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: SAR) =
        encoder.encodeSerializableValue(SARSurrogate.serializer(), SARSurrogate(value))

    override fun deserialize(decoder: Decoder): SAR =
        SAR(decoder.decodeSerializableValue(SARSurrogate.serializer()))

}

internal object TRANGESerializer : KSerializer<TRANGE> {
    override val descriptor: SerialDescriptor = TRANGESurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: TRANGE) =
        encoder.encodeSerializableValue(TRANGESurrogate.serializer(), TRANGESurrogate(value))

    override fun deserialize(decoder: Decoder): TRANGE =
        TRANGE(decoder.decodeSerializableValue(TRANGESurrogate.serializer()))

}

internal object ATRSerializer : KSerializer<ATR> {
    override val descriptor: SerialDescriptor = ATRSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ATR) =
        encoder.encodeSerializableValue(ATRSurrogate.serializer(), ATRSurrogate(value))

    override fun deserialize(decoder: Decoder): ATR =
        ATR(decoder.decodeSerializableValue(ATRSurrogate.serializer()))

}

internal object NATRSerializer : KSerializer<NATR> {
    override val descriptor: SerialDescriptor = NATRSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: NATR) =
        encoder.encodeSerializableValue(NATRSurrogate.serializer(), NATRSurrogate(value))

    override fun deserialize(decoder: Decoder): NATR =
        NATR(decoder.decodeSerializableValue(NATRSurrogate.serializer()))

}

internal object ADSerializer : KSerializer<AD> {
    override val descriptor: SerialDescriptor = ADSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: AD) =
        encoder.encodeSerializableValue(ADSurrogate.serializer(), ADSurrogate(value))

    override fun deserialize(decoder: Decoder): AD =
        AD(decoder.decodeSerializableValue(ADSurrogate.serializer()))

}

internal object ADOSCSerializer : KSerializer<ADOSC> {
    override val descriptor: SerialDescriptor = ADOSCSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ADOSC) =
        encoder.encodeSerializableValue(ADOSCSurrogate.serializer(), ADOSCSurrogate(value))

    override fun deserialize(decoder: Decoder): ADOSC =
        ADOSC(decoder.decodeSerializableValue(ADOSCSurrogate.serializer()))

}

internal object OBVSerializer : KSerializer<OBV> {
    override val descriptor: SerialDescriptor = OBVSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: OBV) =
        encoder.encodeSerializableValue(OBVSurrogate.serializer(), OBVSurrogate(value))

    override fun deserialize(decoder: Decoder): OBV =
        OBV(decoder.decodeSerializableValue(OBVSurrogate.serializer()))

}

internal object HTTRENDLINESerializer : KSerializer<HTTRENDLINE> {
    override val descriptor: SerialDescriptor = HTTRENDLINESurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: HTTRENDLINE) =
        encoder.encodeSerializableValue(HTTRENDLINESurrogate.serializer(), HTTRENDLINESurrogate(value))

    override fun deserialize(decoder: Decoder): HTTRENDLINE =
        HTTRENDLINE(decoder.decodeSerializableValue(HTTRENDLINESurrogate.serializer()))

}

internal object HTSINESerializer : KSerializer<HTSINE> {
    override val descriptor: SerialDescriptor = HTSINESurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: HTSINE) =
        encoder.encodeSerializableValue(HTSINESurrogate.serializer(), HTSINESurrogate(value))

    override fun deserialize(decoder: Decoder): HTSINE =
        HTSINE(decoder.decodeSerializableValue(HTSINESurrogate.serializer()))

}

internal object HTTRENDMODESerializer : KSerializer<HTTRENDMODE> {
    override val descriptor: SerialDescriptor = HTTRENDMODESurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: HTTRENDMODE) =
        encoder.encodeSerializableValue(HTTRENDMODESurrogate.serializer(), HTTRENDMODESurrogate(value))

    override fun deserialize(decoder: Decoder): HTTRENDMODE =
        HTTRENDMODE(decoder.decodeSerializableValue(HTTRENDMODESurrogate.serializer()))

}

internal object HTDCPERIODSerializer : KSerializer<HTDCPERIOD> {
    override val descriptor: SerialDescriptor = HTDCPERIODSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: HTDCPERIOD) =
        encoder.encodeSerializableValue(HTDCPERIODSurrogate.serializer(), HTDCPERIODSurrogate(value))

    override fun deserialize(decoder: Decoder): HTDCPERIOD =
        HTDCPERIOD(decoder.decodeSerializableValue(HTDCPERIODSurrogate.serializer()))

}

internal object HTDCPHASESerializer : KSerializer<HTDCPHASE> {
    override val descriptor: SerialDescriptor = HTDCPHASESurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: HTDCPHASE) =
        encoder.encodeSerializableValue(HTDCPHASESurrogate.serializer(), HTDCPHASESurrogate(value))

    override fun deserialize(decoder: Decoder): HTDCPHASE =
        HTDCPHASE(decoder.decodeSerializableValue(HTDCPHASESurrogate.serializer()))

}

internal object HTPHASORSerializer : KSerializer<HTPHASOR> {
    override val descriptor: SerialDescriptor = HTPHASORSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: HTPHASOR) =
        encoder.encodeSerializableValue(HTPHASORSurrogate.serializer(), HTPHASORSurrogate(value))

    override fun deserialize(decoder: Decoder): HTPHASOR =
        HTPHASOR(decoder.decodeSerializableValue(HTPHASORSurrogate.serializer()))

}
