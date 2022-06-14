package com.train.dto.converters;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.EconomyGrowth;
import com.train.dto.EconomyGrowthDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EconomyGrowthConverter {
    public EconomyGrowthDto toDto(EconomyGrowth economyGrowth) {
        return new EconomyGrowthDto(
                economyGrowth.getIdCountry(),
                economyGrowth.getYear(),
                economyGrowth.getValue(),
                economyGrowth.getIdIndicator()
        );
    }

    public EconomyGrowth toModel(EconomyGrowthDto economyGrowthDto) {
        var economyGrowth = new EconomyGrowth();
        economyGrowth.setYear(economyGrowthDto.getYear());
        economyGrowth.setValue(economyGrowthDto.getValue());
        economyGrowth.setIdCountry(economyGrowthDto.getId_country());
        economyGrowth.setIdIndicator(economyGrowthDto.getId_indicator());
        return economyGrowth;
    }
}