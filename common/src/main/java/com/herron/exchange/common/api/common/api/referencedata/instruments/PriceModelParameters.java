package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.PriceModels;
import com.herron.exchange.common.api.common.enums.PriceType;
import org.immutables.value.Value;

import java.util.List;

public interface PriceModelParameters extends Message {
    PriceModels priceModel();

    @Value.Default
    default List<PriceType> intradayPricePriority() {
        return List.of(
                PriceType.LAST_PRICE,
                PriceType.MID_BID_ASK_PRICE,
                PriceType.VWAP,
                PriceType.THEORETICAL
        );
    }

    @Value.Default
    default List<PriceType> SettlementPricePriority() {
        return List.of(
                PriceType.VWAP,
                PriceType.LAST_PRICE,
                PriceType.MID_BID_ASK_PRICE,
                PriceType.THEORETICAL
        );
    }
}
