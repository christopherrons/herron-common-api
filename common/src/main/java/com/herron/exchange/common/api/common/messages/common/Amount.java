package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.api.Message;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

//FIXME: Handle as long instead
public abstract class Amount<T extends Amount<?>> implements Message {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected final BigDecimal value;
    @JsonIgnore
    protected final double realValue;

    protected Amount(double realValue) {
        this(realValue, BigDecimal.valueOf(realValue));
    }

    protected Amount(BigDecimal value) {
        this(Double.NaN, value);
    }

    protected Amount(double realValue, BigDecimal value) {
        this.realValue = realValue;
        this.value = value;
    }

    protected abstract T newInstance(double value);

    protected abstract T newInstance(BigDecimal value);

    protected abstract void validate(T value);

    protected abstract T emptyAmount();

    public boolean gt(T otherAmount) {
        validate(otherAmount);
        return this.value.compareTo(otherAmount.value) > 0;
    }

    public boolean gt(double otherValue) {
        return this.value.compareTo(BigDecimal.valueOf(otherValue)) > 0;
    }

    public boolean lt(T otherAmount) {
        validate(otherAmount);
        return this.value.compareTo(otherAmount.value) < 0;
    }

    public boolean lt(double otherValue) {
        return this.value.compareTo(BigDecimal.valueOf(otherValue)) < 0;
    }

    public boolean geq(T otherAmount) {
        validate(otherAmount);
        return value.compareTo(otherAmount.value) >= 0;
    }

    public boolean geq(double otherValue) {
        return value.compareTo(BigDecimal.valueOf(otherValue)) >= 0;
    }

    public boolean leq(T otherAmount) {
        validate(otherAmount);
        return value.compareTo(otherAmount.value) <= 0;
    }

    public boolean leq(double otherValue) {
        return value.compareTo(BigDecimal.valueOf(otherValue)) <= 0;
    }

    public T multiply(T otherAmount) {
        validate(otherAmount);
        return newInstance(value.multiply(otherAmount.value));
    }

    public T multiply(double otherValue) {
        return newInstance(value.multiply(BigDecimal.valueOf(otherValue)));
    }

    public T divide(T otherAmount) {
        validate(otherAmount);
        return newInstance(value.divide(otherAmount.value, RoundingMode.HALF_DOWN));
    }

    public T divide(double otherValue) {
        return newInstance(value.divide(BigDecimal.valueOf(otherValue), RoundingMode.HALF_DOWN));
    }

    public T add(T otherAmount) {
        validate(otherAmount);
        return newInstance(value.add(otherAmount.value));
    }

    public T add(double otherValue) {
        return newInstance(value.add(BigDecimal.valueOf(otherValue)));
    }

    public T subtract(T otherAmount) {
        validate(otherAmount);
        return newInstance(value.subtract(otherAmount.value));
    }

    public T subtract(double otherValue) {
        return newInstance(value.subtract(BigDecimal.valueOf(otherValue)));
    }

    public T max(T otherAmount) {
        return this.gt(otherAmount) ? (T) this : otherAmount;
    }

    public T min(T otherAmount) {
        return this.lt(otherAmount) ? (T) this : otherAmount;
    }

    public T scale(int decimals) {
        return newInstance(value.setScale(decimals, RoundingMode.HALF_EVEN));
    }

    public int signum() {
        return value.signum();
    }

    public double getRealValue() {
        return Double.isNaN(realValue) ? value.doubleValue() : realValue;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount<?> amount)) return false;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, realValue);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                '}';
    }
}
