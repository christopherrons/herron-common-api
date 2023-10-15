package com.herron.exchange.common.api.common.api.referencedata.exchange;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableDefaultProduct;
import org.immutables.value.Value;


@JsonSubTypes({
        @JsonSubTypes.Type(value = ImmutableDefaultProduct.class, name = "DFPR"),
})
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
