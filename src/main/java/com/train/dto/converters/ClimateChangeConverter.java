package com.train.dto.converters;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.ClimateChange;
import com.train.dto.ClimateChangeDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClimateChangeConverter {
    public ClimateChangeDto toDto(ClimateChange climateChange) {
        return new ClimateChangeDto(
                climateChange.getIdCountry(),
                climateChange.getYear(),
                climateChange.getValue(),
                climateChange.getIdIndicator()
        );
    }

    public ClimateChange toModel(ClimateChangeDto climateChangeDto) {
        var climateChange = new ClimateChange();
        climateChange.setYear(climateChangeDto.getYear());
        climateChange.setValue(climateChangeDto.getValue());
        climateChange.setIdCountry(climateChangeDto.getId_country());
        climateChange.setIdIndicator(climateChangeDto.getId_indicator());
        return climateChange;
    }
}
