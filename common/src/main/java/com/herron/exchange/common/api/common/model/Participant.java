package com.herron.exchange.common.api.common.model;

import java.util.Objects;

public record Participant(Member member, User user) {
    public String getParticipantId() {
        return member.getMemberId() + "-" + user.getUserId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant that)) return false;
        return Objects.equals(member, that.member) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, user);
    }
}