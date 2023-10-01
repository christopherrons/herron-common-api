package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.OrderbookData;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable()
public interface HerronOrderbookData extends OrderbookData {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_DATA;
    }
}
