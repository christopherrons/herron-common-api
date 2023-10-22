package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.UnitEnum;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;

import java.math.BigDecimal;
import java.util.Objects;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.QUANTITY;

public class Quantity extends Amount<Quantity> {
    public static final Quantity ZERO = new Quantity(0, UnitEnum.UNITLESS);
    private final UnitEnum unit;

    private Quantity(double quantity, UnitEnum unit) {
        super(quantity);
        this.unit = unit;
    }

    private Quantity(@JsonProperty("value") BigDecimal quantity,
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

    @Override
    protected Quantity newInstance(BigDecimal quantity) {
        return new Quantity(quantity, unit);
    }

    protected void validate(Quantity otherQuantity) {
        if (unit != otherQuantity.unit) {
            throw new IllegalArgumentException(String.format("Units %s and %s are incompatible.", unit, otherQuantity.unit));
        }
    }

    @Override
    protected Quantity emptyAmount() {
        return ZERO;
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
    public CommonMessageTypesEnum messageType() {
        return QUANTITY;
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "unit=" + unit +
                "} " + super.toString();
    }
}
