package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.pricing.PriceModelResult;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.BLACK_SCHOLES_PRICE_MODEL_RESULT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBlackScholesPriceModelResult.Builder.class)
public interface BlackScholesPriceModelResult extends PriceModelResult {

    OptionGreeks sensitivity();

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return BLACK_SCHOLES_PRICE_MODEL_RESULT;
    }

}
