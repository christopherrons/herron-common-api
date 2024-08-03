package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.herron.exchange.common.api.common.enums.TradeType.AUTOMATCH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TradeExecutionTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var trade = ImmutableTrade.builder()
                .volume(Volume.create(1))
                .instrumentId("instrument")
                .tradeId("tradeid")
                .orderbookId("orderbookid")
                .isBidSideAggressor(true)
                .timeOfEvent(Timestamp.from(1))
                .eventType(EventType.SYSTEM)
                .price(Price.create(1))
                .askParticipant(new Participant("member", "user"))
                .bidParticipant(new Participant("member", "user"))
                .askOrderId("askorderid")
                .tradeType(AUTOMATCH)
                .bidOrderId("bidorderid")

                .build();
        var object = ImmutableTradeExecution.builder()
                .timeOfEvent(Timestamp.from(1))
                .addAllMessages(List.of(trade))
                .orderbookId("orderbookid")
                .eventType(EventType.SYSTEM)
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}