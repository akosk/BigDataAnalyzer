package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.MeresiEredmenyCement;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface MeresiEredmenyCementService {

    MeresiEredmenyCement findById(int id);

    void saveMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement);

    void deleteMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement);

    void updateMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement);

    List<MeresiEredmenyCement> findAllMeresiEredmenyCements();

}
