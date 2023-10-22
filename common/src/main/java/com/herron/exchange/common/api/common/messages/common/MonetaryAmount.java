package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;

import java.math.BigDecimal;
import java.util.Objects;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.MONETARY_AMOUNT;

public class MonetaryAmount extends Amount<MonetaryAmount> {
    public static final MonetaryAmount ZERO_AMOUNT = new MonetaryAmount(0, "N/A");
    private final String currency;

    private MonetaryAmount(double value, String currency) {
        super(value);
        this.currency = currency;
    }

    private MonetaryAmount(@JsonProperty("value") BigDecimal value, @JsonProperty("currency") String currency) {
        super(value);
        this.currency = currency;
    }

    public static MonetaryAmount create(double value, String currency) {
        return new MonetaryAmount(value, currency);
    }

    @Override
    protected MonetaryAmount newInstance(double value) {
        return new MonetaryAmount(value, currency);
    }

    @Override
    protected MonetaryAmount newInstance(BigDecimal value) {
        return new MonetaryAmount(value, currency);
    }

    protected void validate(MonetaryAmount otherMonetaryAmount) {
        if (!currency.equals(otherMonetaryAmount.currency)) {
            throw new IllegalArgumentException(String.format("Currency %s and %s are incompatible.", currency, otherMonetaryAmount.currency));
        }
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    protected MonetaryAmount emptyAmount() {
        return ZERO_AMOUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonetaryAmount that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currency);
    }

    @Override
    public CommonMessageTypesEnum messageType() {
        return MONETARY_AMOUNT;
    }

    @Override
    public String toString() {
        return "MonetaryAmount{" +
                "value=" + value +
                "} " + super.toString();
    }
}
