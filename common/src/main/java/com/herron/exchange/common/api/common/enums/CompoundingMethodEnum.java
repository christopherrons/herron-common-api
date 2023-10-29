package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum CompoundingMethodEnum {
    SIMPLE("simple"),
    CONTINUOUS("continuous"),
    COMPOUNDING("compounding");

    private static final Map<String, CompoundingMethodEnum> VALUES_BY_IDENTIFIER = stream(CompoundingMethodEnum.values()).collect(toMap(CompoundingMethodEnum::getValue, identity()));
    private final String value;

    CompoundingMethodEnum(String value) {
        this.value = value;
    }

    public static CompoundingMethodEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public static double calculateValue(CompoundingMethodEnum compoundingMethodEnum,
                                        double interest,
                                        double time,
                                        int frequency) {
        return switch (compoundingMethodEnum) {
            case SIMPLE -> (interest / frequency) * time;
            case COMPOUNDING -> Math.pow(1 + (interest / frequency), time * frequency);
            case CONTINUOUS -> Math.exp(interest * time);
        };
    }

    public String getValue() {
        return value;
    }

    public double calculateValue(double interest, double time, int frequency) {
        return CompoundingMethodEnum.calculateValue(this, interest, time, frequency);
    }
}
