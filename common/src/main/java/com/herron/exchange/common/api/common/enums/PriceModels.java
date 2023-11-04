package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum PriceModels {
    INTANGIBLE,
    BASIC_FUTURE_MODEL,
    BLACK_SCHOLES,
    BLACK_76,
    BARONE_ADESI_WHALEY,
    BOND_DISCOUNT
}
