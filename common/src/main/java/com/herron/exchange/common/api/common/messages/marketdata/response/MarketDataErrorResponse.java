package com.herron.exchange.common.api.common.messages.marketdata.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataResponse;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.Status.ERROR;
import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_ERROR_RESPONSE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataErrorResponse.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataErrorResponse extends MarketDataResponse {

    static MarketDataErrorResponse createErrorResponse(String errorMessage) {
        return ImmutableMarketDataErrorResponse.builder()
                .status(ERROR)
                .error(errorMessage)
                .build();
    }

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_ERROR_RESPONSE;
    }
}
