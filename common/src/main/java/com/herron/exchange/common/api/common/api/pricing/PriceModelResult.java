package com.herron.exchange.common.api.common.api.pricing;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.enums.Status;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import org.immutables.value.Value;

public interface PriceModelResult extends Event {

    Status status();

    Price price();

    Timestamp marketTime();

    @Value.Derived
    default Timestamp calculationTime() {
        return timeOfEvent();
    }

}
