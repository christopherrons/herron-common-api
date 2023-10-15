package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herron.exchange.common.api.common.messages.DefaultBroadcastMessage;
import com.herron.exchange.common.api.common.messages.ImmutableDefaultBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.DefaultBusinessCalendar;
import com.herron.exchange.common.api.common.messages.refdata.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

    @Test
    void tmp_test() throws JsonProcessingException {
        var mapper = new ObjectMapper();
        ImmutableDefaultMarket market = ImmutableDefaultMarket.builder()
                .marketId("bitstamp")
                .businessCalendar(DefaultBusinessCalendar.defaultWeekendCalendar())
                .build();
        var r = mapper.readValue(market.serialize(), DefaultMarket.class);
        DefaultProduct product = ImmutableDefaultProduct.builder()
                .market(market)
                .productId(String.format("%s_equity", market.marketId()))
                .currency("usd")
                .build();
        var r2 = mapper.readValue(product.serialize(), DefaultProduct.class);
        DefaultBroadcastMessage b = ImmutableDefaultBroadcastMessage.builder()
                .message(product)
                .sequenceNumber(1)
                .timeOfEventMs(1)
                .build();

        var ins = ImmutableDefaultEquityInstrument.builder()
                .instrumentId(String.format("%s_btcusd", product.productId()))
                .product(product)
                .firstTradingDate(LocalDate.MIN)
                .lastTradingDate(LocalDate.MAX)
                .build();
        var r3 = mapper.readValue(product.serialize(), DefaultEquityInstrument.class);
        MessageTypesEnum.DEFAULT_BROADCAST_MESSAGE.deserializeMessage(b.serialize());

    }
}