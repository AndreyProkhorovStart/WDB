package com.train.repositories;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.ClimateChange;
import static com.tej.JooQDemo.jooq.sample.model.tables.Country.COUNTRY;
import static com.tej.JooQDemo.jooq.sample.model.tables.ClimateChange.CLIMATE_CHANGE;
import static com.tej.JooQDemo.jooq.sample.model.tables.Indicators.INDICATORS;
import com.tej.JooQDemo.jooq.sample.model.tables.records.ClimateChangeRecord;
import com.train.merged_pojo.IndicatorWithCountry;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClimateChangeRepository {
    @Autowired
    DSLContext context;

    public List<IndicatorWithCountry> getAll(){
        return context
                .select()
                .from(CLIMATE_CHANGE)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(CLIMATE_CHANGE.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(CLIMATE_CHANGE.ID_INDICATOR))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(CLIMATE_CHANGE.ID),
                                r.get(CLIMATE_CHANGE.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(CLIMATE_CHANGE.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(CLIMATE_CHANGE.YEAR),
                                r.get(CLIMATE_CHANGE.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByIndicator(Integer climateChangeIndicator){
        return context
                .select()
                .from(CLIMATE_CHANGE)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(CLIMATE_CHANGE.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(CLIMATE_CHANGE.ID_INDICATOR))
                .where(CLIMATE_CHANGE.ID_INDICATOR.eq(climateChangeIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(CLIMATE_CHANGE.ID),
                                r.get(CLIMATE_CHANGE.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(CLIMATE_CHANGE.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(CLIMATE_CHANGE.YEAR),
                                r.get(CLIMATE_CHANGE.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByCountryId(Integer countryId, Integer climateChangeIndicator){
        return context
                .select()
                .from(CLIMATE_CHANGE)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(CLIMATE_CHANGE.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(CLIMATE_CHANGE.ID_INDICATOR))
                .where(CLIMATE_CHANGE.ID_COUNTRY.eq(countryId))
                .and(CLIMATE_CHANGE.ID_INDICATOR.eq(climateChangeIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(CLIMATE_CHANGE.ID),
                                r.get(CLIMATE_CHANGE.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(CLIMATE_CHANGE.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(CLIMATE_CHANGE.YEAR),
                                r.get(CLIMATE_CHANGE.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByYear(String year, Integer climateChangeIndicator){
        return context
                .select()
                .from(CLIMATE_CHANGE)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(CLIMATE_CHANGE.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(CLIMATE_CHANGE.ID_INDICATOR))
                .where(CLIMATE_CHANGE.YEAR.eq(year))
                .and(CLIMATE_CHANGE.ID_INDICATOR.eq(climateChangeIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(CLIMATE_CHANGE.ID),
                                r.get(CLIMATE_CHANGE.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(CLIMATE_CHANGE.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(CLIMATE_CHANGE.YEAR),
                                r.get(CLIMATE_CHANGE.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByYearAndCountryId(String year, Integer countryId, Integer climateChangeIndicator){
        return context
                .select()
                .from(CLIMATE_CHANGE)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(CLIMATE_CHANGE.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(CLIMATE_CHANGE.ID_INDICATOR))
                .where(CLIMATE_CHANGE.ID_COUNTRY.eq(countryId)).and(CLIMATE_CHANGE.YEAR.eq(year))
                .and(CLIMATE_CHANGE.ID_INDICATOR.eq(climateChangeIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(CLIMATE_CHANGE.ID),
                                r.get(CLIMATE_CHANGE.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(CLIMATE_CHANGE.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(CLIMATE_CHANGE.YEAR),
                                r.get(CLIMATE_CHANGE.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getBetweenYear(String firstYear, String secondYear, Integer climateChangeIndicator){
        return context
                .select()
                .from(CLIMATE_CHANGE)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(CLIMATE_CHANGE.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(CLIMATE_CHANGE.ID_INDICATOR))
                .where(CLIMATE_CHANGE.YEAR.between(firstYear)
                        .and(secondYear))
                .and(CLIMATE_CHANGE.ID_INDICATOR.eq(climateChangeIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(CLIMATE_CHANGE.ID),
                                r.get(CLIMATE_CHANGE.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(CLIMATE_CHANGE.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(CLIMATE_CHANGE.YEAR),
                                r.get(CLIMATE_CHANGE.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getBetweenYearAndCountryId(String firstYear, String secondYear, Integer countryId, Integer climateChangeIndicator){
        return context
                .select()
                .from(CLIMATE_CHANGE)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(CLIMATE_CHANGE.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(CLIMATE_CHANGE.ID_INDICATOR))
                .where(CLIMATE_CHANGE.YEAR.between(firstYear).and(secondYear))
                .and(CLIMATE_CHANGE.ID_COUNTRY.eq(countryId))
                .and(CLIMATE_CHANGE.ID_INDICATOR.eq(climateChangeIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(CLIMATE_CHANGE.ID),
                                r.get(CLIMATE_CHANGE.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(CLIMATE_CHANGE.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(CLIMATE_CHANGE.YEAR),
                                r.get(CLIMATE_CHANGE.VALUE)
                        )
                );
    }

    public Integer insert(ClimateChange climateChange){
        ClimateChangeRecord result = context
                        .insertInto(CLIMATE_CHANGE, CLIMATE_CHANGE.ID_INDICATOR, CLIMATE_CHANGE.ID_COUNTRY,CLIMATE_CHANGE.YEAR,CLIMATE_CHANGE.VALUE)
                        .values(
                                climateChange.getIdIndicator(),
                                climateChange.getIdCountry(),
                                climateChange.getYear(),
                                climateChange.getValue()
                        )
                        .returning(CLIMATE_CHANGE.ID)
                        .fetchOne();
        return result.getId();
    }
}
