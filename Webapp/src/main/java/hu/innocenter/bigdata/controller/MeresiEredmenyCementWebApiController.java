package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.MeresiEredmenyCement;
import hu.innocenter.bigdata.service.MeresiEredmenyCementService;
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
public class MeresiEredmenyCementWebApiController {

    @Autowired
    private MeresiEredmenyCementService meresiEredmenyCementService;

    @RequestMapping(value = "/webapi-meresi-eredmeny-cements", method = RequestMethod.GET, produces = "application/json")
    public List<MeresiEredmenyCement> queryMeresiEredmenyCement(Model model) {

        List<MeresiEredmenyCement> meresiEredmenyCements = meresiEredmenyCementService.findAllMeresiEredmenyCements();
        return meresiEredmenyCements;

    }

    @RequestMapping(value = "/webapi-meresi-eredmeny-cements", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createMeresiEredmenyCement(@RequestBody MeresiEredmenyCement meresiEredmenyCement) {

        Date now = new Date();
        meresiEredmenyCement.setCreated(now);
        meresiEredmenyCement.setUpdated(now);
        meresiEredmenyCementService.saveMeresiEredmenyCement(meresiEredmenyCement);
    }

    @RequestMapping(value = {"/webapi-meresi-eredmeny-cements/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public MeresiEredmenyCement getMeresiEredmenyCement(@PathVariable Integer id, Model model) {
        MeresiEredmenyCement meresiEredmenyCement = meresiEredmenyCementService.findById(id);
        return meresiEredmenyCement;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-meresi-eredmeny-cements/{id}"}, method = RequestMethod.DELETE)
    public void deleteMeresiEredmenyCement(@PathVariable Integer id, Model model) {
        MeresiEredmenyCement meresiEredmenyCement = meresiEredmenyCementService.findById(id);
        meresiEredmenyCementService.deleteMeresiEredmenyCement(meresiEredmenyCement);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-meresi-eredmeny-cements/{id}"}, method = RequestMethod.PUT)
    public MeresiEredmenyCement editMeresiEredmenyCement(@RequestBody MeresiEredmenyCement meresiEredmenyCement, Model model, @PathVariable String id) {

        Date now = new Date();
        meresiEredmenyCement.setUpdated(now);

        meresiEredmenyCementService.updateMeresiEredmenyCement(meresiEredmenyCement);

        return meresiEredmenyCement;
    }

}
