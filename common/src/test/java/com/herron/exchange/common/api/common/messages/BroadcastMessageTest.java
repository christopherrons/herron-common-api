package com.herron.exchange.common.api.common.messages;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.enums.KafkaTopicEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableMarket;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableProduct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BroadcastMessageTest {

    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var market = ImmutableMarket.builder()
                .marketId("market")
                .businessCalendar(BusinessCalendar.defaultWeekendCalendar())
                .build();

        var product = ImmutableProduct.builder()
                .productId("product")
                .currency("eur")
                .market(market)
                .build();

        var object = ImmutableBroadcastMessage.builder()
                .sequenceNumber(1)
                .message(product)
                .eventType(EventType.SYSTEM)
                .timeOfEventMs(1)
                .partitionKey(new PartitionKey(KafkaTopicEnum.AUDIT_TRAIL, 1))
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }

}