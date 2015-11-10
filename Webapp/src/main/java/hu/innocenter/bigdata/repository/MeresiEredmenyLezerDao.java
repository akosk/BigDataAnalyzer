package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.MeresiEredmenyLezer;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface MeresiEredmenyLezerDao {
    MeresiEredmenyLezer findById(int id);

    void saveMeresiEredmenyLezer(MeresiEredmenyLezer meresiEredmenyLezer);

    void deleteMeresiEredmenyLezer(MeresiEredmenyLezer meresiEredmenyLezer);

    List<MeresiEredmenyLezer> findAllMeresiEredmenyLezers();

}
