package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.PRODUCT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableProduct.Builder.class)
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

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return PRODUCT;
    }
}
