package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.OptionExerciseTyleEnum;
import com.herron.exchange.common.api.common.enums.OptionTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.InstrumentTypeEnum.OPTION;

public interface OptionInstrument extends DerivativeInstrument {

    Price strikePrice();

    OptionTypeEnum optionType();

    OptionExerciseTyleEnum optionExerciseStyle();

    @Value.Derived
    default InstrumentTypeEnum instrumentType() {
        return OPTION;
    }
}
