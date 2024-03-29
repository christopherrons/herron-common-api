package com.herron.exchange.common.api.common.messages.common;

public record Participant(Member member, User user) {

    public Participant(String member, String user) {
        this(new Member(member), new User(user));
    }

    public String getParticipantId() {
        return member.memberId() + "-" + user.fullName();
    }
}