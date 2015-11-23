package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.LezeresKozetModell;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface LezeresKozetModellService {

    LezeresKozetModell findById(int id);

    void saveKozetModell(LezeresKozetModell lezeresKozetModell);

    void deleteKozetModell(LezeresKozetModell lezeresKozetModell);

    void updateKozetModell(LezeresKozetModell lezeresKozetModell);

    List<LezeresKozetModell> findAllKozetModells();

}
