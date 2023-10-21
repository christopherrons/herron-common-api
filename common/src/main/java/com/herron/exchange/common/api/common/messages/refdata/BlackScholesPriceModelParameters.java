package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.PriceModelParameters;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.PriceModels;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.BLACK_SCHOLES_PRICE_MODEL_PARAMETERS;
import static com.herron.exchange.common.api.common.enums.PriceModels.BLACK_SCHOLES;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBlackScholesPriceModelParameters.Builder.class)
public interface BlackScholesPriceModelParameters extends PriceModelParameters {

    @Value.Derived
    default PriceModels priceModel() {
        return BLACK_SCHOLES;
    }

    @Value.Derived
    default MessageTypesEnum messageType() {
        return BLACK_SCHOLES_PRICE_MODEL_PARAMETERS;
    }
}
