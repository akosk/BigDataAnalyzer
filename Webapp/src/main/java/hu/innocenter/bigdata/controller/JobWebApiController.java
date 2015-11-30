package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.CalculationConfiguration;
import hu.innocenter.bigdata.model.Job;
import hu.innocenter.bigdata.service.CalculationConfigurationService;
import hu.innocenter.bigdata.service.JobService;
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
public class JobWebApiController {

    @Autowired
    private JobService jobService;
    @Autowired
    private CalculationConfigurationService calculationConfigurationService;

    @RequestMapping(value = "/webapi-jobs", method = RequestMethod.GET, produces = "application/json")
    public List<Job> queryJob(Model model) {

        List<Job> jobs = jobService.findAllJobs();
        for (Job job : jobs) {
            job.setCalculation_configuration_name(job.getCalculationConfiguration().getName());
        }
        return jobs;

    }

    @RequestMapping(value = "/webapi-jobs", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createJob(@RequestBody Job job) {

        CalculationConfiguration config = calculationConfigurationService.findById(job.getCalculation_configuration_id());
        job.setCalculationConfiguration(config);
        Date now = new Date();
        job.setStatus(Job.STATUS.WAITING);
        job.setCreated(now);
        job.setUpdated(now);
        jobService.saveJob(job);
    }

    @RequestMapping(value = {"/webapi-jobs/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public Job getJob(@PathVariable Integer id, Model model) {
        Job job = jobService.findById(id);
        return job;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-jobs/{id}"}, method = RequestMethod.DELETE)
    public void deleteJob(@PathVariable Integer id, Model model) {
        Job job = jobService.findById(id);
        jobService.deleteJob(job);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-jobs/{id}"}, method = RequestMethod.PUT)
    public Job editJob(@RequestBody Job job, Model model, @PathVariable String id) {

        Date now = new Date();
        job.setUpdated(now);

        jobService.updateJob(job);

        return job;
    }

}
