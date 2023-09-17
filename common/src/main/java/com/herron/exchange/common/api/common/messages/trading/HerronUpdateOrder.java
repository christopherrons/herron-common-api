package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.UpdateOrder;
import com.herron.exchange.common.api.common.enums.*;
import com.herron.exchange.common.api.common.model.Participant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronUpdateOrder(OrderOperationEnum orderOperation,
                                Participant participant,
                                String orderId,
                                OrderSideEnum orderSide,
                                double initialVolume,
                                double currentVolume,
                                double price,
                                long timeStampInMs,
                                String instrumentId,
                                String orderbookId,
                                OrderExecutionTypeEnum orderExecutionType,
                                OrderTypeEnum orderType,
                                OrderUpdatedOperationTypeEnum updateOperationType) implements UpdateOrder {


    public HerronUpdateOrder(UpdateOrder order) {
        this(order.orderOperation(),
                order.participant(),
                order.orderId(),
                order.orderSide(),
                order.initialVolume(),
                order.currentVolume(),
                order.price(),
                order.timeStampInMs(),
                order.instrumentId(),
                order.orderbookId(),
                order.orderExecutionType(),
                order.orderType(),
                order.updateOperationType());
    }

    @Override
    public HerronUpdateOrder getCopy() {
        return new HerronUpdateOrder(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_UPDATE_ORDER;
    }
}
