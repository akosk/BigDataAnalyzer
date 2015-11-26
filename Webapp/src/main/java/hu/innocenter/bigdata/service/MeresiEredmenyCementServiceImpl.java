package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.MeresiEredmenyCement;
import hu.innocenter.bigdata.repository.MeresiEredmenyCementDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("meresiEredmenyCementService")
@Transactional
public class MeresiEredmenyCementServiceImpl implements MeresiEredmenyCementService {

    @Autowired
    private MeresiEredmenyCementDao dao;


    @Override
    public MeresiEredmenyCement findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement) {
        dao.saveMeresiEredmenyCement(meresiEredmenyCement);
    }

    @Override
    public void deleteMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement) {
        dao.deleteMeresiEredmenyCement(meresiEredmenyCement);
    }

    @Override
    public void updateMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement) {
        MeresiEredmenyCement entity = dao.findById(meresiEredmenyCement.getId());

        if (entity != null) {
            BeanUtils.copyProperties(meresiEredmenyCement, entity, "id", "created");
            dao.saveMeresiEredmenyCement(entity);
        }

    }

    @Override
    public List<MeresiEredmenyCement> findAllMeresiEredmenyCements() {
        return dao.findAllMeresiEredmenyCements();
    }

}
