package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
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

    default boolean hasUpdated(TopOfBook topOfBook) {
        var now = Timestamp.now();
        var tob1 = ImmutableTopOfBook.builder().from(this).timeOfEvent(now).build();
        var tob2 = ImmutableTopOfBook.builder().from(topOfBook).timeOfEvent(now).build();
        return !tob1.equals(tob2);
    }

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return TOP_OF_BOOK;
    }
}