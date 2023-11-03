package com.herron.exchange.common.api.common.messages.pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableOptionGreeks.Builder.class)
public interface OptionGreeks {

    PureNumber delta();

    PureNumber theta();

    PureNumber gamma();

    PureNumber vega();

    PureNumber rho();
}
