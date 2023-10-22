package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.PriceModels;
import com.herron.exchange.common.api.common.messages.refdata.Product;
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

    PriceModelParameters priceModelParameters();

    @Value.Derived
    @JsonIgnore
    default PriceModels priceModel() {
        return priceModelParameters().priceModel();
    }

}
