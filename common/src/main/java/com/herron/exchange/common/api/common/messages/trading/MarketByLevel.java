package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import java.util.List;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.MARKET_BY_LEVEL;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketByLevel.Builder.class)
public interface MarketByLevel extends OrderbookEvent {

    List<LevelData> levelData();

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return MARKET_BY_LEVEL;
    }

    @Value.Immutable
    @JsonDeserialize(builder = ImmutableLevelData.Builder.class)
    interface LevelData {
        int level();
        @Nullable
        Long nrOfAskOrders();
        @Nullable
        Long nrOfBidOrders();
        @Nullable
        Price askPrice();
        @Nullable
        Price bidPrice();
        @Nullable
        Volume bidVolume();
        @Nullable
        Volume askVolume();

        @Value.Derived
        default long totalNrOfOrders() {
            return nrOfAskOrders() + nrOfBidOrders();
        }

        @Value.Derived
        default Volume totalVolume() {
            return bidVolume().add(askVolume());
        }
    }
}