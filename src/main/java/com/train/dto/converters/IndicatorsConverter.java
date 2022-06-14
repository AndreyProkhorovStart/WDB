package com.train.dto.converters;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Indicators;
import com.train.dto.IndicatorsDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IndicatorsConverter {
    public IndicatorsDto toDto(Indicators indicators) {
        return new IndicatorsDto(
                indicators.getIdGroup(),
                indicators.getCode(),
                indicators.getName()
        );
    }

    public Indicators toModel(IndicatorsDto indicatorsDto) {
        var indicators = new Indicators();
        indicators.setIdGroup(indicators.getIdGroup());
        indicators.setCode(indicators.getCode());
        indicators.setName(indicatorsDto.getName());
        return indicators;
    }
}
