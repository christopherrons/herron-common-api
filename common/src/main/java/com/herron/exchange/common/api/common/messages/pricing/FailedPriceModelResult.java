package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.pricing.PriceModelResult;
import com.herron.exchange.common.api.common.enums.Status;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.EventType.SYSTEM;
import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.FAILED_PRICE_MODEL_RESULT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableFailedPriceModelResult.Builder.class)
public interface FailedPriceModelResult extends PriceModelResult {

    static FailedPriceModelResult createFailedResult(String reason) {
        return ImmutableFailedPriceModelResult.builder()
                .failReason(reason)
                .eventType(SYSTEM)
                .price(Price.ZERO)
                .timeOfEvent(Timestamp.now())
                .build();
    }

    String failReason();

    @Value.Derived
    default Status status() {
        return Status.ERROR;
    }

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return FAILED_PRICE_MODEL_RESULT;
    }
}
