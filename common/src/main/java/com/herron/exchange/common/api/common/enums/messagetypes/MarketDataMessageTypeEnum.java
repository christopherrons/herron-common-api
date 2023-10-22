package com.herron.exchange.common.api.common.enums.messagetypes;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.MessageType;
import com.herron.exchange.common.api.common.messages.marketdata.DefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.entries.DefaultMarketDataPrice;
import com.herron.exchange.common.api.common.messages.marketdata.entries.DefaultMarketDataYieldCurve;
import com.herron.exchange.common.api.common.messages.marketdata.requests.DefaultMarketDataYieldCurveRequest;
import com.herron.exchange.common.api.common.messages.marketdata.response.DefaultMarketDataYieldCurveResponse;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.DefaultMarketDataPriceStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.DefaultMarketDataYieldCurveStaticKey;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MarketDataMessageTypeEnum implements MessageType {
    DEFAULT_MARKET_DATA_PRICE("DMDP", DefaultMarketDataPrice.class),
    DEFAULT_MARKET_DATA_PRICE_STATIC_KEY("DMDPSK", DefaultMarketDataPriceStaticKey.class),
    DEFAULT_MARKET_DATA_YIELD_CURVE("DMDYC", DefaultMarketDataYieldCurve.class),
    DEFAULT_MARKET_DATA_YIELD_CURVE_STATIC_KEY("DMDYCSK", DefaultMarketDataYieldCurveStaticKey.class),
    DEFAULT_MARKET_DATA_YIELD_CURVE_REQUEST("DMDYCREQ", DefaultMarketDataYieldCurveRequest.class),
    DEFAULT_MARKET_DATA_YIELD_CURVE_RESPONSE("DMDYCRES", DefaultMarketDataYieldCurveResponse.class),
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

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }

    public static Map<String, Class<? extends Message>> getIdToClassImplementation() {
        return stream(MarketDataMessageTypeEnum.values()).collect(Collectors.toMap(MarketDataMessageTypeEnum::getMessageTypeId, MarketDataMessageTypeEnum::getClassToBeDeserialized));
    }
}
