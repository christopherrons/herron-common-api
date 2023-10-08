package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.exchange.Market;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronMarket.Builder.class)
public interface HerronMarket extends Market {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_MARKET;
    }
}
