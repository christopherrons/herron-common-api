package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.pricing.PriceModelResult;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.BLACK_SCHOLES_PRICE_MODEL_PARAMETERS;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBlackScholesPriceModelParameters.Builder.class)
public interface BondDiscountPriceModelResult extends PriceModelResult {

    Price accruedInterest();

    Price cleanPrice();

    @Value.Derived
    default Price dirtyPrice() {
        return cleanPrice().add(accruedInterest());
    }

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return BLACK_SCHOLES_PRICE_MODEL_PARAMETERS;
    }
}
