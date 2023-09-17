package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.Market;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.model.BusinessCalendar;

public record HerronMarket(String marketId, BusinessCalendar businessCalendar) implements Market {

    public HerronMarket(HerronMarket market) {
        this(market.marketId, market.businessCalendar);
    }

    @Override
    public Message getCopy() {
        return new HerronMarket(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return null;
    }
}
