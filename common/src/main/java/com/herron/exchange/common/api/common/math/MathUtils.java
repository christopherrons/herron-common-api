package com.herron.exchange.common.api.common.math;

public class MathUtils {
    public static final double[] ROUNDING_FACTORS = {1.0, 10.0, 100.0, 1000.0, 10_000.0, 100_000.0, 1_000_000.0, 10_000_000.0, 100_000_000.0, 1_000_000_000.0, 10_000_000_000.0};
    public static final long[] ADDING_FACTORS = {1L, 10L, 100L, 1000L, 10_000L, 100_000L, 1_000_000L, 10_000_000L, 100_000_000L, 1_000_000_000L, 10_000_000_000L};

    public static double roundDouble(final double value, final int decimals) {
        if (decimals < ROUNDING_FACTORS.length) {
            return Math.round(value * ROUNDING_FACTORS[decimals]) / ROUNDING_FACTORS[decimals];
        }
        return value;
    }

    public static double convertToDouble(final long value, final int decimals) {
        return value / ROUNDING_FACTORS[decimals];
    }

    public static double convertToDouble(final double value, final int decimals) {
        return value / ROUNDING_FACTORS[decimals];
    }
}
