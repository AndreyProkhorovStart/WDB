package com.train.services;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Indicators;

import com.train.merged_pojo.IndicatorWithGroup;
import com.train.repositories.IndicatorsRepository;
import org.jooq.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IndicatorsService {

    @Autowired
    private IndicatorsRepository indicatorsRepository;

    public List<IndicatorWithGroup> getAll() {
        return indicatorsRepository.getAll();
    }

    public List<IndicatorWithGroup> read(Integer id_indicators) {
        List<IndicatorWithGroup> indicators = indicatorsRepository.getByGroupId(id_indicators);
        if(indicators == null) throw new NoDataFoundException();
        return indicators;
    }
    public Integer getByCodeAndGroupId(String code, Integer id_groups){
        return indicatorsRepository.getByCodeAndGroupId(code, id_groups);
    }

    public Integer create(Indicators indicators) {
        return indicatorsRepository.insert(indicators);
    }
    public void update(Integer idIndicator) {indicatorsRepository.update(idIndicator); }
}
