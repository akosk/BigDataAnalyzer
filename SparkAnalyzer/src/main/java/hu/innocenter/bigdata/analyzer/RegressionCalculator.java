package hu.innocenter.bigdata.analyzer;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.JdbcRDD;

import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by Ákos on 2015.09.22..
 */
public class RegressionCalculator implements Calculator {

    private static SparkConf sparkConf;
    private static JavaSparkContext sc;
    private static PrintStream out = System.out;


    static class ParsePoint implements Function<LabeledPoint, Vector> {

        @Override
        public Vector call(LabeledPoint point) {

            return point.features();
        }
    }


    static JavaRDD<LabeledPoint> readDataFromDB(final String dataSource, String query) {

        System.out.println(query);

        return JdbcRDD.create(
                sc,
                new JdbcRDD.ConnectionFactory() {

                    @Override
                    public Connection getConnection() throws Exception {
                        InitialContext ctx = null;
                        Connection connection = null;
                        try {
                            ctx = new InitialContext();
                            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(dataSource);

                            connection = ds.getConnection();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (NamingException e) {
                            e.printStackTrace();
                        }
                        return connection;

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


    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {
        JavaRDD<Vector> points = null;
        JavaRDD<LabeledPoint> labeledPoints = null;

        sparkConf = new SparkConf().setAppName("JavaLR").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        StandardScaler scaler = new StandardScaler(true, true);
        out.println("%%%%%%%% SCALER: " + scaler);


        try {
            labeledPoints = readDataFromDB(dataSource, sqlQuery);
            StandardScalerModel scalerModel = scaler.fit(labeledPoints.map(new ParsePoint()).rdd());
            out.println("%%%%%%%% SCALER MODEL: " + scalerModel.withMean() + "--" + scalerModel.withStd());
            // scaler.


            points = labeledPoints.map(new ParsePoint()).cache();

            points = scalerModel.transform(points);

/*
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

            }
*/
            // Run Logistic regression
            double stepSize = 2;//Double.parseDouble(args[1]);
            int iterations = 200;//Integer.parseInt(args[2]);
            LogisticRegressionModel model = LogisticRegressionWithSGD.train(labeledPoints.rdd(), iterations, stepSize);
            out.println("Regression weights: " + model.weights());

            out.println("System trained");


            sc.stop();

        } catch (Exception e) {
            out.println("****** " + e.getMessage());

        }

        return new RegressionResult();
    }
}
