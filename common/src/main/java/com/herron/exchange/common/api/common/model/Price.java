package com.herron.exchange.common.api.common.model;

//FIXME: Handle as long instead
public record Price(double price) {

    public boolean gt(Price otherPrice) {
        return this.price > otherPrice.price();
    }

    public boolean lt(Price otherPrice) {
        return this.price < otherPrice.price();
    }

    public boolean ge(Price otherPrice) {
        return this.price >= otherPrice.price();
    }

    public boolean le(Price otherPrice) {
        return this.price <= otherPrice.price();
    }

    public static Price create(double price) {
        return new Price(price);
    }

    public Price multiply(Price otherPrice) {
        return new Price(price * otherPrice.price);
    }

    public Price divide(Price otherPrice) {
        return new Price(price * (1 / otherPrice.price));
    }

    public Price add(Price otherPrice) {
        return new Price(price + otherPrice.price);
    }

    public Price subtract(Price otherPrice) {
        return new Price(price - otherPrice.price);
    }
}
