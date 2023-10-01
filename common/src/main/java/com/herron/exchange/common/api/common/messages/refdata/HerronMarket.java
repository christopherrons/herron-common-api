package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.Market;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
public interface HerronMarket extends Market {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_MARKET;
    }
}
