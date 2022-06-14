package com.train.services;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.EconomyGrowth;
import com.train.dto.EconomyGrowthDto;

import com.train.merged_pojo.IndicatorWithCountry;
import com.train.repositories.EconomyGrowthRepository;
import org.jooq.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EconomyGrowthService {

    @Autowired
    private EconomyGrowthRepository economyGrowthRepository;

    public List<IndicatorWithCountry> getAll() {
        return economyGrowthRepository.getAll();
    }

    public List<IndicatorWithCountry> getEconomyGrowthIndicator(EconomyGrowthDto economyGrowthDto) {
        List<IndicatorWithCountry> economyGrowths;
        if (economyGrowthDto.getId_indicator() != null) {
            if (economyGrowthDto.getId_country() != null && economyGrowthDto.getStartYear() != null && economyGrowthDto.getEndYear() != null) {
                economyGrowths = economyGrowthRepository.getBetweenYearAndCountryId(economyGrowthDto.getStartYear(), economyGrowthDto.getEndYear(),economyGrowthDto.getId_country(), economyGrowthDto.getId_indicator());
            } else if (economyGrowthDto.getId_country() != null && economyGrowthDto.getYear() != null) {
                economyGrowths = economyGrowthRepository.getByYearAndCountryId(economyGrowthDto.getYear(), economyGrowthDto.getId_country(), economyGrowthDto.getId_indicator());
            } else if (economyGrowthDto.getStartYear() != null && economyGrowthDto.getEndYear() != null) {
                economyGrowths = economyGrowthRepository.getBetweenYear(economyGrowthDto.getStartYear(), economyGrowthDto.getEndYear(), economyGrowthDto.getId_indicator());
            } else if (economyGrowthDto.getYear() != null) {
                economyGrowths = economyGrowthRepository.getByYear(economyGrowthDto.getYear(), economyGrowthDto.getId_indicator());
            } else if (economyGrowthDto.getId_country() != null) {
                economyGrowths = economyGrowthRepository.getByCountryId(economyGrowthDto.getId_country(), economyGrowthDto.getId_indicator());
            } else {
                economyGrowths = economyGrowthRepository.getByIndicator(economyGrowthDto.getId_indicator());
            }
        } else {
            throw new NoDataFoundException();
        }
        if (economyGrowths.isEmpty()) {
            throw new NoDataFoundException();
        }
        return economyGrowths;
    }
    public Integer create(EconomyGrowth economyGrowth) {
        return economyGrowthRepository.insert(economyGrowth);
    }
}
