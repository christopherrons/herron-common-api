package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.PriceModelParameters;
import com.herron.exchange.common.api.common.enums.PriceModels;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.PriceModels.INTANGIBLE;
import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.INTANGIBLE_PRICE_MODEL_PARAMETERS;

@Value.Immutable
@JsonDeserialize(builder = ImmutableIntangiblePriceModelParameters.Builder.class)
public interface IntangiblePriceModelParameters extends PriceModelParameters {


    @Value.Derived
    default PriceModels priceModel() {
        return INTANGIBLE;
    }

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return INTANGIBLE_PRICE_MODEL_PARAMETERS;
    }
}
