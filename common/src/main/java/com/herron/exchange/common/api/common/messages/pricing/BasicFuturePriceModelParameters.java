package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.PriceModelParameters;
import com.herron.exchange.common.api.common.enums.PriceModels;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.PriceModels.BASIC_FUTURE_MODEL;
import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.BASIC_FUTURE_PRICE_MODEL_PARAMETERS;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBasicFuturePriceModelParameters.Builder.class)
public interface BasicFuturePriceModelParameters extends PriceModelParameters {

    @Value.Derived
    default PriceModels priceModel() {
        return BASIC_FUTURE_MODEL;
    }

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return BASIC_FUTURE_PRICE_MODEL_PARAMETERS;
    }
}
