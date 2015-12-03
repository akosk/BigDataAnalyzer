package hu.innocenter.bigdata.workers;

import hu.innocenter.bigdata.analyzer.Calculator;
import hu.innocenter.bigdata.calculator.CalculationParametersBuilder;
import hu.innocenter.bigdata.calculator.CalculatorFactory;
import hu.innocenter.bigdata.analyzer.Result;
import hu.innocenter.bigdata.model.Job;
import hu.innocenter.bigdata.service.JobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.30..
 * akos.kiszely@gmail.com
 */

@Component
public class JobWorker {

    Logger log = Logger.getLogger(JobWorker.class);

    @Autowired
    private JobService jobService;

//    @Scheduled(fixedDelay = 4000)
    public void work() {

        List<Job> waitingJobs = jobService.findWaitingJobs();

        if (waitingJobs.size() > 0) {
            Job job = waitingJobs.get(0);

            job.setJob_start(new Date());
            job.setStatus(Job.STATUS.IN_PROGRESS);
            jobService.updateJob(job);


            Result s = null;
            try {
                s = calculate(job);
            } catch (Exception e) {
                e.printStackTrace();
            }

            job.setJob_end(new Date());
            job.setSpark_output(s.getResultText());
            job.setStatus(Job.STATUS.FINISHED);
            jobService.updateJob(job);
        }

    }

    private Result calculate(Job job) {
        Calculator calculator = CalculatorFactory.createCalculatorById(job.getCalculationConfiguration());
        CalculationParametersBuilder parametersBuilder = new CalculationParametersBuilder(job.getCalculationConfiguration());
        return calculator.calculate("java:comp/env/jdbc/bigdata", parametersBuilder.getSqlQuery(), parametersBuilder.getParams());
    }

}
