package com.herron.exchange.common.api.common.messages.common;

import com.herron.exchange.common.api.common.api.Message;

import java.util.Objects;

//FIXME: Handle as long instead
public abstract class Amount<T extends Amount<?>> implements Message {

    protected final double value;

    protected Amount(double value) {
        this.value = value;
    }

    protected abstract T newInstance(double value);

    protected abstract void validate(T value);

    public boolean gt(T otherAmount) {
        validate(otherAmount);
        return this.value > otherAmount.value;
    }

    public boolean lt(T otherAmount) {
        validate(otherAmount);
        return this.value < otherAmount.value;
    }

    public boolean ge(T otherAmount) {
        validate(otherAmount);
        return this.value >= otherAmount.value;
    }

    public boolean le(T otherAmount) {
        validate(otherAmount);
        return this.value <= otherAmount.value;
    }

    public T multiply(T otherAmount) {
        validate(otherAmount);
        return newInstance(value * otherAmount.value);
    }

    public T divide(T otherAmount) {
        validate(otherAmount);
        return newInstance(value * (1 / otherAmount.value));
    }

    public T add(T otherAmount) {
        validate(otherAmount);
        return newInstance(value + otherAmount.value);
    }

    public T subtract(T otherAmount) {
        validate(otherAmount);
        return newInstance(value - otherAmount.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount<?> amount)) return false;
        return Double.compare(value, amount.value) == 0;
    }

    public double getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
