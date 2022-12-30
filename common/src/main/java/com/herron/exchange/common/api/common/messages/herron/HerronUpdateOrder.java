package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.UpdateOrder;
import com.herron.exchange.common.api.common.enums.*;
import com.herron.exchange.common.api.common.model.Participant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronUpdateOrder(@JsonProperty("orderOperation") OrderOperationEnum orderOperation,
                                @JsonProperty("participant") Participant participant,
                                @JsonProperty("orderId") String orderId,
                                @JsonProperty("orderSide") OrderSideEnum orderSide,
                                @JsonProperty("initialVolume") double initialVolume,
                                @JsonProperty("currentVolume") double currentVolume,
                                @JsonProperty("price") double price,
                                @JsonProperty("timeStampInMs") long timeStampInMs,
                                @JsonProperty("instrumentId") String instrumentId,
                                @JsonProperty("orderbookId") String orderbookId,
                                @JsonProperty("orderExecutionType") OrderExecutionTypeEnum orderExecutionType,
                                @JsonProperty("orderType") OrderTypeEnum orderType,
                                @JsonProperty("updateOperationType") OrderUpdatedOperationTypeEnum updateOperationType) implements UpdateOrder {


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
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ADD_ORDER;
    }

}
