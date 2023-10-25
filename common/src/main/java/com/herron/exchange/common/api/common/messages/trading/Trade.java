package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.TradeType;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.TRADE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableTrade.Builder.class)
public interface Trade extends OrderbookEvent {

    TradeType tradeType();

    Participant bidParticipant();

    Participant askParticipant();

    String tradeId();

    String bidOrderId();

    String askOrderId();

    boolean isBidSideAggressor();

    Volume volume();

    Price price();

    String instrumentId();

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return TRADE;
    }

}
