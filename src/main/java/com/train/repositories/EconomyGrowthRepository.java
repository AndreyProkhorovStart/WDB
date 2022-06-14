package com.train.repositories;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.EconomyGrowth;
import static com.tej.JooQDemo.jooq.sample.model.tables.EconomyGrowth.ECONOMY_GROWTH;
import static com.tej.JooQDemo.jooq.sample.model.tables.Country.COUNTRY;
import static com.tej.JooQDemo.jooq.sample.model.tables.Indicators.INDICATORS;
import com.tej.JooQDemo.jooq.sample.model.tables.records.EconomyGrowthRecord;
import com.train.merged_pojo.IndicatorWithCountry;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EconomyGrowthRepository {
    @Autowired
    DSLContext context;

    public List<IndicatorWithCountry> getAll(){
        return context
                .select()
                .from(ECONOMY_GROWTH)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(ECONOMY_GROWTH.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(ECONOMY_GROWTH.ID_INDICATOR))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(ECONOMY_GROWTH.ID),
                                r.get(ECONOMY_GROWTH.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(ECONOMY_GROWTH.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(ECONOMY_GROWTH.YEAR),
                                r.get(ECONOMY_GROWTH.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByIndicator(Integer economyGrowthIndicator){
        return context
                .select()
                .from(ECONOMY_GROWTH)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(ECONOMY_GROWTH.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(ECONOMY_GROWTH.ID_INDICATOR))
                .where(ECONOMY_GROWTH.ID_INDICATOR.eq(economyGrowthIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(ECONOMY_GROWTH.ID),
                                r.get(ECONOMY_GROWTH.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(ECONOMY_GROWTH.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(ECONOMY_GROWTH.YEAR),
                                r.get(ECONOMY_GROWTH.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByCountryId(Integer countryId, Integer economyGrowthIndicator){
        return context
                .select()
                .from(ECONOMY_GROWTH)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(ECONOMY_GROWTH.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(ECONOMY_GROWTH.ID_INDICATOR))
                .where(ECONOMY_GROWTH.ID_COUNTRY.eq(countryId))
                .and(ECONOMY_GROWTH.ID_INDICATOR.eq(economyGrowthIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(ECONOMY_GROWTH.ID),
                                r.get(ECONOMY_GROWTH.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(ECONOMY_GROWTH.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(ECONOMY_GROWTH.YEAR),
                                r.get(ECONOMY_GROWTH.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByYear(String year, Integer economyGrowthIndicator){
        return context
                .select()
                .from(ECONOMY_GROWTH)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(ECONOMY_GROWTH.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(ECONOMY_GROWTH.ID_INDICATOR))
                .where(ECONOMY_GROWTH.YEAR.eq(year))
                .and(ECONOMY_GROWTH.ID_INDICATOR.eq(economyGrowthIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(ECONOMY_GROWTH.ID),
                                r.get(ECONOMY_GROWTH.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(ECONOMY_GROWTH.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(ECONOMY_GROWTH.YEAR),
                                r.get(ECONOMY_GROWTH.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getByYearAndCountryId(String year, Integer countryId, Integer economyGrowthIndicator){
        return context
                .select()
                .from(ECONOMY_GROWTH)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(ECONOMY_GROWTH.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(ECONOMY_GROWTH.ID_INDICATOR))
                .where(ECONOMY_GROWTH.ID_COUNTRY.eq(countryId)).and(ECONOMY_GROWTH.YEAR.eq(year))
                .and(ECONOMY_GROWTH.ID_INDICATOR.eq(economyGrowthIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(ECONOMY_GROWTH.ID),
                                r.get(ECONOMY_GROWTH.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(ECONOMY_GROWTH.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(ECONOMY_GROWTH.YEAR),
                                r.get(ECONOMY_GROWTH.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getBetweenYear(String firstYear, String secondYear, Integer economyGrowthIndicator){
        return context
                .select()
                .from(ECONOMY_GROWTH)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(ECONOMY_GROWTH.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(ECONOMY_GROWTH.ID_INDICATOR))
                .where(ECONOMY_GROWTH.YEAR.between(firstYear)
                        .and(secondYear))
                .and(ECONOMY_GROWTH.ID_INDICATOR.eq(economyGrowthIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(ECONOMY_GROWTH.ID),
                                r.get(ECONOMY_GROWTH.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(ECONOMY_GROWTH.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(ECONOMY_GROWTH.YEAR),
                                r.get(ECONOMY_GROWTH.VALUE)
                        )
                );
    }

    public List<IndicatorWithCountry> getBetweenYearAndCountryId(String firstYear, String secondYear, Integer countryId, Integer economyGrowthIndicator){
        return context
                .select()
                .from(ECONOMY_GROWTH)
                .leftJoin(COUNTRY)
                .on(COUNTRY.ID.eq(ECONOMY_GROWTH.ID_COUNTRY))
                .leftJoin(INDICATORS)
                .on(INDICATORS.ID.eq(ECONOMY_GROWTH.ID_INDICATOR))
                .where(ECONOMY_GROWTH.YEAR.between(firstYear).and(secondYear))
                .and(ECONOMY_GROWTH.ID_COUNTRY.eq(countryId))
                .and(ECONOMY_GROWTH.ID_INDICATOR.eq(economyGrowthIndicator))
                .fetch()
                .map(
                        r-> new IndicatorWithCountry(
                                r.get(ECONOMY_GROWTH.ID),
                                r.get(ECONOMY_GROWTH.ID_INDICATOR),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME),
                                r.get(ECONOMY_GROWTH.ID_COUNTRY),
                                r.get(COUNTRY.FULL_NAME),
                                r.get(ECONOMY_GROWTH.YEAR),
                                r.get(ECONOMY_GROWTH.VALUE)
                        )
                );
    }

    public Integer insert(EconomyGrowth economyGrowth){
        EconomyGrowthRecord result = context
                .insertInto(ECONOMY_GROWTH, ECONOMY_GROWTH.ID_INDICATOR, ECONOMY_GROWTH.ID_COUNTRY,ECONOMY_GROWTH.YEAR,ECONOMY_GROWTH.VALUE)
                .values(
                        economyGrowth.getIdIndicator(),
                        economyGrowth.getIdCountry(),
                        economyGrowth.getYear(),
                        economyGrowth.getValue()
                )
                .returning(ECONOMY_GROWTH.ID)
                .fetchOne();
        return result.getId();
    }
}
