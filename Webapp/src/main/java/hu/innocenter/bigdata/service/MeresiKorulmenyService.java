package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.MeresiKorulmeny;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface MeresiKorulmenyService {

    MeresiKorulmeny findById(int id);

    void saveMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny);

    void deleteMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny);

    void updateMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny);

    List<MeresiKorulmeny> findAllMeresiKorulmenys();

}
