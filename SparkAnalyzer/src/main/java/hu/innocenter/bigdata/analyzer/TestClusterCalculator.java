package hu.innocenter.bigdata.analyzer;

import java.util.HashMap;

public class TestClusterCalculator {

    public static void main(String[] args) {

        Calculator calculator = new ClusterCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();


        String fields = "cementes_kozetmodell.homok_frakcio_1," +
                "cementes_kozetmodell.homok_frakcio_2," +
                "cementes_kozetmodell.homok_frakcio_3," +
                "cementes_kozetmodell.anyag_frakcio_1," +
                "cementes_kozetmodell.belso_furat_atmero," +
                "cementes_kozetmodell.acelcso_kulso_atmero," +
                "meresi_eredmeny_cement.linearis_permeabilitas_utana ";
        String q = "SELECT " +
                fields +
                "FROM meresi_eredmeny_cement  " +
                "INNER JOIN cementes_kozetmodell ON meresi_eredmeny_cement.kozetmodell_kod=cementes_kozetmodell.kozetmodell_kod " +
                "WHERE 1=1  AND meresi_eredmeny_cement.kozetmodell_kod NOT LIKE '%R%'";


        params.put("fieldnames", fields.split(","));
        Result s = calculator.calculate("java:comp/env/jdbc/bigdata", q, params);

        System.out.println(s.getResultText());
    }

}
