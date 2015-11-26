package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.MeresiKorulmeny;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("meresiKorulmenyDao")
public class MeresiKorulmenyDaoImpl extends AbstractDao<Integer, MeresiKorulmeny> implements MeresiKorulmenyDao {
    @Override
    public MeresiKorulmeny findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny) {
        persist(meresiKorulmeny);
    }

    @Override
    public void deleteMeresiKorulmeny(MeresiKorulmeny meresiKorulmeny) {

        delete(meresiKorulmeny);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MeresiKorulmeny> findAllMeresiKorulmenys() {
        Criteria criteria = createEntityCriteria();
        return (List<MeresiKorulmeny>) criteria.list();

    }
}