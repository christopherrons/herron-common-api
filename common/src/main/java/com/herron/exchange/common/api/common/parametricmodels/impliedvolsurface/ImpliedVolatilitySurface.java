package com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.OptionTypeEnum;

import static com.herron.exchange.common.api.common.enums.OptionTypeEnum.CALL;

public class ImpliedVolatilitySurface {

    private final String id;
    private final double underlyingPrice;

    public ImpliedVolatilitySurface(@JsonProperty("id") String id, @JsonProperty("underlyingPrice") double underlyingPrice) {
        this.id = id;
        this.underlyingPrice = underlyingPrice;
    }

    public static ImpliedVolatilitySurface create(String id, double value) {
        return new ImpliedVolatilitySurface(id, value);
    }

    public double getImpliedVolatility(double timeToMaturity, double strikePrice, OptionTypeEnum optionType) {
        double logMoneyness = optionType == CALL ? Math.log(underlyingPrice / strikePrice) : Math.log(strikePrice / underlyingPrice);
        return 0.0;
    }

    public String getId() {
        return id;
    }

    public double getSpotPrice() {
        return underlyingPrice;
    }
}
