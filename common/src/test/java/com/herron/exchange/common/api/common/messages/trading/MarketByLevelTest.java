package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketByLevelTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var levelData = ImmutableLevelData.builder()
                .bidPrice(Price.create(1))
                .askPrice(Price.create(1))
                .bidVolume(Volume.create(1))
                .askVolume(Volume.create(1))
                .nrOfBidOrders(1L)
                .nrOfAskOrders(1L)
                .level(1)
                .build();
        var object = ImmutableMarketByLevel.builder()
                .orderbookId("orderbookid")
                .eventType(EventType.SYSTEM)
                .timeOfEvent(Timestamp.now())
                .addLevelData(levelData)
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}