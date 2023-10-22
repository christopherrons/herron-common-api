package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.Quote;
import com.herron.exchange.common.api.common.enums.OrderSideEnum;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.PRICE_QUOTE;

@Value.Immutable
@JsonDeserialize(builder = ImmutablePriceQuote.Builder.class)
public interface PriceQuote extends Quote {
    Price price();

    OrderSideEnum side();


    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return PRICE_QUOTE;
    }
}