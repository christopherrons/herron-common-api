package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Instrument;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStockInstrument(String instrumentId,
                                    InstrumentTypeEnum instrumentType) implements Instrument {

    public HerronStockInstrument(Instrument instrument) {
        this(instrument.instrumentId(),
                instrument.instrumentType());
    }

    @Override
    public HerronStockInstrument getCopy() {
        return new HerronStockInstrument(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STOCK_INSTRUMENT;
    }

}
