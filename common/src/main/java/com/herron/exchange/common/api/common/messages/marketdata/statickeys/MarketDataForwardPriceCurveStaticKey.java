package com.herron.exchange.common.api.common.messages.marketdata.statickeys;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.StaticKey;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_FORWARD_PRICE_CURVE_STATIC_KEY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataForwardPriceCurveStaticKey.Builder.class)
public interface MarketDataForwardPriceCurveStaticKey extends StaticKey {

    String instrumentId();

    PureNumber strikePrice();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_FORWARD_PRICE_CURVE_STATIC_KEY;
    }
}
