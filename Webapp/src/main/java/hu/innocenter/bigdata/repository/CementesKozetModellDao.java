package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.CementesKozetModell;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface CementesKozetModellDao {
    CementesKozetModell findById(int id);

    void saveKozetModell(CementesKozetModell cementesKozetModell);

    void deleteKozetModell(CementesKozetModell cementesKozetModell);

    List<CementesKozetModell> findAllCementesKozetModells();

}
