package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.MeresiEredmenyLezer;
import hu.innocenter.bigdata.service.MeresiEredmenyLezerService;
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
public class MeresiEredmenyLezerWebApiController {

    @Autowired
    private MeresiEredmenyLezerService meresiEredmenyLezerService;

    @RequestMapping(value = "/webapi-meresi-eredmeny-lezers", method = RequestMethod.GET, produces = "application/json")
    public List<MeresiEredmenyLezer> queryMeresiEredmenyLezer(Model model) {

        List<MeresiEredmenyLezer> meresiEredmenyLezers = meresiEredmenyLezerService.findAllMeresiEredmenyLezers();
        return meresiEredmenyLezers;

    }

    @RequestMapping(value = "/webapi-meresi-eredmeny-lezers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createMeresiEredmenyLezer(@RequestBody MeresiEredmenyLezer meresiEredmenyLezer) {

        Date now = new Date();
        meresiEredmenyLezer.setCreated(now);
        meresiEredmenyLezer.setUpdated(now);
        meresiEredmenyLezerService.saveMeresiEredmenyLezer(meresiEredmenyLezer);
    }

    @RequestMapping(value = {"/webapi-meresi-eredmeny-lezers/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public MeresiEredmenyLezer getMeresiEredmenyLezer(@PathVariable Integer id, Model model) {
        MeresiEredmenyLezer meresiEredmenyLezer = meresiEredmenyLezerService.findById(id);
        return meresiEredmenyLezer;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-meresi-eredmeny-lezers/{id}"}, method = RequestMethod.DELETE)
    public void deleteMeresiEredmenyLezer(@PathVariable Integer id, Model model) {
        MeresiEredmenyLezer meresiEredmenyLezer = meresiEredmenyLezerService.findById(id);
        meresiEredmenyLezerService.deleteMeresiEredmenyLezer(meresiEredmenyLezer);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-meresi-eredmeny-lezers/{id}"}, method = RequestMethod.PUT)
    public MeresiEredmenyLezer editMeresiEredmenyLezer(@RequestBody MeresiEredmenyLezer meresiEredmenyLezer, Model model, @PathVariable String id) {

        Date now = new Date();
        meresiEredmenyLezer.setUpdated(now);

        meresiEredmenyLezerService.updateMeresiEredmenyLezer(meresiEredmenyLezer);

        return meresiEredmenyLezer;
    }

}
