package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.exchange.Market;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_MARKET;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarket.Builder.class)
public interface DefaultMarket extends Market {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_MARKET;
    }
}
