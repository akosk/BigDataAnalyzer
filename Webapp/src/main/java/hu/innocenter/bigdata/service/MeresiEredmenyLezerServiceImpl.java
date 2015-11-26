package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.MeresiEredmenyLezer;
import hu.innocenter.bigdata.repository.MeresiEredmenyLezerDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("meresiEredmenyLezerService")
@Transactional
public class MeresiEredmenyLezerServiceImpl implements MeresiEredmenyLezerService {

    @Autowired
    private MeresiEredmenyLezerDao dao;


    @Override
    public MeresiEredmenyLezer findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveMeresiEredmenyLezer(MeresiEredmenyLezer meresiEredmenyLezer) {
        dao.saveMeresiEredmenyLezer(meresiEredmenyLezer);
    }

    @Override
    public void deleteMeresiEredmenyLezer(MeresiEredmenyLezer meresiEredmenyLezer) {
        dao.deleteMeresiEredmenyLezer(meresiEredmenyLezer);
    }

    @Override
    public void updateMeresiEredmenyLezer(MeresiEredmenyLezer meresiEredmenyLezer) {
        MeresiEredmenyLezer entity = dao.findById(meresiEredmenyLezer.getId());

        if (entity != null) {
            BeanUtils.copyProperties(meresiEredmenyLezer, entity, "id", "created");
            dao.saveMeresiEredmenyLezer(entity);
        }

    }

    @Override
    public List<MeresiEredmenyLezer> findAllMeresiEredmenyLezers() {
        return dao.findAllMeresiEredmenyLezers();
    }

}
