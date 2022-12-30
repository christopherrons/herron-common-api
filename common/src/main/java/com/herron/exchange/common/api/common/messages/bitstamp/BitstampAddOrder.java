package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.AddOrder;
import com.herron.exchange.common.api.common.enums.*;
import com.herron.exchange.common.api.common.model.Member;
import com.herron.exchange.common.api.common.model.Participant;
import com.herron.exchange.common.api.common.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampAddOrder(@JsonProperty("orderOperation") String orderOperationString,
                               @JsonProperty("participant") String participantString,
                               @JsonProperty("orderId") String orderId,
                               @JsonProperty("orderSide") int orderSideValue,
                               @JsonProperty("initialVolume") double initialVolume,
                               @JsonProperty("currentVolume") double currentVolume,
                               @JsonProperty("price") double price,
                               @JsonProperty("timeStampInMs") long timeStampInMs,
                               @JsonProperty("instrumentId") String instrumentId,
                               @JsonProperty("orderbookId") String orderbookId,
                               @JsonProperty("orderExecutionType") String orderExecutionTypeString,
                               @JsonProperty("orderType") String orderTypeString) implements AddOrder {


    @Override
    public Participant participant() {
        return new Participant(new Member(participantString.split(";")[0]), new User(participantString.split(";")[1]));
    }

    @Override
    public OrderSideEnum orderSide() {
        return OrderSideEnum.fromValue(orderSideValue);
    }

    @Override
    public OrderOperationEnum orderOperation() {
        return OrderOperationEnum.fromValue(orderOperationString);
    }

    @Override
    public OrderExecutionTypeEnum orderExecutionType() {
        return OrderExecutionTypeEnum.fromValue(orderExecutionTypeString);
    }

    @Override
    public OrderTypeEnum orderType() {
        return OrderTypeEnum.fromValue(orderTypeString);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_ORDER;
    }


    @Override
    public OrderAddOperationTypeEnum addOperationType() {
        return OrderAddOperationTypeEnum.NEW_ORDER;
    }
}
