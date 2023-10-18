package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.orders.PriceQuote;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_PRICE_QUOTE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultPriceQuote.Builder.class)
public interface DefaultPriceQuote extends PriceQuote {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_PRICE_QUOTE;
    }
}