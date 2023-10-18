package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultTradeExecutionTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var trade = ImmutableDefaultTrade.builder()
                .volume(Volume.create(1))
                .instrumentId("instrument")
                .tradeId("tradeid")
                .orderbookId("orderbookid")
                .isBidSideAggressor(true)
                .timeOfEventMs(1)
                .price(Price.create(1))
                .askParticipant(new Participant("member", "user"))
                .bidParticipant(new Participant("member", "user"))
                .askOrderId("askorderid")
                .bidOrderId("bidorderid")

                .build();
        var object = ImmutableDefaultTradeExecution.builder()
                .timeOfEventMs(1)
                .addAllMessages(List.of(trade))
                .orderbookId("orderbookid")
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}