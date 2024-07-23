package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.math.MathUtils;
import com.herron.exchange.common.api.common.math.model.Point3d;
import com.herron.exchange.common.api.common.math.parametricmodels.impliedvolsurface.model.ImpliedVolPoint;
import com.herron.exchange.common.api.common.math.parametricmodels.regression.PolynomialRegression;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;

public class SurfaceTest {

    @Test
    void test_surfaces() {
        List<CartesianPoint3d> points = List.of(
                new Point3d(0, 0, 5),
                new Point3d(1, 0, 6),
                new Point3d(2, 0, 3),
                new Point3d(3, 0, 5),

                new Point3d(0, 1, 3),
                new Point3d(1, 1, 5),
                new Point3d(2, 1, 6),
                new Point3d(3, 1, 3),

                new Point3d(0, 2, 4),
                new Point3d(1, 2, 3),
                new Point3d(2, 2, 5),
                new Point3d(3, 2, 6),

                new Point3d(0, 3, 5),
                new Point3d(1, 3, 4),
                new Point3d(2, 3, 3),
                new Point3d(3, 3, 5)
        );
        createPoints(points);
    }

    @Test
    void test_surfaces_gaussian() {
        double[] mean = {0.0, 0.0}; // Mean vector
        double[][] covarianceMatrix = {{1.0, 0.5}, {0.5, 1.0}}; // Covariance matrix
        MultivariateNormalDistribution distribution = new MultivariateNormalDistribution(mean, covarianceMatrix);
        int numSamples = 100;
        List<CartesianPoint3d> points = new ArrayList<>();
        for (int i = 0; i < numSamples; i++) {
            var sample = distribution.sample();
            points.add(
                    new Point3d(
                            MathUtils.roundDouble(sample[0], 1),
                            MathUtils.roundDouble(sample[1], 1),
                            MathUtils.roundDouble(distribution.density(sample), 3)
                    )
            );
        }
        createPoints(points);
    }

    @Test
    void test_with_iv_points() {
        String fileName = "ivpoints/ivpoints.txt"; // Change this to your file name
        var mf = new DefaultMessageFactory();
        // Use ClassLoader
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        List<CartesianPoint3d> points = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<ImpliedVolPoint> iv = objectMapper.readValue(line, new TypeReference<>() {
                });
                points = iv.stream().filter(p -> p.maturity() < 1 && (p.logMoneyness() < 0.5 && p.logMoneyness() > -0.5)).map(CartesianPoint3d.class::cast).toList();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        createPoints(points);
    }

    @Test
    void test_with_iv_points_apple() {
        var points = getPoints();
        createPoints(points);
    }

    private static void createPoints(List<CartesianPoint3d> points) {
        writeRandomDoublesToCSV("/home/christopher/test_points.csv", points);

        double[] randomX = generateRandomDoubles(
                500,
                points.stream().min(Comparator.comparing(CartesianPoint3d::x)).get().x(),
                points.stream().max(Comparator.comparing(CartesianPoint3d::x)).get().x()
        );
        double[] randomY = generateRandomDoubles(
                500,
                points.stream().min(Comparator.comparing(CartesianPoint3d::y)).get().y(),
                points.stream().max(Comparator.comparing(CartesianPoint3d::y)).get().y()
        );

        var hermite = HermiteBiCubicSurface.create(points);
        double[] z = new double[randomY.length];
        int i = 0;
        while (i < z.length) {
            z[i] = hermite.getFunctionValue(randomX[i], randomY[i]);
            i++;
        }
        writeRandomDoublesToCSV("/home/christopher/hermite.csv", randomX, randomY, z);

        int item = 0;
        double[] labels = new double[points.size()];
        double[][] predictors = new double[points.size()][2];
        for (var point : points) {
            labels[item] = point.z();
            predictors[item][0] = point.x();
            predictors[item][1] = point.y();
            item++;
        }

        var regression = PolynomialRegression.create(labels, predictors, 3);
        z = new double[randomY.length * randomX.length];
        i = 0;
        for (var x : randomX) {
            for (var y : randomY) {
                z[i] = regression.getFunctionValue(x, y);
                i++;
            }
        }
        writeRandomDoublesToCSV("/home/christopher/regression.csv", randomX, randomY, z);

        var nurbs = NurbsLeastSquaresSurface.create(points, 3);
        z = new double[randomY.length * randomX.length];
        i = 0;
        for (var x : randomX) {
            for (var y : randomY) {
                z[i] = nurbs.getFunctionValue(x, y);
                i++;
            }
        }
        writeRandomDoublesToCSV("/home/christopher/nurbs.csv", randomX, randomY, z);
    }

    private static double[] generateRandomDoubles(int count, double min, double max) {
        double[] randomDoubles = new double[count];
        Random random = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        for (int i = 0; i < count; i++) {
            randomDoubles[i] = Double.parseDouble(decimalFormat.format(min + (max - min) * random.nextDouble()));
        }

        return randomDoubles;
    }

    private static void writeRandomDoublesToCSV(String filePath, List<CartesianPoint3d> points) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("x,y,z");
            writer.write(System.lineSeparator());
            for (var point : points) {
                writer.write(String.format("%s,%s,%s", point.x(), point.y(), point.z()));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRandomDoublesToCSV(String filePath, double[] randomX, double[] randomY, double[] z) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("x,y,z");
            writer.write(System.lineSeparator());
            for (int i = 0; i < randomX.length; i++) {
                writer.write(String.format("%s,%s,%.3f", randomX[i], randomY[i], z[i]));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CartesianPoint3d> getPoints() {
        File csvFile = new File("/home/christopher/dev/options-dataset-builder/allOpt.csv"); // Replace with the path to your CSV file

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        try {
            MappingIterator<MyDataClass> it = csvMapper.readerFor(MyDataClass.class).with(schema).readValues(csvFile);
            List<MyDataClass> data = it.readAll();

            List<CartesianPoint3d> points = new ArrayList<>();
            for (MyDataClass entry : data) {
                var strike = entry.strike;
                var spot = 189.97;
                var logMoneyness = Math.log(strike / spot);
                var expiration = entry.expirationDate;
                var impliedVol = entry.impliedVolatility;
                var ttm = DAYS.between(LocalDate.of(2023, 11, 24), LocalDate.parse(expiration)) / 365.0;
                points.add(
                        new Point3d(ttm, strike, impliedVol)
                );
            }
            return points;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class MyDataClass {
        private int id;
        private String contractSymbol;
        private String lastTradeDate;
        private double strike;
        private double lastPrice;
        private double bid;
        private double ask;
        private double change;
        private double percentChange;
        private double volume;
        private int openInterest;
        private double impliedVolatility;
        private boolean inTheMoney;
        private String contractSize;
        private String currency;
        private String type;
        private String expirationDate;

        public void setId(int id) {
            this.id = id;
        }

        public void setContractSymbol(String contractSymbol) {
            this.contractSymbol = contractSymbol;
        }

        public void setLastTradeDate(String lastTradeDate) {
            this.lastTradeDate = lastTradeDate;
        }

        public void setStrike(double strike) {
            this.strike = strike;
        }

        public void setLastPrice(double lastPrice) {
            this.lastPrice = lastPrice;
        }

        public void setBid(double bid) {
            this.bid = bid;
        }

        public void setAsk(double ask) {
            this.ask = ask;
        }

        public void setChange(double change) {
            this.change = change;
        }

        public void setPercentChange(double percentChange) {
            this.percentChange = percentChange;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public void setOpenInterest(int openInterest) {
            this.openInterest = openInterest;
        }

        public void setImpliedVolatility(double impliedVolatility) {
            this.impliedVolatility = impliedVolatility;
        }

        public void setInTheMoney(boolean inTheMoney) {
            this.inTheMoney = inTheMoney;
        }

        public void setContractSize(String contractSize) {
            this.contractSize = contractSize;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }
    }
}
