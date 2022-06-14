package com.train.repositories;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Country;
import com.tej.JooQDemo.jooq.sample.model.tables.records.CountryRecord;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.tej.JooQDemo.jooq.sample.model.Tables.COUNTRY;

@Repository
public class CountryRepository {

    @Autowired
    DSLContext context;

    public List<Country> getAll() {
            return context.select()
                .from(COUNTRY)
                .fetch()
                .map(
                    r -> new Country(
                            r.get(COUNTRY.ID),
                            r.get(COUNTRY.CODE_NAME),
                            r.get(COUNTRY.FULL_NAME)
                    )
                );
    }

    public Country getById(Integer id) {
        var result = context
                .select()
                .from(COUNTRY)
                .where(COUNTRY.ID.eq(id))
                .fetchOne();
        if(result == null) return null;
        return result.map(
                r -> new Country(
                        r.get(COUNTRY.ID),
                        r.get(COUNTRY.CODE_NAME),
                        r.get(COUNTRY.FULL_NAME)
                )
        );
    }

    public Integer getByCodeName(String codeName){
        return context
                .select()
                .from(COUNTRY)
                .where(COUNTRY.CODE_NAME.eq(codeName))
                .fetchAny(COUNTRY.ID);
    }

    public Integer insert(Country country) {
        CountryRecord result = context
                .insertInto(COUNTRY, COUNTRY.CODE_NAME, COUNTRY.FULL_NAME)
                .values(
                        country.getCodeName(),
                        country.getFullName()

                )
                .returning(COUNTRY.ID)
                .fetchOne();
        return result.getId();
    }
}
