package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.model.Participant;

public interface Order extends Message {

    String orderId();

    String orderbookId();

    String instrumentId();

    Participant participant();

    OrderTypeEnum orderType();
    OrderOperationEnum orderOperation();

    double price();

    double initialVolume();

    double currentVolume();

}