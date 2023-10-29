package com.herron.exchange.common.api.common.messages.marketdata.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataResponse;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.marketdata.entries.MarketDataPrice;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.Status.ERROR;
import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_PRICE_RESPONSE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataPriceResponse.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataPriceResponse extends MarketDataResponse {

    static MarketDataPriceResponse createErrorResponse(String errorMessage) {
        return ImmutableMarketDataPriceResponse.builder()
                .status(ERROR)
                .error(errorMessage)
                .build();
    }

    @Nullable
    MarketDataPrice marketDataPrice();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_PRICE_RESPONSE;
    }
}
