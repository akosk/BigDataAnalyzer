package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.CementesKozetModell;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface CementesKozetModellService {

    CementesKozetModell findById(int id);

    void saveKozetModell(CementesKozetModell cementesKozetModell);

    void deleteKozetModell(CementesKozetModell cementesKozetModell);

    void updateKozetModell(CementesKozetModell cementesKozetModell);

    List<CementesKozetModell> findAllKozetModells();

}
