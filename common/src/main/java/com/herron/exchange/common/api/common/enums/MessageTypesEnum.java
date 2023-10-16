package com.herron.exchange.common.api.common.enums;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.mapper.JsonMapperUtil;
import com.herron.exchange.common.api.common.messages.DefaultBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.DefaultBusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.DefaultDataStreamState;
import com.herron.exchange.common.api.common.messages.marketdata.DefaultMarketDataPrice;
import com.herron.exchange.common.api.common.messages.refdata.DefaultBondInstrument;
import com.herron.exchange.common.api.common.messages.refdata.DefaultEquityInstrument;
import com.herron.exchange.common.api.common.messages.refdata.DefaultFutureInstrument;
import com.herron.exchange.common.api.common.messages.refdata.DefaultMarket;
import com.herron.exchange.common.api.common.messages.refdata.DefaultOptionInstrument;
import com.herron.exchange.common.api.common.messages.refdata.DefaultOrderbookData;
import com.herron.exchange.common.api.common.messages.refdata.DefaultProduct;
import com.herron.exchange.common.api.common.messages.trading.DefaultAddOrder;
import com.herron.exchange.common.api.common.messages.trading.DefaultCancelOrder;
import com.herron.exchange.common.api.common.messages.trading.DefaultStateChange;
import com.herron.exchange.common.api.common.messages.trading.DefaultTrade;
import com.herron.exchange.common.api.common.messages.trading.DefaultTradeExecution;
import com.herron.exchange.common.api.common.messages.trading.DefaultTradingCalendar;
import com.herron.exchange.common.api.common.messages.trading.DefaultUpdateOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MessageTypesEnum {

    INVALID_MESSAGE_TYPE(null, null),
    DEFAULT_MARKET_DATA_PRICE("DMDP", DefaultMarketDataPrice.class),
    DEFAULT_BROADCAST_MESSAGE("DEBM", DefaultBroadcastMessage.class),
    DEFAULT_TRADING_CALENDAR("DFTC", DefaultTradingCalendar.class),
    DEFAULT_BUSINESS_CALENDAR("DFBC", DefaultBusinessCalendar.class),
    DEFAULT_ADD_ORDER("DFAO", DefaultAddOrder.class),
    DEFAULT_UPDATE_ORDER("DFUO", DefaultUpdateOrder.class),
    DEFAULT_CANCEL_ORDER("DFCO", DefaultCancelOrder.class),
    DEFAULT_TRADE("DFTR", DefaultTrade.class),
    DEFAULT_ORDERBOOK_DATA("DFOB", DefaultOrderbookData.class),
    DEFAULT_EQUITY_INSTRUMENT("DFSI", DefaultEquityInstrument.class),
    DEFAULT_BOND_INSTRUMENT("DFBI", DefaultBondInstrument.class),
    DEFAULT_OPTION_INSTRUMENT("DFOI", DefaultOptionInstrument.class),
    DEFAULT_FUTURE_INSTRUMENT("DFFI", DefaultFutureInstrument.class),
    DEFAULT_TRADE_EXECUTION("DFEX", DefaultTradeExecution.class),
    DEFAULT_DATA_STREAM_STATE("DFDL", DefaultDataStreamState.class),
    DEFAULT_MARKET("DFMA", DefaultMarket.class),
    DEFAULT_PRODUCT("DFPR", DefaultProduct.class),
    DEFAULT_ORDERBOOK_STATE_CHANGE("DFSC", DefaultStateChange.class);

    private static final Map<String, MessageTypesEnum> VALUES_BY_IDENTIFIER = stream(MessageTypesEnum.values())
            .collect(toMap(MessageTypesEnum::getMessageTypeId, identity()));
    private static final DefaultMessageFactory MESSAGE_FACTORY = new DefaultMessageFactory();

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDeserialized;

    MessageTypesEnum(String messageTypeId, Class<? extends Message> classToBeDeserialized) {
        this.messageTypeId = messageTypeId;
        this.classToBeDeserialized = classToBeDeserialized;
    }

    public static MessageTypesEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE);
    }

    public static Message deserializeMessage(String messageTypeId, String message) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE).deserializeMessage(message);
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }

    public Message deserializeMessage(Object message) {
        if (classToBeDeserialized == null) {
            return null;
        }
        return MESSAGE_FACTORY.deserializeMessage(message, classToBeDeserialized);
    }

    public String serializeMessage(Message message) {
        if (message == null) {
            return null;
        }
        return MESSAGE_FACTORY.serializeMessage(message);
    }

    private static class DefaultMessageFactory {
        private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageFactory.class);
        private final SimpleModule typeIdModule = new SimpleModule("DEFAULT-TYPE-ID");
        private final ObjectMapper objectMapper;

        public DefaultMessageFactory() {
            registerDefaultTypes();
            this.objectMapper = configure(Jackson2ObjectMapperBuilder.json()).build();
        }

        private void registerDefaultTypes() {
            Arrays.stream(MessageTypesEnum.values()).forEach(
                    messageType -> registerSubtype(messageType.getMessageTypeId(), messageType.getClassToBeDeserialized())
            );
        }

        private void registerSubtype(String typeId, Class<? extends Message> implementationClazz) {
            typeIdModule.registerSubtypes(new NamedType(implementationClazz, typeId));
        }

        public Jackson2ObjectMapperBuilder configure(Jackson2ObjectMapperBuilder builder) {
            return builder
                    // .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                    .annotationIntrospector(new JacksonAnnotationIntrospector())
                    .modules(
                            new Jdk8Module(),
                            new JavaTimeModule(),
                            typeIdModule
                    );
        }


        public String serializeMessage(Message message) {
            try {
                return objectMapper.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                LOGGER.warn("Unable to map message {}: {}", message, e);
            }
            return null;
        }

        public Message deserializeMessage(Object message, Class<? extends Message> classToBeDecoded) {
            try {
                if (message instanceof String messageString) {
                    return objectMapper.readValue(messageString, classToBeDecoded);
                } else if (message instanceof JsonNode node) {
                    return objectMapper.treeToValue(node, classToBeDecoded);
                }
                LOGGER.warn("Unable to map unhandled type: {}:", message);
            } catch (JsonProcessingException e) {
                LOGGER.warn("Unable to map message {}: {}", message, e);
            }
            return null;
        }
    }
}
