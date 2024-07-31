package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.Quote;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.PRICE_QUOTE;
import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.VOLUME_QUOTE;

@Value.Immutable
@JsonDeserialize(builder = ImmutablePriceQuote.Builder.class)
public interface VolumeQuote extends Quote {
    Volume volume();

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return VOLUME_QUOTE;
    }
}