package com.herron.exchange.common.api.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Participant(Member member, User user) {
    public String getParticipantId() {
        return member.memberId() + "-" + user.fullName();
    }
}