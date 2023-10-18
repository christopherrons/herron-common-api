package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.orderbook.OrderbookData;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_ORDERBOOK_DATA;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultOrderbookData.Builder.class)
public interface DefaultOrderbookData extends OrderbookData {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_ORDERBOOK_DATA;
    }
}
