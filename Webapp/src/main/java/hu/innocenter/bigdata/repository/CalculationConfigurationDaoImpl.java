package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.CalculationConfiguration;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("calculationConfigurationDao")
public class CalculationConfigurationDaoImpl extends AbstractDao<Integer, CalculationConfiguration> implements CalculationConfigurationDao {
    @Override
    public CalculationConfiguration findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveCalculationConfiguration(CalculationConfiguration calculationConfiguration) {
        persist(calculationConfiguration);
    }

    @Override
    public void deleteCalculationConfiguration(CalculationConfiguration calculationConfiguration) {

        delete(calculationConfiguration);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CalculationConfiguration> findAllCalculationConfigurations() {
        Criteria criteria = createEntityCriteria();
        return (List<CalculationConfiguration>) criteria.list();

    }
}