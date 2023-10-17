package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.UnitEnum;

import java.util.Objects;

public class Quantity extends Amount<Quantity> {

    private final UnitEnum unit;

    public Quantity(@JsonProperty("value") double quantity,
                    @JsonProperty("unit") UnitEnum unit) {
        super(quantity);
        this.unit = unit;
    }

    public static Quantity create(double quantity, UnitEnum unit) {
        return new Quantity(quantity, unit);
    }

    public static Quantity create(double quantity) {
        return new Quantity(quantity, UnitEnum.UNITLESS);
    }

    @Override
    protected Quantity newInstance(double quantity) {
        return Quantity.create(quantity, unit);
    }

    protected void validate(Quantity otherQuantity) {
        if (unit != otherQuantity.unit) {
            throw new IllegalArgumentException(String.format("Units %s and %s are incompatible.", unit, otherQuantity.unit));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity quantity)) return false;
        if (!super.equals(o)) return false;
        return unit == quantity.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unit);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.QUANTITY;
    }
}
