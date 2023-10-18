package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.*;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.DefaultBusinessCalendar;
import com.herron.exchange.common.api.common.messages.trading.DefaultTradingCalendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.herron.exchange.common.api.common.enums.AuctionAlgorithmEnum.*;
import static com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum.*;
import static com.herron.exchange.common.api.common.enums.OptionExerciseTyleEnum.*;
import static com.herron.exchange.common.api.common.enums.OptionExerciseTyleEnum.AMERICAN;
import static com.herron.exchange.common.api.common.enums.OptionTypeEnum.*;
import static com.herron.exchange.common.api.common.enums.SettlementTypeEnum.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultOrderbookDataTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var instrument = ImmutableDefaultOptionInstrument.builder()
                .instrumentId("instrumendId")
                .underlyingInstrumentId("underlying")
                .settlementType(PHYSICAL)
                .firstTradingDate(LocalDate.MIN)
                .lastTradingDate(LocalDate.MAX)
                .maturityDate(LocalDate.now())
                .strikePrice(100)
                .optionType(CALL)
                .optionExerciseStyle(AMERICAN)
                .product(ImmutableDefaultProduct.builder().currency("eur").productId("product").market(ImmutableDefaultMarket.builder().marketId("market").businessCalendar(DefaultBusinessCalendar.defaultWeekendCalendar()).build()).build())
                .build();

        var object = ImmutableDefaultOrderbookData.builder()
                .orderbookId("instrumendId")
                .instrument(instrument)
                .tradingCalendar(DefaultTradingCalendar.twentyFourSevenTradingCalendar())
                .matchingAlgorithm(PRO_RATA)
                .auctionAlgorithm(DUTCH)
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}