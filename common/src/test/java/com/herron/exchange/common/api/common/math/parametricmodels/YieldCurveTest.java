package com.herron.exchange.common.api.common.math.parametricmodels;

import com.herron.exchange.common.api.common.enums.DayCountConventionEnum;
import com.herron.exchange.common.api.common.enums.InterpolationMethod;
import com.herron.exchange.common.api.common.math.parametricmodels.yieldcurve.YieldCurve;
import com.herron.exchange.common.api.common.math.parametricmodels.yieldcurve.model.YieldCurveModelParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.herron.exchange.common.api.common.testutils.TestAssertionUtils.assertDouble;


class YieldCurveTest {

    @Test
    void testYieldCurve5Points() {
        LocalDate startDate = LocalDate.parse("2000-01-01");
        var dayCountConvention = DayCountConventionEnum.ACT365;
        List<LocalDate> maturityDates = new ArrayList<>();
        maturityDates.add(startDate.plusDays((long) dayCountConvention.getDaysPerYear()));
        maturityDates.add(startDate.plusDays((long) dayCountConvention.getDaysPerYear() * 2));
        maturityDates.add(startDate.plusDays((long) dayCountConvention.getDaysPerYear() * 3));
        maturityDates.add(startDate.plusDays((long) dayCountConvention.getDaysPerYear() * 4));
        maturityDates.add(startDate.plusDays((long) dayCountConvention.getDaysPerYear() * 5));
        double[] yields = new double[]{1, 5, 4, 3, 2};
        var parameters = YieldCurveModelParameters.create(dayCountConvention,
                InterpolationMethod.CUBIC_SPLINE,
                LocalDate.parse("2000-01-01"),
                maturityDates.get(0),
                maturityDates.toArray(new LocalDate[0]),
                yields
        );
        YieldCurve curve = YieldCurve.create("id", parameters);
        for (int i = 0; i < 10000; i++) {
            double x = ThreadLocalRandom.current().nextDouble(1, 3);
            assertDouble(YieldCurveTestUtils.getFunctionValue5Points(x), curve.getYield(x), 0.0000001);
        }
    }

}