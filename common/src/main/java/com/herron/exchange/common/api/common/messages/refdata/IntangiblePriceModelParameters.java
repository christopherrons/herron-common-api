package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.PriceModelParameters;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.PriceModels;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.INTANGIBLE_PRICE_MODEL_PARAMETERS;
import static com.herron.exchange.common.api.common.enums.PriceModels.INTANGIBLE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableIntangiblePriceModelParameters.Builder.class)
public interface IntangiblePriceModelParameters extends PriceModelParameters {


    @Value.Derived
    default PriceModels priceModel() {
        return INTANGIBLE;
    }

    @Value.Derived
    default MessageTypesEnum messageType() {
        return INTANGIBLE_PRICE_MODEL_PARAMETERS;
    }
}
