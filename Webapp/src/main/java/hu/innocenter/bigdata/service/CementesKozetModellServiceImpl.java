package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.CementesKozetModell;
import hu.innocenter.bigdata.repository.CementesKozetModellDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("cementesKozetModellService")
@Transactional
public class CementesKozetModellServiceImpl implements CementesKozetModellService {

    @Autowired
    private CementesKozetModellDao dao;


    @Override
    public CementesKozetModell findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveKozetModell(CementesKozetModell cementesKozetModell) {
        dao.saveKozetModell(cementesKozetModell);
    }

    @Override
    public void deleteKozetModell(CementesKozetModell cementesKozetModell) {
        dao.deleteKozetModell(cementesKozetModell);
    }

    @Override
    public void updateKozetModell(CementesKozetModell cementesKozetModell) {
        CementesKozetModell entity = dao.findById(cementesKozetModell.getId());

        if (entity != null) {
            BeanUtils.copyProperties(cementesKozetModell, entity, "id", "created");
            dao.saveKozetModell(entity);
        }

    }

    @Override
    public List<CementesKozetModell> findAllKozetModells() {
        return dao.findAllCementesKozetModells();
    }

}
