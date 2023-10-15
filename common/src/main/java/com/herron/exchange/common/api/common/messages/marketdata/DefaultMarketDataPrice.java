package com.herron.exchange.common.api.common.messages.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataPrice;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.PriceType;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.immutables.value.Value;

import java.time.LocalDateTime;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataPrice.Builder.class)
public interface DefaultMarketDataPrice extends MarketDataPrice {

    default DefaultMarketDataPrice from(PriceType priceType,
                                        LocalDateTime localDateTime,
                                        String instrumentId,
                                        Price price) {
        return ImmutableDefaultMarketDataPrice.builder()
                .priceType(priceType)
                .staticKey(ImmutableDefaultMarketDataPriceStaticKey.builder().instrumentId(instrumentId).build())
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(localDateTime).build())
                .price(price)
                .build();
    }

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_MARKET_DATA_PRICE;
    }

}
