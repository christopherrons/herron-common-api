package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.enums.TradeType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import static com.herron.exchange.common.api.common.enums.TradeType.AUTOMATCH;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TradeTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableTrade.builder()
                .volume(Volume.create(1))
                .instrumentId("instrument")
                .tradeId("tradeid")
                .orderbookId("orderbookid")
                .isBidSideAggressor(true)
                .timeOfEventMs(1)
                .eventType(EventType.SYSTEM)
                .price(Price.create(1))
                .askParticipant(new Participant("member", "user"))
                .bidParticipant(new Participant("member", "user"))
                .askOrderId("askorderid")
                .bidOrderId("bidorderid")
                .tradeType(AUTOMATCH)
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}