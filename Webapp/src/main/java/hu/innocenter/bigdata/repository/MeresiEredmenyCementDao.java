package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.MeresiEredmenyCement;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface MeresiEredmenyCementDao {
    MeresiEredmenyCement findById(int id);

    void saveMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement);

    void deleteMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement);

    List<MeresiEredmenyCement> findAllMeresiEredmenyCements();

}
