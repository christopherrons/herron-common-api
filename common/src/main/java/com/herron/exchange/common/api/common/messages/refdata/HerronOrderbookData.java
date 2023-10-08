package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.orderbook.OrderbookData;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronOrderbookData.Builder.class)
public interface HerronOrderbookData extends OrderbookData {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_DATA;
    }
}
