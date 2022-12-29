package com.herron.exchange.common.api.common.model;

public record Participant(Member member, User user) {
    public String getParticipantId() {
        return member.getMemberId() + "-" + user.getUserId();
    }
}