package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.exchange.Product;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_PRODUCT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultProduct.Builder.class)
public interface DefaultProduct extends Product {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_PRODUCT;
    }
}
