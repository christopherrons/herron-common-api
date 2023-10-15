package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.orders.CancelOrder;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronCancelOrder.Builder.class)
public interface HerronCancelOrder extends CancelOrder {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_CANCEL_ORDER;
    }

}
