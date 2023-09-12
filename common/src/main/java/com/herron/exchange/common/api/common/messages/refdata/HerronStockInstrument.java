package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Instrument;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.model.Market;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStockInstrument(String instrumentId, Market market) implements Instrument {

    public HerronStockInstrument(Instrument instrument) {
        this(instrument.instrumentId(), instrument.market());
    }

    @Override
    public HerronStockInstrument getCopy() {
        return new HerronStockInstrument(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STOCK_INSTRUMENT;
    }

    @Override
    public InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.STOCK;
    }
}
