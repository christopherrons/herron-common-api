package com.herron.exchange.common.api.common.enums.messagetypes;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.MessageType;
import com.herron.exchange.common.api.common.messages.pricing.BasicFuturePriceModelParameters;
import com.herron.exchange.common.api.common.messages.pricing.BlackScholesPriceModelParameters;
import com.herron.exchange.common.api.common.messages.pricing.BondDiscountPriceModelParameters;
import com.herron.exchange.common.api.common.messages.pricing.BondDiscountPriceModelResult;
import com.herron.exchange.common.api.common.messages.refdata.IntangiblePriceModelParameters;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum PricingMessageTypeEnum implements MessageType {
    BLACK_SCHOLES_PRICE_MODEL_PARAMETERS("BSPMP", BlackScholesPriceModelParameters.class),
    BOND_DISCOUNT_PRICE_MODEL_PARAMETERS("BDPMP", BondDiscountPriceModelParameters.class),
    BOND_DISCOUNT_PRICE_MODEL_RESULT("BDPMR", BondDiscountPriceModelResult.class),
    BASIC_FUTURE_PRICE_MODEL_PARAMETERS("BFPMP", BasicFuturePriceModelParameters.class),
    INTANGIBLE_PRICE_MODEL_PARAMETERS("IPMP", IntangiblePriceModelParameters.class);

    private static final Map<String, PricingMessageTypeEnum> VALUES_BY_IDENTIFIER = stream(PricingMessageTypeEnum.values())
            .collect(toMap(PricingMessageTypeEnum::getMessageTypeId, identity()));

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDeserialized;

    PricingMessageTypeEnum(String messageTypeId, Class<? extends Message> classToBeDeserialized) {
        this.messageTypeId = messageTypeId;
        this.classToBeDeserialized = classToBeDeserialized;
    }

    public static PricingMessageTypeEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, null);
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }

    public static Map<String, Class<? extends Message>> getIdToClassImplementation() {
        return stream(PricingMessageTypeEnum.values()).collect(Collectors.toMap(PricingMessageTypeEnum::getMessageTypeId, PricingMessageTypeEnum::getClassToBeDeserialized));
    }
}
