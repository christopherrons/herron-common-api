package com.herron.exchange.common.api.common.messages;

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
public record BitstampOrder(@JsonProperty("orderOperation") String orderOperation,
                            @JsonProperty("participant") String participant,
                            @JsonProperty("orderId") String orderId,
                            @JsonProperty("orderType") int orderType,
                            @JsonProperty("initialVolume") double initialVolume,
                            @JsonProperty("currentVolume") double currentVolume,
                            @JsonProperty("price") double price,
                            @JsonProperty("timeStampInMs") long timeStampInMs,
                            @JsonProperty("instrumentId") String instrumentId,
                            @JsonProperty("orderbookId") String orderbookId) implements Order {


    @Override
    public Participant getParticipant() {
        return new Participant(new Member(participant.split(";")[0]), new User(participant.split(";")[0]));
    }

    @Override
    public OrderTypeEnum orderTypeEnum() {
        return OrderTypeEnum.fromValue(orderType);
    }

    @Override
    public OrderOperationEnum orderOperationEnum() {
        return OrderOperationEnum.fromValue(orderOperation);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_ORDER;
    }


}
