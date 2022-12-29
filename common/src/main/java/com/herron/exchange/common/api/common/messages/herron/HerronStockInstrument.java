package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Instrument;
import com.herron.exchange.common.api.common.api.StateChange;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStockInstrument(@JsonProperty("instrumentId") String instrumentId,
                                    @JsonProperty("instrumentType") InstrumentTypeEnum instrumentType,
                                    @JsonProperty("tradingCurrency") String tradingCurrency,
                                    long timeStampInMs) implements Instrument {

    public HerronStockInstrument(Instrument instrument) {
        this(instrument.instrumentId(),
                instrument.instrumentType(),
                instrument.tradingCurrency(),
                instrument.timeStampInMs());
    }
    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STOCK_INSTRUMENT;
    }

}
