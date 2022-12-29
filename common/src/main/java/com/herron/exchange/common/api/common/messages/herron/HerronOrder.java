package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Order;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.model.Participant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrder(@JsonProperty("orderOperation") OrderOperationEnum orderOperation,
                          @JsonProperty("participant") Participant participant,
                          @JsonProperty("orderId") String orderId,
                          @JsonProperty("orderType") OrderTypeEnum orderType,
                          @JsonProperty("initialVolume") double initialVolume,
                          @JsonProperty("currentVolume") double currentVolume,
                          @JsonProperty("price") double price,
                          @JsonProperty("timeStampInMs") long timeStampInMs,
                          @JsonProperty("instrumentId") String instrumentId,
                          @JsonProperty("orderbookId") String orderbookId) implements Order {


    public HerronOrder(Order order) {
        this(order.orderOperation(),
                order.participant(),
                order.orderId(),
                order.orderType(),
                order.initialVolume(),
                order.currentVolume(),
                order.price(),
                order.timeStampInMs(),
                order.instrumentId(),
                order.orderbookId());
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDER;
    }


}
