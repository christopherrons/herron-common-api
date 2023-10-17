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

    protected abstract T emptyAmount();

    public boolean gt(T otherAmount) {
        validate(otherAmount);
        return gt(otherAmount.value);
    }

    public boolean gt(double otherValue) {
        return this.value > otherValue;
    }

    public boolean lt(T otherAmount) {
        validate(otherAmount);
        return lt(otherAmount.value);
    }

    public boolean lt(double otherValue) {
        return this.value < otherValue;
    }

    public boolean geq(T otherAmount) {
        validate(otherAmount);
        return geq(otherAmount.value);
    }

    public boolean geq(double otherValue) {
        return this.value >= otherValue;
    }

    public boolean leq(T otherAmount) {
        validate(otherAmount);
        return leq(otherAmount.value);
    }

    public boolean leq(double otherValue) {
        return this.value <= otherValue;
    }

    public T multiply(T otherAmount) {
        validate(otherAmount);
        return multiply(otherAmount.value);
    }

    public T multiply(double otherValue) {
        return newInstance(value * otherValue);
    }

    public T divide(T otherAmount) {
        validate(otherAmount);
        return divide(otherAmount.value);
    }

    public T divide(double otherValue) {
        return newInstance(value * (1 / otherValue));
    }

    public T add(T otherAmount) {
        validate(otherAmount);
        return add(otherAmount.value);
    }

    public T add(double otherValue) {
        return newInstance(value + otherValue);
    }

    public T subtract(T otherAmount) {
        validate(otherAmount);
        return subtract(otherAmount.value);
    }

    public T subtract(double otherValue) {
        return newInstance(value - otherValue);
    }

    public T max(T otherAmount) {
        return this.gt(otherAmount.value) ? (T) this : otherAmount;
    }

    public T min(T otherAmount) {
        return this.lt(otherAmount.value) ? (T) this : otherAmount;
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
