package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.PriceModels;

public interface PriceModelParameters extends Message {
    PriceModels priceModel();
}
