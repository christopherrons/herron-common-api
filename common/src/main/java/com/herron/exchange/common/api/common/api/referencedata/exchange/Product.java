package com.herron.exchange.common.api.common.api.referencedata.exchange;

import com.herron.exchange.common.api.common.api.Message;
import org.immutables.value.Value;

public interface Product extends Message {

    @Value.Default
    default String productName() {
        return productId();
    }

    String productId();

    Market market();

    String currency();

    @Value.Default
    default BusinessCalendar businessCalendar() {
        return market().businessCalendar();
    }

    @Value.Default
    default double contractSize() {
        return 1;
    }

}