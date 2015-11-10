package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.KozetModell;
import org.springframework.stereotype.Repository;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("kozetModellDao")
public class KozetModellDaoImpl extends AbstractDao<Integer, KozetModell> implements KozetModellDao {
    @Override
    public KozetModell findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveKozetModell(KozetModell kozetModell) {
        persist(kozetModell);
    }

    @Override
    public void deleteKozetModell(KozetModell kozetModell) {

        delete(kozetModell);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<KozetModell> findAllKozetModells() {
        Criteria criteria = createEntityCriteria();
        return (List<KozetModell>) criteria.list();

    }
}