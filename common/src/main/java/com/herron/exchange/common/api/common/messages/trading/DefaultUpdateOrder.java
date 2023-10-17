package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.orders.UpdateOrder;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultUpdateOrder.Builder.class)
public interface DefaultUpdateOrder extends UpdateOrder {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_UPDATE_ORDER;
    }
}
