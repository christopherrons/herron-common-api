package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.DefaultBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.*;
import com.herron.exchange.common.api.common.messages.marketdata.DefaultMarketDataPrice;
import com.herron.exchange.common.api.common.messages.marketdata.DefaultMarketDataPriceStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.DefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.refdata.*;
import com.herron.exchange.common.api.common.messages.trading.*;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MessageTypesEnum {
    INVALID_MESSAGE_TYPE(null, null),
    MONETARY_AMOUNT("MA", MonetaryAmount.class),
    VOLUME("V", Volume.class),
    QUANTITY("Q", Quantity.class),
    PRICE("P", Price.class),
    DEFAULT_MARKET_DATA_PRICE("DMDP", DefaultMarketDataPrice.class),
    DEFAULT_MARKET_DATA_PRICE_STATIC_KEY("DMDPSK", DefaultMarketDataPriceStaticKey.class),
    DEFAULT_TIME_COMPONENT_KEY("DTCK", DefaultTimeComponentKey.class),
    DEFAULT_BROADCAST_MESSAGE("DEBM", DefaultBroadcastMessage.class),
    DEFAULT_TRADING_CALENDAR("DFTC", DefaultTradingCalendar.class),
    DEFAULT_BUSINESS_CALENDAR("DFBC", DefaultBusinessCalendar.class),
    DEFAULT_LIMIT_ORDER("DFLO", DefaultLimitOrder.class),
    DEFAULT_MARKET_ORDER("DFMO", DefaultMarketOrder.class),
    DEFAULT_TRADE("DFTR", DefaultTrade.class),
    DEFAULT_ORDERBOOK_DATA("DFOB", DefaultOrderbookData.class),
    DEFAULT_EQUITY_INSTRUMENT("DFSI", DefaultEquityInstrument.class),
    DEFAULT_BOND_INSTRUMENT("DFBI", DefaultBondInstrument.class),
    DEFAULT_OPTION_INSTRUMENT("DFOI", DefaultOptionInstrument.class),
    DEFAULT_FUTURE_INSTRUMENT("DFFI", DefaultFutureInstrument.class),
    DEFAULT_TRADE_EXECUTION("DFEX", DefaultTradeExecution.class),
    DEFAULT_DATA_STREAM_STATE("DFDL", DefaultDataStreamState.class),
    DEFAULT_MARKET("DFMA", DefaultMarket.class),
    DEFAULT_PRODUCT("DFPR", DefaultProduct.class),
    DEFAULT_ORDERBOOK_STATE_CHANGE("DFSC", DefaultStateChange.class);

    private static final Map<String, MessageTypesEnum> VALUES_BY_IDENTIFIER = stream(MessageTypesEnum.values())
            .collect(toMap(MessageTypesEnum::getMessageTypeId, identity()));
    private static final DefaultMessageFactory MESSAGE_FACTORY = new DefaultMessageFactory();

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDeserialized;

    MessageTypesEnum(String messageTypeId, Class<? extends Message> classToBeDeserialized) {
        this.messageTypeId = messageTypeId;
        this.classToBeDeserialized = classToBeDeserialized;
    }

    public static MessageTypesEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE);
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }

}
