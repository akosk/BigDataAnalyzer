package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.Job;
import hu.innocenter.bigdata.repository.JobDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("jobService")
@Transactional
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao dao;


    @Override
    public Job findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveJob(Job job) {
        dao.saveJob(job);
    }

    @Override
    public void deleteJob(Job job) {
        dao.deleteJob(job);
    }

    @Override
    public void updateJob(Job job) {
        Job entity = dao.findById(job.getId());

        if (entity != null) {
            BeanUtils.copyProperties(job, entity, "id", "created");
            dao.saveJob(entity);
        }

    }

    @Override
    public List<Job> findAllJobs() {
        return dao.findAllJobs();
    }

}
