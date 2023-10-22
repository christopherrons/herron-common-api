package com.herron.exchange.common.api.common.messages.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.TimeComponentKey;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.DEFAULT_TIME_COMPONENT_KEY;


@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultTimeComponentKey.Builder.class)
public interface DefaultTimeComponentKey extends TimeComponentKey {

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return DEFAULT_TIME_COMPONENT_KEY;
    }
}
