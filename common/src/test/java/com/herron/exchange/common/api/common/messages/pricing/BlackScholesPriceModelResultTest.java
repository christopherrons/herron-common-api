package com.herron.exchange.common.api.common.messages.pricing;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import org.junit.jupiter.api.Test;

import static com.herron.exchange.common.api.common.enums.EventType.SYSTEM;
import static com.herron.exchange.common.api.common.enums.Status.OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BlackScholesPriceModelResultTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableBlackScholesPriceModelResult.builder()
                .price(Price.create(100))
                .sensitivity(ImmutableOptionGreeks.builder()
                        .delta(PureNumber.create(1))
                        .gamma(PureNumber.create(1))
                        .rho(PureNumber.create(1))
                        .vega(PureNumber.create(1))
                        .theta(PureNumber.create(1))
                        .build())
                .eventType(SYSTEM)
                .timeOfEvent(Timestamp.now())
                .marketTime(Timestamp.now())
                .status(OK)
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}