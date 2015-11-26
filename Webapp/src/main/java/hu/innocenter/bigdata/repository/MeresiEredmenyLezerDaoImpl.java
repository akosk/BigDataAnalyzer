package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.MeresiEredmenyLezer;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("meresiEredmenyLezerDao")
public class MeresiEredmenyLezerDaoImpl extends AbstractDao<Integer, MeresiEredmenyLezer> implements MeresiEredmenyLezerDao {
    @Override
    public MeresiEredmenyLezer findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveMeresiEredmenyLezer(MeresiEredmenyLezer meresiEredmenyLezer) {
        persist(meresiEredmenyLezer);
    }

    @Override
    public void deleteMeresiEredmenyLezer(MeresiEredmenyLezer meresiEredmenyLezer) {

        delete(meresiEredmenyLezer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MeresiEredmenyLezer> findAllMeresiEredmenyLezers() {
        Criteria criteria = createEntityCriteria();
        return (List<MeresiEredmenyLezer>) criteria.list();

    }
}