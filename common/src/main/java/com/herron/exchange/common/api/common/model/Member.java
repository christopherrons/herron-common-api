package com.herron.exchange.common.api.common.model;

public record Member(String memberId) {
    public String getMemberId() {
        return memberId;
    }
}
