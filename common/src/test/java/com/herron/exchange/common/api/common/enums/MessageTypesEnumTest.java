package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.enums.messagetypes.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MessageTypesEnumTest {

    @Test
    void test_no_duplicate_ids() {
        Set<String> foundMessageTypes = new HashSet<>();
        for (var messageType : CommonMessageTypesEnum.values()) {
            assertFalse(foundMessageTypes.contains(messageType.getMessageTypeId()));
            foundMessageTypes.add(messageType.getMessageTypeId());
        }
        for (var messageType : MarketDataMessageTypeEnum.values()) {
            assertFalse(foundMessageTypes.contains(messageType.getMessageTypeId()));
            foundMessageTypes.add(messageType.getMessageTypeId());
        }
        for (var messageType : PriceModelMessageTypeEnum.values()) {
            assertFalse(foundMessageTypes.contains(messageType.getMessageTypeId()));
            foundMessageTypes.add(messageType.getMessageTypeId());
        }
        for (var messageType : ReferenceDataMessageTypeEnum.values()) {
            assertFalse(foundMessageTypes.contains(messageType.getMessageTypeId()));
            foundMessageTypes.add(messageType.getMessageTypeId());
        }
        for (var messageType : TradingMessageTypeEnum.values()) {
            assertFalse(foundMessageTypes.contains(messageType.getMessageTypeId()));
            foundMessageTypes.add(messageType.getMessageTypeId());
        }
    }
}