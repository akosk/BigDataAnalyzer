package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.CalculationConfiguration;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface CalculationConfigurationService {

    CalculationConfiguration findById(int id);

    void saveCalculationConfiguration(CalculationConfiguration calculationConfiguration);

    void deleteCalculationConfiguration(CalculationConfiguration calculationConfiguration);

    void updateCalculationConfiguration(CalculationConfiguration calculationConfiguration);

    List<CalculationConfiguration> findAllCalculationConfigurations();

}
