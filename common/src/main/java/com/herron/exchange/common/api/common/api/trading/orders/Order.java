package com.herron.exchange.common.api.common.api.trading.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.*;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;

public interface Order extends OrderbookEvent {

    String orderId();

    String instrumentId();

    Participant participant();

    OrderSideEnum orderSide();

    OrderOperationEnum orderOperation();

    Volume initialVolume();

    Volume currentVolume();

    Price price();

    OrderOperationCauseEnum orderOperationCause();

    TimeInForceEnum timeInForce();

    OrderTypeEnum orderType();

    @JsonIgnore
    default boolean isActiveOrder() {
        return !isNonActiveOrder();
    }

    @JsonIgnore
    default boolean isNonActiveOrder() {
        if (this.orderType() == OrderTypeEnum.MARKET) {
            return true;
        }

        return switch (this.timeInForce()) {
            case FAK, FOK -> true;
            default -> false;
        };
    }

}
