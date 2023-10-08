package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.exchange.Product;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronMarket.Builder.class)
public interface HerronProduct extends Product {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_PRODUCT;
    }
}
