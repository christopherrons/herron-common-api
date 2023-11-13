package com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.api.math.Function3d;
import com.herron.exchange.common.api.common.enums.OptionTypeEnum;
import com.herron.exchange.common.api.common.math.interpolation.surfaces.HermiteBiCubicSurface;
import com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface.model.ImpliedVolatilitySurfaceModelParameters;

import static com.herron.exchange.common.api.common.enums.OptionTypeEnum.CALL;

public class ImpliedVolatilitySurface {

    private final String id;
    private final double underlyingPrice;
    private final ImpliedVolatilitySurfaceModelParameters parameters;
    private final Function3d impliedVolFunction;

    public ImpliedVolatilitySurface(@JsonProperty("id") String id,
                                    @JsonProperty("underlyingPrice") double underlyingPrice,
                                    @JsonProperty("parameters") ImpliedVolatilitySurfaceModelParameters parameters) {
        this.id = id;
        this.underlyingPrice = underlyingPrice;
        this.parameters = parameters;
        this.impliedVolFunction = createImpliedVolatilityFunction();
    }

    public static ImpliedVolatilitySurface create(String id, double underlyingPrice, ImpliedVolatilitySurfaceModelParameters parameters) {
        return new ImpliedVolatilitySurface(id, underlyingPrice, parameters);
    }

    private Function3d createImpliedVolatilityFunction() {
        var points = parameters.points().stream().map(CartesianPoint3d.class::cast).toList();
        return switch (parameters.surfaceConstructionMethod()) {
            case HERMITE_BICUBIC -> HermiteBiCubicSurface.create(points);
            default -> throw new IllegalArgumentException("Other methods are not supported");
        };
    }

    public double getImpliedVolatility(double timeToMaturity, double strikePrice, OptionTypeEnum optionType) {
        double logMoneyness = optionType == CALL ? Math.log(underlyingPrice / strikePrice) : Math.log(strikePrice / underlyingPrice);
        return impliedVolFunction.getFunctionValue(timeToMaturity, logMoneyness);
    }

    public String getId() {
        return id;
    }

    public double getSpotPrice() {
        return underlyingPrice;
    }
}
