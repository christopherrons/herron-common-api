package com.herron.exchange.common.api.common.messages.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.TimeComponentKey;
import org.immutables.value.Value;


@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultTimeComponentKey.Builder.class)
public interface DefaultTimeComponentKey extends TimeComponentKey {
}
