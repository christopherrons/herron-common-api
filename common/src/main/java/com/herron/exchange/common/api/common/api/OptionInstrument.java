package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.OptionExerciseTyleEnum;
import com.herron.exchange.common.api.common.enums.OptionTypeEnum;
import org.immutables.value.Value;

public interface OptionInstrument extends DerivativeInstrument {

    double strikePrice();

    OptionTypeEnum optionType();

    OptionExerciseTyleEnum optionExerciseStyle();

    @Value.Default
    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.OPTION;
    }
}
