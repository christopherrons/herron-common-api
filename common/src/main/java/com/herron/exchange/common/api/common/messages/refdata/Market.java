package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.MARKET;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarket.Builder.class)
public interface Market extends Message {
    String marketId();

    BusinessCalendar businessCalendar();

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return MARKET;
    }
}
