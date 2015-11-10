package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.MeresiEredmenyCement;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("meresiEredmenyCementDao")
public class MeresiEredmenyCementDaoImpl extends AbstractDao<Integer, MeresiEredmenyCement> implements MeresiEredmenyCementDao {
    @Override
    public MeresiEredmenyCement findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement) {
        persist(meresiEredmenyCement);
    }

    @Override
    public void deleteMeresiEredmenyCement(MeresiEredmenyCement meresiEredmenyCement) {

        delete(meresiEredmenyCement);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MeresiEredmenyCement> findAllMeresiEredmenyCements() {
        Criteria criteria = createEntityCriteria();
        return (List<MeresiEredmenyCement>) criteria.list();

    }
}