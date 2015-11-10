package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.KozetModell;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface KozetModellService {

    KozetModell findById(int id);

    void saveKozetModell(KozetModell kozetModell);

    void deleteKozetModell(KozetModell kozetModell);

    void updateKozetModell(KozetModell kozetModell);

    List<KozetModell> findAllKozetModells();

}
