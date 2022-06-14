package com.train.services;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.ClimateChange;
import com.train.dto.ClimateChangeDto;
import com.train.merged_pojo.IndicatorWithCountry;
import com.train.repositories.ClimateChangeRepository;
import org.jooq.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClimateChangeService {

    @Autowired
    private ClimateChangeRepository climateChangeRepository;

    public List<IndicatorWithCountry> getAll() {
        return climateChangeRepository.getAll();
    }

    public List<IndicatorWithCountry> getClimateChangeIndicator(ClimateChangeDto climateChangeDto) {
        List<IndicatorWithCountry> climateChanges;
        if (climateChangeDto.getId_indicator() != null) {
            if (climateChangeDto.getId_country() != null && climateChangeDto.getStartYear() != null && climateChangeDto.getEndYear() != null) {
                climateChanges = climateChangeRepository.getBetweenYearAndCountryId(climateChangeDto.getStartYear(), climateChangeDto.getEndYear(),climateChangeDto.getId_country(), climateChangeDto.getId_indicator());
            } else if (climateChangeDto.getId_country() != null && climateChangeDto.getYear() != null) {
                climateChanges = climateChangeRepository.getByYearAndCountryId(climateChangeDto.getYear(), climateChangeDto.getId_country(), climateChangeDto.getId_indicator());
            } else if (climateChangeDto.getStartYear() != null && climateChangeDto.getEndYear() != null) {
                climateChanges = climateChangeRepository.getBetweenYear(climateChangeDto.getStartYear(), climateChangeDto.getEndYear(), climateChangeDto.getId_indicator());
            } else if (climateChangeDto.getYear() != null) {
                climateChanges = climateChangeRepository.getByYear(climateChangeDto.getYear(), climateChangeDto.getId_indicator());
            } else if (climateChangeDto.getId_country() != null) {
                climateChanges = climateChangeRepository.getByCountryId(climateChangeDto.getId_country(), climateChangeDto.getId_indicator());
            } else {
                climateChanges = climateChangeRepository.getByIndicator(climateChangeDto.getId_indicator());
            }
        } else {
            throw new NoDataFoundException();
        }
        if (climateChanges.isEmpty()) {
            throw new NoDataFoundException();
        }
        return climateChanges;
    }

    public Integer create(ClimateChange climateChange) {
        return climateChangeRepository.insert(climateChange);
    }
}
