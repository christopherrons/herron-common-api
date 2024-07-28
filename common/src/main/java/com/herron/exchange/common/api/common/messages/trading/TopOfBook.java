package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.TOP_OF_BOOK;

@Value.Immutable
@JsonDeserialize(builder = ImmutableTopOfBook.Builder.class)
public interface TopOfBook extends OrderbookEvent {

    @Nullable
    PriceQuote bidQuote();

    @Nullable
    PriceQuote askQuote();

    @Nullable
    PriceQuote lastQuote();

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return TOP_OF_BOOK;
    }
}