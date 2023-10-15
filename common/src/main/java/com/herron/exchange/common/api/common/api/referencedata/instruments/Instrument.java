package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.referencedata.exchange.Product;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import org.immutables.value.Value;

import java.time.LocalDate;

public interface Instrument extends Message {

    @Value.Default
    default String instrumentName() {
        return instrumentId();
    }

    String instrumentId();

    InstrumentTypeEnum instrumentType();

    Product product();

    @Value.Default
    default String currency() {
        return product().currency();
    }

    @Value.Default
    default double contractSize() {
        return product().contractSize();
    }
    
    LocalDate firstTradingDate();

    LocalDate lastTradingDate();

}
