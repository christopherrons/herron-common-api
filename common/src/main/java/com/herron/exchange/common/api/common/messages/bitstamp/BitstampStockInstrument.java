package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Instrument;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampStockInstrument(@JsonProperty("instrumentId") String instrumentId,
                                      @JsonProperty("instrumentType") String instrumentTypeString,
                                      @JsonProperty("tradingCurrency") String tradingCurrency,
                                      long timeStampInMs) implements Instrument {

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_STOCK_INSTRUMENT;
    }

    @Override
    public InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.fromValue(instrumentTypeString);
    }
}
