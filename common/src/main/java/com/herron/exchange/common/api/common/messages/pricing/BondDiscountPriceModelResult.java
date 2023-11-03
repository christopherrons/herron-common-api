package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.pricing.PriceModelResult;
import com.herron.exchange.common.api.common.enums.DayCountConventionEnum;
import com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import org.immutables.value.Value;

import java.util.List;

import static com.herron.exchange.common.api.common.enums.messagetypes.PricingMessageTypeEnum.BOND_DISCOUNT_PRICE_MODEL_RESULT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBondDiscountPriceModelResult.Builder.class)
public interface BondDiscountPriceModelResult extends PriceModelResult {

    DayCountConventionEnum dayCountConvention();

    PureNumber accruedInterest();

    Price cleanPrice();

    Price dirtyPrice();

    List<DiscountedPaymentResult> discountedPaymentResult();

    @Override
    @Value.Derived
    default Price price() {
        return dirtyPrice();
    }

    @Value.Derived
    default PricingMessageTypeEnum messageType() {
        return BOND_DISCOUNT_PRICE_MODEL_RESULT;
    }

    @Value.Immutable
    @JsonDeserialize(builder = ImmutableDiscountedPaymentResult.Builder.class)
    interface DiscountedPaymentResult {
        Timestamp start();

        Timestamp end();

        double timeToMaturity();

        double yieldToMaturity();

        double discountFactor();

        double couponValuePercentage();
    }
}
