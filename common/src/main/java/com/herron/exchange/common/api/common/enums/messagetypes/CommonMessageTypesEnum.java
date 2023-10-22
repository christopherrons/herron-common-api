package com.herron.exchange.common.api.common.enums.messagetypes;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.MessageType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.DefaultBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.*;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum CommonMessageTypesEnum implements MessageType {
    MONETARY_AMOUNT("MA", MonetaryAmount.class),
    VOLUME("V", Volume.class),
    QUANTITY("Q", Quantity.class),
    PRICE("P", Price.class),
    DEFAULT_BROADCAST_MESSAGE("DEBM", DefaultBroadcastMessage.class),
    DEFAULT_DATA_STREAM_STATE("DFDL", DefaultDataStreamState.class);

    private static final Map<String, CommonMessageTypesEnum> VALUES_BY_IDENTIFIER = stream(CommonMessageTypesEnum.values())
            .collect(toMap(CommonMessageTypesEnum::getMessageTypeId, identity()));
    private static final DefaultMessageFactory MESSAGE_FACTORY = new DefaultMessageFactory();

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDeserialized;

    CommonMessageTypesEnum(String messageTypeId, Class<? extends Message> classToBeDeserialized) {
        this.messageTypeId = messageTypeId;
        this.classToBeDeserialized = classToBeDeserialized;
    }

    public static CommonMessageTypesEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, null);
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }

    public static Map<String, Class<? extends Message>> getIdToClassImplementation() {
        return stream(CommonMessageTypesEnum.values()).collect(Collectors.toMap(CommonMessageTypesEnum::getMessageTypeId, CommonMessageTypesEnum::getClassToBeDeserialized));
    }

}
