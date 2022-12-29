package com.herron.exchange.common.api.common.model;

public record User(String fullName) {
    public String getUserId() {
        return fullName;
    }
}