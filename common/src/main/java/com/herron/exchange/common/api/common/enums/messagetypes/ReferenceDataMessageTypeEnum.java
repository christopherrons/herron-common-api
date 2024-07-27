package com.herron.exchange.common.api.common.enums.messagetypes;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.MessageType;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import com.herron.exchange.common.api.common.messages.refdata.*;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum ReferenceDataMessageTypeEnum implements MessageType {
    MARKET("MARK", Market.class),

    BUSINESS_CALENDAR("BUCA", BusinessCalendar.class),

    INSTRUMENT_HIERARCHY_GROUP("INHI", InstrumentHierarchyGroup.class),

    PRODUCT("PROD", Product.class),

    DEFAULT_ORDERBOOK_DATA("DFOB", DefaultOrderbookData.class),

    DEFAULT_EQUITY_INSTRUMENT("DFSI", DefaultEquityInstrument.class),

    DEFAULT_BOND_INSTRUMENT("DFBI", DefaultBondInstrument.class),

    DEFAULT_OPTION_INSTRUMENT("DFOI", DefaultOptionInstrument.class),

    DEFAULT_FUTURE_INSTRUMENT("DFFI", DefaultFutureInstrument.class);


    private static final Map<String, ReferenceDataMessageTypeEnum> VALUES_BY_IDENTIFIER = stream(ReferenceDataMessageTypeEnum.values())
            .collect(toMap(ReferenceDataMessageTypeEnum::getMessageTypeId, identity()));

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDeserialized;

    ReferenceDataMessageTypeEnum(String messageTypeId, Class<? extends Message> classToBeDeserialized) {
        this.messageTypeId = messageTypeId;
        this.classToBeDeserialized = classToBeDeserialized;
    }

    public static ReferenceDataMessageTypeEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, null);
    }

    public static Map<String, Class<? extends Message>> getIdToClassImplementation() {
        return stream(ReferenceDataMessageTypeEnum.values()).collect(Collectors.toMap(ReferenceDataMessageTypeEnum::getMessageTypeId, ReferenceDataMessageTypeEnum::getClassToBeDeserialized));
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }
}
