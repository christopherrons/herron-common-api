package com.herron.exchange.common.api.common.enums;

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
    void tmp_test() {
        ImmutableDefaultMarket market = ImmutableDefaultMarket.builder()
                .marketId("bitstamp")
                .businessCalendar(DefaultBusinessCalendar.defaultWeekendCalendar())
                .build();
        var r = MessageTypesEnum.DEFAULT_MARKET.deserializeMessage(market.serialize());
        DefaultProduct product = ImmutableDefaultProduct.builder()
                .market(market)
                .productId(String.format("%s_equity", market.marketId()))
                .currency("usd")
                .build();
        var r2 =  MessageTypesEnum.DEFAULT_PRODUCT.deserializeMessage(product.serialize());
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
        var r3 =  MessageTypesEnum.DEFAULT_EQUITY_INSTRUMENT.deserializeMessage(ins.serialize());
        MessageTypesEnum.DEFAULT_BROADCAST_MESSAGE.deserializeMessage(b.serialize());

    }
}