package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.PriceModelParameters;
import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.DayCountConvetionEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.PriceModels;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.BOND_DISCOUNT_PRICE_MODEL_PARAMETERS;
import static com.herron.exchange.common.api.common.enums.PriceModels.BOND_DISCOUNT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBondDiscountPriceModelParameters.Builder.class)
public interface BondDiscountPriceModelParameters extends PriceModelParameters {

    CompoundingMethodEnum compoundingMethod();

    DayCountConvetionEnum dayCountConvention();

    @Nullable
    Double constantYield();

    @Nullable
    String yieldCurveId();

    boolean calculateWithCurve();

    @Value.Check
    default void checkValues() {
        if (calculateWithCurve() && StringUtils.isEmpty(yieldCurveId())) {
            throw new IllegalArgumentException("No curve id added even-though curve calculation is expected.");
        }

        if (!calculateWithCurve() && constantYield() == null) {
            throw new IllegalArgumentException("No yield added even-though constant yield is expected.");
        }
    }

    @Value.Derived
    default PriceModels priceModel() {
        return BOND_DISCOUNT;
    }

    @Value.Derived
    default MessageTypesEnum messageType() {
        return BOND_DISCOUNT_PRICE_MODEL_PARAMETERS;
    }
}
