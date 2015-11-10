package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.KozetModell;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface KozetModellDao {
    KozetModell findById(int id);

    void saveKozetModell(KozetModell kozetModell);

    void deleteKozetModell(KozetModell kozetModell);

    List<KozetModell> findAllKozetModells();

}
