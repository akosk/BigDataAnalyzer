package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.MeresiKorulmeny;
import hu.innocenter.bigdata.service.MeresiKorulmenyService;
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
public class MeresiKorulmenyWebApiController {

    @Autowired
    private MeresiKorulmenyService meresiKorulmenyService;

    @RequestMapping(value = "/webapi-meresi-korulmenys", method = RequestMethod.GET, produces = "application/json")
    public List<MeresiKorulmeny> queryMeresiKorulmeny(Model model) {

        List<MeresiKorulmeny> meresiKorulmenys = meresiKorulmenyService.findAllMeresiKorulmenys();
        return meresiKorulmenys;

    }

    @RequestMapping(value = "/webapi-meresi-korulmenys", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createMeresiKorulmeny(@RequestBody MeresiKorulmeny meresiKorulmeny) {

        Date now = new Date();
        meresiKorulmeny.setCreated(now);
        meresiKorulmeny.setUpdated(now);
        meresiKorulmenyService.saveMeresiKorulmeny(meresiKorulmeny);
    }

    @RequestMapping(value = {"/webapi-meresi-korulmenys/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public MeresiKorulmeny getMeresiKorulmeny(@PathVariable Integer id, Model model) {
        MeresiKorulmeny meresiKorulmeny = meresiKorulmenyService.findById(id);
        return meresiKorulmeny;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-meresi-korulmenys/{id}"}, method = RequestMethod.DELETE)
    public void deleteMeresiKorulmeny(@PathVariable Integer id, Model model) {
        MeresiKorulmeny meresiKorulmeny = meresiKorulmenyService.findById(id);
        meresiKorulmenyService.deleteMeresiKorulmeny(meresiKorulmeny);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-meresi-korulmenys/{id}"}, method = RequestMethod.PUT)
    public MeresiKorulmeny editMeresiKorulmeny(@RequestBody MeresiKorulmeny meresiKorulmeny, Model model, @PathVariable String id) {

        Date now = new Date();
        meresiKorulmeny.setUpdated(now);

        meresiKorulmenyService.updateMeresiKorulmeny(meresiKorulmeny);

        return meresiKorulmeny;
    }

}
