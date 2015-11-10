package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.LezeresKozetModell;
import hu.innocenter.bigdata.service.LezeresKozetModellService;
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
public class LezeresKozetModellWebApiController {

    @Autowired
    private LezeresKozetModellService lezeresKozetModellService;

    @RequestMapping(value = "/webapi-lezeres-kozet-modells", method = RequestMethod.GET, produces = "application/json")
    public List<LezeresKozetModell> queryKozetModell(Model model) {

        List<LezeresKozetModell> lezeresKozetModells = lezeresKozetModellService.findAllKozetModells();
        return lezeresKozetModells;

    }

    @RequestMapping(value = "/webapi-lezeres-kozet-modells", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createKozetModell(@RequestBody LezeresKozetModell lezeresKozetModell) {

        Date now = new Date();
        lezeresKozetModell.setCreated(now);
        lezeresKozetModell.setUpdated(now);
        lezeresKozetModellService.saveKozetModell(lezeresKozetModell);
    }

    @RequestMapping(value = {"/webapi-lezeres-kozet-modells/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public LezeresKozetModell getKozetModell(@PathVariable Integer id, Model model) {
        LezeresKozetModell lezeresKozetModell = lezeresKozetModellService.findById(id);
        return lezeresKozetModell;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-lezeres-kozet-modells/{id}"}, method = RequestMethod.DELETE)
    public void deleteKozetModell(@PathVariable Integer id, Model model) {
        LezeresKozetModell lezeresKozetModell = lezeresKozetModellService.findById(id);
        lezeresKozetModellService.deleteKozetModell(lezeresKozetModell);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-lezeres-kozet-modells/{id}"}, method = RequestMethod.PUT)
    public LezeresKozetModell editKozetModell(@RequestBody LezeresKozetModell lezeresKozetModell, Model model, @PathVariable String id) {

        Date now = new Date();
        lezeresKozetModell.setUpdated(now);

        lezeresKozetModellService.updateKozetModell(lezeresKozetModell);

        return lezeresKozetModell;
    }

}
