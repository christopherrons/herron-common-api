package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.OrderExecutionTypeEnum;
import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import com.herron.exchange.common.api.common.enums.OrderSideEnum;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.model.MonetaryAmount;
import com.herron.exchange.common.api.common.model.Participant;

public interface Order extends Message {

    String orderId();

    String orderbookId();

    String instrumentId();

    Participant participant();

    OrderSideEnum orderSide();

    OrderOperationEnum orderOperation();

    MonetaryAmount price();

    double initialVolume();

    double currentVolume();

    OrderExecutionTypeEnum orderExecutionType();

    OrderTypeEnum orderType();

    default boolean isActiveOrder() {
        return !isNonActiveOrder();
    }

    default boolean isNonActiveOrder() {
        if (this.orderType().equals(OrderTypeEnum.MARKET)) {
            return true;
        }

        return switch (this.orderExecutionType()) {
            case FAK, FOK -> true;
            default -> false;
        };
    }

}
