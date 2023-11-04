package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.OptionSubTypeEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.pricing.ImmutableBlackScholesPriceModelParameters;
import com.herron.exchange.common.api.common.messages.trading.TradingCalendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.herron.exchange.common.api.common.enums.AuctionAlgorithmEnum.DUTCH;
import static com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum.PRO_RATA;
import static com.herron.exchange.common.api.common.enums.OptionExerciseTyleEnum.AMERICAN;
import static com.herron.exchange.common.api.common.enums.OptionTypeEnum.CALL;
import static com.herron.exchange.common.api.common.enums.SettlementTypeEnum.PHYSICAL;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultOrderbookDataTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var instrument = ImmutableDefaultOptionInstrument.builder()
                .instrumentId("instrumentId")
                .underlyingInstrumentId("underlying")
                .settlementType(PHYSICAL)
                .firstTradingDate(Timestamp.from(LocalDate.MIN))
                .lastTradingDate(Timestamp.from(LocalDate.MAX))
                .maturityDate(Timestamp.now())
                .strikePrice(PureNumber.create(100))
                .optionType(CALL)
                .optionSubType(OptionSubTypeEnum.OOE)
                .optionExerciseStyle(AMERICAN)
                .priceModelParameters(ImmutableBlackScholesPriceModelParameters.builder().yieldCurveId("id").build())
                .product(ImmutableProduct.builder().currency("eur").productId("product").market(ImmutableMarket.builder().marketId("market").businessCalendar(BusinessCalendar.defaultWeekendCalendar()).build()).build())
                .build();

        var object = ImmutableDefaultOrderbookData.builder()
                .orderbookId("instrumendId")
                .instrument(instrument)
                .tradingCalendar(TradingCalendar.twentyFourSevenTradingCalendar())
                .matchingAlgorithm(PRO_RATA)
                .auctionAlgorithm(DUTCH)
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}