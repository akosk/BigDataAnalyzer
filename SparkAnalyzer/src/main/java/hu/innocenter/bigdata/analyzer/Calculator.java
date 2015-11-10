package hu.innocenter.bigdata.analyzer;

import java.io.PrintStream;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.JdbcRDD;

/**
 * Created by Ákos Kiszely on 2015.11.02..
 * akos.kiszely@gmail.com
 */
public abstract class Calculator {
    protected static SparkConf sparkConf;
    protected static JavaSparkContext sc;
    protected static PrintStream out = System.out;

    protected static class DoubleComparator implements Comparator<Double>, Serializable {
        @Override
        public int compare(Double d1, Double d2) {
            return d1.compareTo(d2);
        }
    }

    public abstract Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params);

    static class ParsePoint implements Function<LabeledPoint, Vector> {

        @Override
        public Vector call(LabeledPoint point) {

            return point.features();
        }
    }
    
    static class ParseLabel implements Function<LabeledPoint, Double> {

        @Override
        public Double call(LabeledPoint point) {

            return point.label();
        }
    }

    static class ResultSetToLabeledPoint implements Function<ResultSet, LabeledPoint> {

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
    
    static class ResultSetToDouble implements Function<ResultSet, Double> {

        @Override
        public Double call(ResultSet r) throws Exception {
            double y = 0.0;
            y = r.getDouble(1);

            out.println(y);
            return y;
        }
    }

    static JavaRDD<Double> readDoublesFromDB(final String dataSource, String query) {

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
                new ResultSetToDouble()
        ).cache();
    }
    
    static JavaRDD<LabeledPoint> readLabeledPointsFromDB(final String dataSource, String query) {

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
                new ResultSetToLabeledPoint()

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

}
