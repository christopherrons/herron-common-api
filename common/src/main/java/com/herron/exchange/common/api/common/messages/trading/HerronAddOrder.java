package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.orders.AddOrder;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.OrderAddOperationTypeEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronAddOrder.Builder.class)
public interface HerronAddOrder extends AddOrder {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ADD_ORDER;
    }
}
