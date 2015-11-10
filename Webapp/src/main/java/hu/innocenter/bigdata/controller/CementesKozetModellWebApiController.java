package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.CementesKozetModell;
import hu.innocenter.bigdata.service.CementesKozetModellService;
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
public class CementesKozetModellWebApiController {

    @Autowired
    private CementesKozetModellService cementesKozetModellService;

    @RequestMapping(value = "/webapi-cementes-kozet-modells", method = RequestMethod.GET, produces = "application/json")
    public List<CementesKozetModell> queryKozetModell(Model model) {

        List<CementesKozetModell> cementesKozetModells = cementesKozetModellService.findAllKozetModells();
        return cementesKozetModells;

    }

    @RequestMapping(value = "/webapi-cementes-kozet-modells", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createKozetModell(@RequestBody CementesKozetModell cementesKozetModell) {

        Date now = new Date();
        cementesKozetModell.setCreated(now);
        cementesKozetModell.setUpdated(now);
        cementesKozetModellService.saveKozetModell(cementesKozetModell);
    }

    @RequestMapping(value = {"/webapi-cementes-kozet-modells/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public CementesKozetModell getKozetModell(@PathVariable Integer id, Model model) {
        CementesKozetModell cementesKozetModell = cementesKozetModellService.findById(id);
        return cementesKozetModell;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-cementes-kozet-modells/{id}"}, method = RequestMethod.DELETE)
    public void deleteKozetModell(@PathVariable Integer id, Model model) {
        CementesKozetModell cementesKozetModell = cementesKozetModellService.findById(id);
        cementesKozetModellService.deleteKozetModell(cementesKozetModell);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-cementes-kozet-modells/{id}"}, method = RequestMethod.PUT)
    public CementesKozetModell editKozetModell(@RequestBody CementesKozetModell cementesKozetModell, Model model, @PathVariable String id) {

        Date now = new Date();
        cementesKozetModell.setUpdated(now);

        cementesKozetModellService.updateKozetModell(cementesKozetModell);

        return cementesKozetModell;
    }

}
