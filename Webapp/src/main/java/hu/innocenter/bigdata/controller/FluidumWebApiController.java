package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.Fluidum;
import hu.innocenter.bigdata.model.Fluidum;
import hu.innocenter.bigdata.service.FluidumService;
import hu.innocenter.bigdata.service.FluidumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */

@RestController
public class FluidumWebApiController {

    @Autowired
    private FluidumService fluidumService;

    @RequestMapping(value = "/webapi-fluidums", method = RequestMethod.GET, produces = "application/json")
    public List<Fluidum> queryFluidum(Model model) {

        List<Fluidum> fluidums = fluidumService.findAllFluidums();
        return fluidums;

    }

    @RequestMapping(value = "/webapi-fluidums", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createFluidum(@RequestBody Fluidum fluidum) {

        Date now = new Date();
        fluidum.setCreated(now);
        fluidum.setUpdated(now);
        fluidumService.saveFluidum(fluidum);
    }

    @RequestMapping(value = {"/webapi-fluidums/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public Fluidum getFluidum(@PathVariable Integer id, Model model) {
        Fluidum fluidum = fluidumService.findById(id);
        return fluidum;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-fluidums/{id}"}, method = RequestMethod.DELETE)
    public void deleteFluidum(@PathVariable Integer id, Model model) {
        Fluidum fluidum = fluidumService.findById(id);
        fluidumService.deleteFluidum(fluidum);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-fluidums/{id}"}, method = RequestMethod.PUT)
    public Fluidum editFluidum(@RequestBody Fluidum fluidum, Model model, @PathVariable String id) {

        Date now = new Date();
        fluidum.setUpdated(now);

        fluidumService.updateFluidum(fluidum);

        return fluidum;
    }

}
