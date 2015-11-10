package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.CementesKozetModell;
import hu.innocenter.bigdata.model.Fluidum;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface FluidumDao {
    Fluidum findById(int id);

    void saveFluidum(Fluidum fludium);

    void deleteFluidum(Fluidum fluidum);

    List<Fluidum> findAllFluidums();

}
