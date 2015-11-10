package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.Fluidum;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface FluidumService {

    Fluidum findById(int id);

    void saveFluidum(Fluidum fluidum);

    void deleteFluidum(Fluidum fluidum);

    void updateFluidum(Fluidum fluidum);

    List<Fluidum> findAllFluidums();

}
