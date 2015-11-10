package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.Fluidum;
import hu.innocenter.bigdata.repository.FluidumDao;
import hu.innocenter.bigdata.repository.FluidumDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("fluidumService")
@Transactional
public class FluidumServiceImpl implements FluidumService {

    @Autowired
    private FluidumDao dao;


    @Override
    public Fluidum findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveFluidum(Fluidum fluidum) {
        dao.saveFluidum(fluidum);
    }

    @Override
    public void deleteFluidum(Fluidum fluidum) {
        dao.deleteFluidum(fluidum);
    }

    @Override
    public void updateFluidum(Fluidum fluidum) {
        Fluidum entity = dao.findById(fluidum.getId());

        if (entity != null) {
            BeanUtils.copyProperties(fluidum, entity, "id", "created");
            dao.saveFluidum(entity);
        }

    }

    @Override
    public List<Fluidum> findAllFluidums() {
        return dao.findAllFluidums();
    }

}
