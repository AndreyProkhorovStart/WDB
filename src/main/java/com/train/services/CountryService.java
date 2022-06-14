package com.train.services;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Country;
import com.train.exceptions.CountryNotFoundException;

import com.train.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.getAll();
    }

    public Country read(Integer id) {
        Country country = countryRepository.getById(id);
        if(country == null) throw new CountryNotFoundException(id);
        return country;
    }

    public Integer getByCodeName(String codeName){
        return countryRepository.getByCodeName(codeName);
    }

    public Integer create(Country country) {
        return countryRepository.insert(country);
    }
}
