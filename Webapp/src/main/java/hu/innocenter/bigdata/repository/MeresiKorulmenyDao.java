package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.MeresiKorulmeny;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface MeresiKorulmenyDao {
    MeresiKorulmeny findById(int id);

    void saveMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny);

    void deleteMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny);

    List<MeresiKorulmeny> findAllMeresiKorulmenys();

}
