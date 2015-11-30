package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.Job;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("jobDao")
public class JobDaoImpl extends AbstractDao<Integer, Job> implements JobDao {
    @Override
    public Job findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveJob(Job job) {
        persist(job);
    }

    @Override
    public void deleteJob(Job job) {

        delete(job);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Job> findAllJobs() {
        Criteria criteria = createEntityCriteria();
        return (List<Job>) criteria.list();

    }
}