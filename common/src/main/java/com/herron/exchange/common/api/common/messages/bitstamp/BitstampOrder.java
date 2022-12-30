package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Order;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.model.Member;
import com.herron.exchange.common.api.common.model.Participant;
import com.herron.exchange.common.api.common.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampOrder(@JsonProperty("orderOperation") String orderOperationString,
                            @JsonProperty("participant") String participantString,
                            @JsonProperty("orderId") String orderId,
                            @JsonProperty("orderType") int orderTypeValue,
                            @JsonProperty("initialVolume") double initialVolume,
                            @JsonProperty("currentVolume") double currentVolume,
                            @JsonProperty("price") double price,
                            @JsonProperty("timeStampInMs") long timeStampInMs,
                            @JsonProperty("instrumentId") String instrumentId,
                            @JsonProperty("orderbookId") String orderbookId) implements Order {


    @Override
    public Participant participant() {
        return new Participant(new Member(participantString.split(";")[0]), new User(participantString.split(";")[1]));
    }

    @Override
    public OrderTypeEnum orderType() {
        return OrderTypeEnum.fromValue(orderTypeValue);
    }

    @Override
    public OrderOperationEnum orderOperation() {
        return OrderOperationEnum.fromValue(orderOperationString);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_ORDER;
    }


}
