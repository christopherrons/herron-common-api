package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.OptionTypeEnum;

public interface FutureInstrument extends DerivativeInstrument {

    double strikePrice();

    OptionTypeEnum optionType();
}
