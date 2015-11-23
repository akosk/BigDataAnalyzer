package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.CementesKozetModell;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("cementesKozetModellDao")
public class CementesKozetModellDaoImpl extends AbstractDao<Integer, CementesKozetModell> implements CementesKozetModellDao {
    @Override
    public CementesKozetModell findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveKozetModell(CementesKozetModell cementesKozetModell) {
        persist(cementesKozetModell);
    }

    @Override
    public void deleteKozetModell(CementesKozetModell cementesKozetModell) {

        delete(cementesKozetModell);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CementesKozetModell> findAllCementesKozetModells() {
        Criteria criteria = createEntityCriteria();
        return (List<CementesKozetModell>) criteria.list();

    }
}