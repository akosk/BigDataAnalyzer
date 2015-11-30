package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.Job;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface JobService {

    Job findById(int id);

    void saveJob(Job job);

    void deleteJob(Job job);

    void updateJob(Job job);

    List<Job> findAllJobs();

    List<Job> findWaitingJobs();

}
