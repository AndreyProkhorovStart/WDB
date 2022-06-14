package com.train.dto.converters;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Country;
import com.train.dto.CountryDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CountryConverter {
    public CountryDto toDto(Country country) {
        return new CountryDto(
                country.getCodeName(),
                country.getFullName()
        );
    }

    public Country toModel(CountryDto countryDto) {
        var country = new Country();
        country.setCodeName(countryDto.getCode_name());
            country.setFullName(countryDto.getCode_name());
        return country;
    }
}
