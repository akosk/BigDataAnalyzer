package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.LezeresKozetModell;
import hu.innocenter.bigdata.repository.LezeresKozetModellDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("lezeresKozetModellService")
@Transactional
public class LezeresKozetModellServiceImpl implements LezeresKozetModellService {

    @Autowired
    private LezeresKozetModellDao dao;


    @Override
    public LezeresKozetModell findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveKozetModell(LezeresKozetModell lezeresKozetModell) {
        dao.saveKozetModell(lezeresKozetModell);
    }

    @Override
    public void deleteKozetModell(LezeresKozetModell lezeresKozetModell) {
        dao.deleteKozetModell(lezeresKozetModell);
    }

    @Override
    public void updateKozetModell(LezeresKozetModell lezeresKozetModell) {
        LezeresKozetModell entity = dao.findById(lezeresKozetModell.getId());

        if (entity != null) {
            BeanUtils.copyProperties(lezeresKozetModell, entity, "id", "created");
            dao.saveKozetModell(entity);
        }

    }

    @Override
    public List<LezeresKozetModell> findAllKozetModells() {
        return dao.findAllLezeresKozetModells();
    }

}
