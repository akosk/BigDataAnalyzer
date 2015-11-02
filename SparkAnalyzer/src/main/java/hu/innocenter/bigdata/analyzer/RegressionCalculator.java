package hu.innocenter.bigdata.analyzer;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.regression.GeneralizedLinearModel;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.JdbcRDD;
import scala.collection.immutable.HashMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Ákos on 2015.09.22..
 */
public class RegressionCalculator extends Calculator implements Calculator {

    private static SparkConf sparkConf;
    private static JavaSparkContext sc;
    private static PrintStream out = System.out;

    private static String url;
    private static String user;
    private static String password;
    private static String schema;

    static class ParsePoint implements Function<LabeledPoint, Vector> {

        @Override
        public Vector call(LabeledPoint point) {

            return point.features();
        }
    }


    static JavaRDD<LabeledPoint> readDataFromDB(String query) {

        System.out.println(query);

        return JdbcRDD.create(
                sc,
                new JdbcRDD.ConnectionFactory() {

                    @Override
                    public Connection getConnection() throws Exception {
                        return DriverManager.getConnection(url, user, password);
                    }
                },
                query + " AND 0 >= ? AND 2 <= ?",
                0, 2, 1,
                new Function<ResultSet, LabeledPoint>() {

                    int columnCount = 0;

                    @Override
                    public LabeledPoint call(ResultSet r) throws Exception {
                        if (columnCount == 0) {
                            columnCount = r.getMetaData().getColumnCount();
                        }

                        double y = 0.0;
                        double[] x = new double[columnCount - 1];


                        for (int i = 1; i <= columnCount; i++) {
                            if (i == 1)
                                y = r.getDouble(i);
                            else
                                x[i - 2] = r.getDouble(i);

                        }
                        LabeledPoint lp = new LabeledPoint(y, Vectors.dense(x));
                        out.println(lp.toString());
                        return lp;
                    }
                }

        ).cache();
    }


    static JavaRDD<LabeledPoint> transformPointsToPCs(RowMatrix pointsMatrix, Matrix PCs, JavaRDD<LabeledPoint> labeledPoints) {
        RowMatrix projected = pointsMatrix.multiply(PCs);

        out.println("***Rows: " + projected.numRows());
        out.println("***Cols: " + projected.numCols());
        out.println("\nProjected:");
        out.println(projected.toString());


        Iterator<Vector> iter = projected.rows().toJavaRDD().toLocalIterator();
        Iterator<LabeledPoint> iterOrig = labeledPoints.toLocalIterator();

        List<LabeledPoint> newPointsArray = new ArrayList<LabeledPoint>();

        while (iter.hasNext() && iterOrig.hasNext()) {
            newPointsArray.add(new LabeledPoint(iterOrig.next().label(), iter.next()));
        }

        return sc.parallelize(newPointsArray);
    }

    static void runPredictionTests(GeneralizedLinearModel model, JavaRDD<LabeledPoint> testPoints) {
        Iterator<LabeledPoint> testIterNew = testPoints.toLocalIterator();

        int counter = 0;
        int success = 0;
        while (testIterNew.hasNext()) {
            LabeledPoint testPoint = testIterNew.next();
            double result = model.predict(Vectors.dense(testPoint.features().toArray()));

            out.print("Testing point " + (++counter) + " - expected: " + testPoint.label() + ", result: " + result);
            if (result == testPoint.label()) {
                out.println(" OK");
                success++;
            } else {
                out.println(" FAILED");
            }
        }

        out.println("\nSuccess crate: " + success + "/" + counter);
    }

    public static void initialize(String driver, String url, String user, String password, String schema) throws ClassNotFoundException {
        initialize(driver, url, user, password, schema, null);
    }

    public static void initialize(String driver, String url, String user, String password, String schema, PrintStream s) throws ClassNotFoundException {

        if (s != null) {
            out = s;
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load database driver");
            throw e;
        }

        RegressionCalculator.url = url;
        RegressionCalculator.user = user;
        RegressionCalculator.password = password;
        RegressionCalculator.schema = schema;
    }


    @Override
    public Result calculate(Connection connection, String sqlQuery, HashMap<String, Object> params) {
        throw new NotImplementedException();
    }


    public Result calculate(String baseQuery, String testQuery, String algorithm, int pcaNum) {
        JavaRDD<Vector> points = null;
        JavaRDD<LabeledPoint> labeledPoints = null;
        JavaRDD<Vector> testPoints = null;
        JavaRDD<LabeledPoint> testLabeledPoints = null;

        sparkConf = new SparkConf().setAppName("JavaLR").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        StandardScaler scaler = new StandardScaler(true, true);
        out.println("%%%%%%%% SCALER: " + scaler);


        try {
            labeledPoints = readDataFromDB(baseQuery);
            StandardScalerModel scalerModel = scaler.fit(labeledPoints.map(new ParsePoint()).rdd());
            out.println("%%%%%%%% SCALER MODEL: " + scalerModel.withMean() + "--" + scalerModel.withStd());
            // scaler.


            points = labeledPoints.map(new ParsePoint()).cache();

            points = scalerModel.transform(points);
            if (testQuery != null && testQuery != "") {
                testLabeledPoints = readDataFromDB(testQuery);
                testPoints = testLabeledPoints.map(new ParsePoint()).cache();
            }


            if (pcaNum > 0) { // Run Principal Component Analysis
                // Create a RowMatrix from JavaRDD<Vector>.
                RowMatrix mat = new RowMatrix(points.rdd());
                //out.println("***Rows: " + mat.numRows());
                //out.println("***Cols: " + mat.numCols());
                // Compute the top pcaNum principal components.
                Matrix pc = mat.computePrincipalComponents(pcaNum);

                //out.println("***Rows: " + pc.numRows());
                //out.println("***Cols: " + pc.numCols());

                out.println("Principal components:");
                out.println(pc.toString());

                labeledPoints = transformPointsToPCs(mat, pc, labeledPoints);

                if (testQuery != null && testQuery != "") {
                    RowMatrix testMat = new RowMatrix(testPoints.rdd());
                    RowMatrix testProjected = testMat.multiply(pc);


                    testLabeledPoints = transformPointsToPCs(testMat, pc, testLabeledPoints);
                }
            }

            if ("LR".equals(algorithm)) {
                double stepSize = 2;//Double.parseDouble(args[1]);
                int iterations = 200;//Integer.parseInt(args[2]);
                LogisticRegressionModel model = LogisticRegressionWithSGD.train(labeledPoints.rdd(), iterations, stepSize);
                out.println("Regression weights: " + model.weights());

                out.println("System trained");
            }
            if ("KMEANS".equals(algorithm)) {
                int k = 2;
                int iterations = 3;
                int runs = 10;

                KMeansModel model;
                model = KMeans.train(points.rdd(), k, iterations, runs, KMeans.K_MEANS_PARALLEL());

                out.println("Cluster centers:");
                for (Vector center : model.clusterCenters()) {
                    out.println(" " + center);
                }

                double cost = model.computeCost(points.rdd());
                out.println("Cost: " + cost);

                out.println("System trained");
            }
            if (testQuery != null && testQuery != "")
                //      runPredictionTests ( model, testLabeledPoints);

                sc.stop();

        } catch (Exception e) {
            out.println("****** " + e.getMessage());

        }

        return new RegressionResult();
    }
}
