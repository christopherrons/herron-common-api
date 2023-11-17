package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataEntry;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataForwardPriceCurveStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataForwardPriceCurveStaticKey;
import com.herron.exchange.common.api.common.math.parametricmodels.forwardcurve.ForwardPriceCurve;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_FORWARD_PRICE_CURVE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataForwardPriceCurve.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataForwardPriceCurve extends MarketDataEntry {

    static MarketDataForwardPriceCurve create(Timestamp timeStamp,
                                              String id,
                                              ForwardPriceCurve curve) {
        return ImmutableMarketDataForwardPriceCurve.builder()
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(timeStamp).build())
                .staticKey(ImmutableMarketDataForwardPriceCurveStaticKey.builder().instrumentId(id).build())
                .forwardPriceCurve(curve)
                .build();
    }

    ForwardPriceCurve forwardPriceCurve();

    @Override
    MarketDataForwardPriceCurveStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_FORWARD_PRICE_CURVE;
    }
}
