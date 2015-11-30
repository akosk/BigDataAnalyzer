package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.Job;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface JobDao {
    Job findById(int id);

    void saveJob(Job fludium);

    void deleteJob(Job job);

    List<Job> findAllJobs();

}
