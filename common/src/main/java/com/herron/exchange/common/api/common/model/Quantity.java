package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.enums.UnitEnum;

//FIXME: Handle as long instead
public record Quantity(double quantity, UnitEnum unit) {

    public static Quantity create(double quantity) {
        return new Quantity(quantity, UnitEnum.UNITLESS);
    }

    public boolean gt(Quantity otherQuantity) {
        validate(otherQuantity);
        return this.quantity > otherQuantity.quantity();
    }

    public boolean lt(Quantity otherQuantity) {
        validate(otherQuantity);
        return this.quantity < otherQuantity.quantity();
    }

    public boolean ge(Quantity otherQuantity) {
        validate(otherQuantity);
        return this.quantity >= otherQuantity.quantity();
    }

    public boolean le(Quantity otherQuantity) {
        validate(otherQuantity);
        return this.quantity <= otherQuantity.quantity();
    }

    public Quantity multiply(Quantity otherQuantity) {
        validate(otherQuantity);
        return new Quantity(quantity * otherQuantity.quantity, unit);
    }

    public Quantity divide(Quantity otherQuantity) {
        validate(otherQuantity);
        return new Quantity(quantity * (1 / otherQuantity.quantity), unit);
    }

    public Quantity add(Quantity otherQuantity) {
        validate(otherQuantity);
        return new Quantity(quantity + otherQuantity.quantity, unit);
    }

    public Quantity subtract(Quantity otherQuantity) {
        validate(otherQuantity);
        return new Quantity(quantity - otherQuantity.quantity, unit);
    }

    private void validate(Quantity otherQuantity) {
        if (unit != otherQuantity.unit) {
            throw new IllegalArgumentException(String.format("Units %s and %s are incompatible.", unit, otherQuantity.unit));
        }
    }
}
