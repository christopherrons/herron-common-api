package com.herron.exchange.common.api.common.enums;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MessageTypesEnumTest {

    @Test
    void test_no_duplicate_ids() {
        Set<String> foundMessageTypes = new HashSet<>();
        for (var messageType : MessageTypesEnum.values()) {
            assertFalse(foundMessageTypes.contains(messageType.getMessageTypeId()));
            foundMessageTypes.add(messageType.getMessageTypeId());
        }
    }
}