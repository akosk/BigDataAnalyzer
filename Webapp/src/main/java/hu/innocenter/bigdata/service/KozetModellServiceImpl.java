package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.KozetModell;
import hu.innocenter.bigdata.repository.KozetModellDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("kozetModellService")
@Transactional
public class KozetModellServiceImpl implements KozetModellService {

    @Autowired
    private KozetModellDao dao;


    @Override
    public KozetModell findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveKozetModell(KozetModell kozetModell) {
        dao.saveKozetModell(kozetModell);
    }

    @Override
    public void updateKozetModell(KozetModell kozetModell) {
        KozetModell entity = dao.findById(kozetModell.getId());

        if (entity != null) {
            BeanUtils.copyProperties(kozetModell, entity, "id", "created");
            dao.saveKozetModell(entity);
        }

    }

    @Override
    public List<KozetModell> findAllKozetModells() {
        return dao.findAllKozetModells();
    }

}
