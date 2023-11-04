package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.PriceModelParameters;
import com.herron.exchange.common.api.common.enums.PriceModels;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.PriceModels.BLACK_76;
import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.BLACK_76_PRICE_MODEL_PARAMETERS;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBlack76PriceModelParameters.Builder.class)
public interface Black76PriceModelParameters extends PriceModelParameters {

    String yieldCurveId();

    @Value.Default
    default PureNumber dividendYield() {
        return PureNumber.ZERO;
    }

    @Value.Derived
    default PriceModels priceModel() {
        return BLACK_76;
    }

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return BLACK_76_PRICE_MODEL_PARAMETERS;
    }
}
