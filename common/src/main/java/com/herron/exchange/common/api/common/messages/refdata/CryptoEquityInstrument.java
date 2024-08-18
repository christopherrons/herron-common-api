package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.EquityInstrument;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.CRYPTO_EQUITY_INSTRUMENT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableCryptoEquityInstrument.Builder.class)
public interface CryptoEquityInstrument extends EquityInstrument {

    String token();

    @Value.Default
    default String counterCurrency() {
        return currency();
    }

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return CRYPTO_EQUITY_INSTRUMENT;
    }
}
