package com.herron.exchange.common.api.common.messages.pricemodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.PriceModelParameters;
import com.herron.exchange.common.api.common.enums.PriceModels;
import com.herron.exchange.common.api.common.enums.messagetypes.PriceModelMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.PriceModels.BLACK_SCHOLES;
import static com.herron.exchange.common.api.common.enums.messagetypes.PriceModelMessageTypeEnum.BLACK_SCHOLES_PRICE_MODEL_PARAMETERS;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBlackScholesPriceModelParameters.Builder.class)
public interface BlackScholesPriceModelParameters extends PriceModelParameters {

    @Value.Derived
    default PriceModels priceModel() {
        return BLACK_SCHOLES;
    }

    @Value.Derived
    default PriceModelMessageTypeEnum messageType() {
        return BLACK_SCHOLES_PRICE_MODEL_PARAMETERS;
    }
}
