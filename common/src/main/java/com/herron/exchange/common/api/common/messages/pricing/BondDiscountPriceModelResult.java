package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.pricing.PriceModelResult;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.BOND_DISCOUNT_PRICE_MODEL_RESULT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBondDiscountPriceModelResult.Builder.class)
public interface BondDiscountPriceModelResult extends PriceModelResult {

    double accruedInterest();

    Price cleanPrice();

    @Value.Derived
    default Price dirtyPrice() {
        return price();
    }

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return BOND_DISCOUNT_PRICE_MODEL_RESULT;
    }
}
