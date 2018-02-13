package hu.innocenter.bigdata.analyzer;

import java.util.HashMap;

/**
 * Created by akosk on 2016.02.05..
 */
public class TestJ {

    public static void main(String[] args) {
//        Calculator calculator = new ScalaLinearRegressionCalculatorTest();
        Calculator calculator = new ScalaLinearRegressionCalculatorLBFGS();
        HashMap<String, Object> params = new HashMap<String, Object>();


        String fields = "cementes_kozetmodell.homok_frakcio_1," +
                "cementes_kozetmodell.homok_frakcio_2," +
                "cementes_kozetmodell.homok_frakcio_3," +
                "cementes_kozetmodell.anyag_frakcio_1," +
                "cementes_kozetmodell.belso_furat_atmero," +
                "cementes_kozetmodell.acelcso_kulso_atmero," +
                "meresi_eredmeny_cement.linearis_permeabilitas_utana";
////        String q = "SELECT " + fields + " From raw_edit a WHERE 1=1 ";
        String q = "SELECT cementes_kozetmodell.homok_frakcio_1,cementes_kozetmodell.homok_frakcio_2,cementes_kozetmodell.homok_frakcio_3,cementes_kozetmodell.anyag_frakcio_1,cementes_kozetmodell.belso_furat_atmero,cementes_kozetmodell.acelcso_kulso_atmero,meresi_eredmeny_cement.linearis_permeabilitas_utana FROM meresi_eredmeny_cement  INNER JOIN cementes_kozetmodell ON meresi_eredmeny_cement.kozetmodell_kod=cementes_kozetmodell.kozetmodell_kod WHERE 1=1  AND meresi_eredmeny_cement.kozetmodell_kod NOT LIKE '%R%'";

//        String fields = "a.x1," +
//                "a.x2," +
//                "a.x3," +
//                "a.x4," +
//                "a.x5," +
//                "a.y";
//
//        String q = "SELECT " + fields + " From basic_lreg a WHERE 1=1 ";
//        String q = "SELECT cementes_kozetmodell.homok_frakcio_1,cementes_kozetmodell.homok_frakcio_2,cementes_kozetmodell.homok_frakcio_3,cementes_kozetmodell.anyag_frakcio_1,cementes_kozetmodell.belso_furat_atmero,cementes_kozetmodell.acelcso_kulso_atmero,meresi_eredmeny_cement.linearis_permeabilitas_utana FROM meresi_eredmeny_cement  INNER JOIN cementes_kozetmodell ON meresi_eredmeny_cement.kozetmodell_kod=cementes_kozetmodell.kozetmodell_kod WHERE 1=1  AND meresi_eredmeny_cement.kozetmodell_kod NOT LIKE '%R%'";

        params.put("fieldnames", fields.split(","));
        params.put("principal", "true");
        params.put("princpal_components", "2");
        Result s = calculator.calculate("java:comp/env/jdbc/bigdata", q, params);


        System.out.println(s.getResultText());


    }
}
