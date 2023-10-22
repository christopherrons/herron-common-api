package com.herron.exchange.common.api.common.curves;

import com.herron.exchange.common.api.common.enums.DayCountConventionEnum;
import com.herron.exchange.common.api.common.enums.InterpolationMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public record YieldCurveModelParameters(DayCountConventionEnum dayCountConventionEnum,
                                        InterpolationMethod interpolationMethod,
                                        LocalDate startDate,
                                        LocalDate endDate,
                                        List<YieldPoint> yieldPoints) {

    public static YieldCurveModelParameters create(DayCountConventionEnum dayCountConventionEnum,
                                                   InterpolationMethod interpolationMethod,
                                                   LocalDate startDate,
                                                   LocalDate endDate,
                                                   LocalDate[] maturityDates,
                                                   double[] yields) {

        return new YieldCurveModelParameters(dayCountConventionEnum, interpolationMethod, startDate, endDate, createYieldPoints(dayCountConventionEnum, startDate, maturityDates, yields));
    }

    private static List<YieldPoint> createYieldPoints(DayCountConventionEnum dayCountConventionEnum,
                                                      LocalDate startDate,
                                                      LocalDate[] maturityDates,
                                                      double[] yields) {
        List<YieldPoint> yieldPoints = new ArrayList<>();
        for (int i = 0; i < yields.length; i++) {
            var maturity = DAYS.between(startDate, maturityDates[i]) / dayCountConventionEnum.getDaysPerYear();
            yieldPoints.add(new YieldPoint(maturity, yields[i]));
        }
        return yieldPoints;
    }
}
