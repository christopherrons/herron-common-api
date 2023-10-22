package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.orders.MarketOrder;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.DEFAULT_MARKET_ORDER;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketOrder.Builder.class)
public interface DefaultMarketOrder extends MarketOrder {

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return DEFAULT_MARKET_ORDER;
    }

}
