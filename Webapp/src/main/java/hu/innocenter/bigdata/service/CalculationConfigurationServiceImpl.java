package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.CalculationConfiguration;
import hu.innocenter.bigdata.model.KozetModell;
import hu.innocenter.bigdata.repository.CalculationConfigurationDao;
import hu.innocenter.bigdata.repository.KozetModellDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("calculationConfigurationService")
@Transactional
public class CalculationConfigurationServiceImpl implements CalculationConfigurationService {

    @Autowired
    private CalculationConfigurationDao dao;


    @Override
    public CalculationConfiguration findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void saveCalculationConfiguration(CalculationConfiguration calculationConfiguration) {
        dao.saveCalculationConfiguration(calculationConfiguration);
    }

    @Override
    public void deleteCalculationConfiguration(CalculationConfiguration calculationConfiguration) {
        dao.deleteCalculationConfiguration(calculationConfiguration);
    }

    @Override
    public void updateCalculationConfiguration(CalculationConfiguration calculationConfiguration) {
        CalculationConfiguration entity = dao.findById(calculationConfiguration.getId());

        if (entity != null) {
            BeanUtils.copyProperties(calculationConfiguration, entity, "id", "created");
            dao.saveCalculationConfiguration(entity);
        }

    }

    @Override
    public List<CalculationConfiguration> findAllCalculationConfigurations() {
        return dao.findAllCalculationConfigurations();
    }

}
