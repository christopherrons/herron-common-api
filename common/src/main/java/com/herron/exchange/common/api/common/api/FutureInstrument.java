package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import org.immutables.value.Value;

public interface FutureInstrument extends DerivativeInstrument {

    @Value.Default
    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.FUTURE;
    }
}
