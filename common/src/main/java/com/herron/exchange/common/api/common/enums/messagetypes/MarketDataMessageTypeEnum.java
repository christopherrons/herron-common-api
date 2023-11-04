package com.herron.exchange.common.api.common.enums.messagetypes;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.MessageType;
import com.herron.exchange.common.api.common.messages.marketdata.DefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.entries.MarketDataForwardPriceCurve;
import com.herron.exchange.common.api.common.messages.marketdata.entries.MarketDataImpliedVolatilitySurface;
import com.herron.exchange.common.api.common.messages.marketdata.entries.MarketDataPrice;
import com.herron.exchange.common.api.common.messages.marketdata.entries.MarketDataYieldCurve;
import com.herron.exchange.common.api.common.messages.marketdata.requests.MarketDataForwardPriceCurveRequest;
import com.herron.exchange.common.api.common.messages.marketdata.requests.MarketDataImpliedVolatilitySurfaceRequest;
import com.herron.exchange.common.api.common.messages.marketdata.requests.MarketDataPriceRequest;
import com.herron.exchange.common.api.common.messages.marketdata.requests.MarketDataYieldCurveRequest;
import com.herron.exchange.common.api.common.messages.marketdata.response.MarketDataForwardPriceCurveResponse;
import com.herron.exchange.common.api.common.messages.marketdata.response.MarketDataImpliedVolatilitySurfaceResponse;
import com.herron.exchange.common.api.common.messages.marketdata.response.MarketDataPriceResponse;
import com.herron.exchange.common.api.common.messages.marketdata.response.MarketDataYieldCurveResponse;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataForwardPriceCurveStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataImpliedVolatilitySurfaceStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataPriceStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataYieldCurveStaticKey;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MarketDataMessageTypeEnum implements MessageType {
    MARKET_DATA_PRICE("MDP", MarketDataPrice.class),
    MARKET_DATA_PRICE_STATIC_KEY("MDPSK", MarketDataPriceStaticKey.class),
    MARKET_DATA_PRICE_REQUEST("MDPRREQ", MarketDataPriceRequest.class),
    MARKET_DATA_PRICE_RESPONSE("MDPRRES", MarketDataPriceResponse.class),

    MARKET_DATA_YIELD_CURVE("MDYC", MarketDataYieldCurve.class),
    MARKET_DATA_YIELD_CURVE_STATIC_KEY("MDYCSK", MarketDataYieldCurveStaticKey.class),
    MARKET_DATA_YIELD_CURVE_REQUEST("MDYCREQ", MarketDataYieldCurveRequest.class),
    MARKET_DATA_YIELD_CURVE_RESPONSE("MDYCRES", MarketDataYieldCurveResponse.class),

    MARKET_DATA_FORWARD_PRICE_CURVE("MDFPC", MarketDataForwardPriceCurve.class),
    MARKET_DATA_FORWARD_PRICE_CURVE_STATIC_KEY("MDFPCK", MarketDataForwardPriceCurveStaticKey.class),
    MARKET_DATA_FORWARD_PRICE_CURVE_REQUEST("MDFPCYEQ", MarketDataForwardPriceCurveRequest.class),
    MARKET_DATA_FORWARD_PRICE_CURVE_RESPONSE("MDFPCRES", MarketDataForwardPriceCurveResponse.class),

    MARKET_DATA_IMPLIED_VOLATILITY_SURFACE("MDIVS", MarketDataImpliedVolatilitySurface.class),
    MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_KEY("MDIVSK", MarketDataImpliedVolatilitySurfaceStaticKey.class),
    MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_REQUEST("MDIVSREQ", MarketDataImpliedVolatilitySurfaceRequest.class),
    MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_RESPONSE("MDIVSRES", MarketDataImpliedVolatilitySurfaceResponse.class),

    DEFAULT_TIME_COMPONENT_KEY("DTCK", DefaultTimeComponentKey.class);

    private static final Map<String, MarketDataMessageTypeEnum> VALUES_BY_IDENTIFIER = stream(MarketDataMessageTypeEnum.values())
            .collect(toMap(MarketDataMessageTypeEnum::getMessageTypeId, identity()));

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDeserialized;

    MarketDataMessageTypeEnum(String messageTypeId, Class<? extends Message> classToBeDeserialized) {
        this.messageTypeId = messageTypeId;
        this.classToBeDeserialized = classToBeDeserialized;
    }

    public static MarketDataMessageTypeEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, null);
    }

    public static Map<String, Class<? extends Message>> getIdToClassImplementation() {
        return stream(MarketDataMessageTypeEnum.values()).collect(Collectors.toMap(MarketDataMessageTypeEnum::getMessageTypeId, MarketDataMessageTypeEnum::getClassToBeDeserialized));
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }
}
