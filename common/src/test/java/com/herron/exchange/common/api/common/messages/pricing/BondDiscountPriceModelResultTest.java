package com.herron.exchange.common.api.common.messages.pricing;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.DayCountConventionEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.herron.exchange.common.api.common.enums.EventType.SYSTEM;
import static com.herron.exchange.common.api.common.enums.Status.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BondDiscountPriceModelResultTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableBondDiscountPriceModelResult.builder()
                .accruedInterest(PureNumber.create(1))
                .cleanPrice(Price.create(1))
                .dirtyPrice(Price.create(1 + 1))
                .dayCountConvention(DayCountConventionEnum.ACT365)
                .discountedPaymentResult(List.of(
                        ImmutableDiscountedPaymentResult.builder()
                                .start(Timestamp.now())
                                .end(Timestamp.now())
                                .discountFactor(1)
                                .yieldToMaturity(1)
                                .timeToMaturity(1)
                                .couponValuePercentage(1)
                                .build())
                )
                .eventType(SYSTEM)
                .timeOfEvent(Timestamp.now())
                .marketTime(Timestamp.now())
                .status(OK)
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}