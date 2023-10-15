package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.enums.OrderExecutionTypeEnum;
import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import com.herron.exchange.common.api.common.enums.OrderSideEnum;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;

public interface Order extends Event {

    String orderId();

    String orderbookId();

    String instrumentId();

    Participant participant();

    OrderSideEnum orderSide();

    OrderOperationEnum orderOperation();

    Volume initialVolume();

    Volume currentVolume();

    Price price();

    OrderExecutionTypeEnum orderExecutionType();

    OrderTypeEnum orderType();

    default boolean isActiveOrder() {
        return !isNonActiveOrder();
    }

    default boolean isNonActiveOrder() {
        if (this.orderType() == OrderTypeEnum.MARKET) {
            return true;
        }

        return switch (this.orderExecutionType()) {
            case FAK, FOK -> true;
            default -> false;
        };
    }

}
