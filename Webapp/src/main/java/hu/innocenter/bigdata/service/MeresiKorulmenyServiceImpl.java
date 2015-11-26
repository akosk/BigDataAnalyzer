package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.MeresiKorulmeny;
import hu.innocenter.bigdata.repository.MeresiKorulmenyDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("meresiKorulmenyService")
@Transactional
public class MeresiKorulmenyServiceImpl implements MeresiKorulmenyService {

    @Autowired
    private MeresiKorulmenyDao dao;


    @Override
    public MeresiKorulmeny findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny) {
        dao.saveMeresiKorulmeny(meresiKorulmeny);
    }

    @Override
    public void deleteMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny) {
        dao.deleteMeresiKorulmeny(meresiKorulmeny);
    }

    @Override
    public void updateMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny) {
        MeresiKorulmeny entity = dao.findById(meresiKorulmeny.getId());

        if (entity != null) {
            BeanUtils.copyProperties(meresiKorulmeny, entity, "id", "created");
            dao.saveMeresiKorulmeny(entity);
        }

    }

    @Override
    public List<MeresiKorulmeny> findAllMeresiKorulmenys() {
        return dao.findAllMeresiKorulmenys();
    }

}
