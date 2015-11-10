package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.Fluidum;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("fluidumDao")
public class FluidumDaoImpl extends AbstractDao<Integer, Fluidum> implements FluidumDao {
    @Override
    public Fluidum findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveFluidum(Fluidum fluidum) {
        persist(fluidum);
    }

    @Override
    public void deleteFluidum(Fluidum fluidum) {

        delete(fluidum);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Fluidum> findAllFluidums() {
        Criteria criteria = createEntityCriteria();
        return (List<Fluidum>) criteria.list();

    }
}