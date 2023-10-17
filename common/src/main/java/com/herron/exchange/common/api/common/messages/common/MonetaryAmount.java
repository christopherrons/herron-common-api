package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

import java.util.Objects;

public class MonetaryAmount extends Amount<MonetaryAmount> {
    public static MonetaryAmount ZERO_AMOUNT = new MonetaryAmount(0, "N/A");
    private final String currency;

    private MonetaryAmount(@JsonProperty("value") double value, @JsonProperty("currency") String currency) {
        super(value);
        this.currency = currency;
    }

    public static MonetaryAmount create(double value, String currency) {
        return new MonetaryAmount(value, currency);
    }

    @Override
    protected MonetaryAmount newInstance(double value) {
        return MonetaryAmount.create(value, currency);
    }

    protected void validate(MonetaryAmount otherMonetaryAmount) {
        if (!currency.equals(otherMonetaryAmount.currency)) {
            throw new IllegalArgumentException(String.format("Currency %s and %s are incompatible.", currency, otherMonetaryAmount.currency));
        }
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
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.MONETARY_AMOUNT;
    }
}
