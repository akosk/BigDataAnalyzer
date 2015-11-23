package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.LezeresKozetModell;
import org.springframework.stereotype.Repository;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

import java.util.List;

import org.hibernate.Criteria;

@Repository("lezeresKozetModellDao")
public class LezeresKozetModellDaoImpl extends AbstractDao<Integer, LezeresKozetModell> implements LezeresKozetModellDao {
    @Override
    public LezeresKozetModell findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveKozetModell(LezeresKozetModell lezeresKozetModell) {
        persist(lezeresKozetModell);
    }

    @Override
    public void deleteKozetModell(LezeresKozetModell lezeresKozetModell) {

        delete(lezeresKozetModell);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LezeresKozetModell> findAllLezeresKozetModells() {
        Criteria criteria = createEntityCriteria();
        return (List<LezeresKozetModell>) criteria.list();

    }
}