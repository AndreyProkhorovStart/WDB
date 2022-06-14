package com.train.repositories;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Indicators;
import com.tej.JooQDemo.jooq.sample.model.tables.records.IndicatorsRecord;
import com.train.merged_pojo.IndicatorWithGroup;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.tej.JooQDemo.jooq.sample.model.Tables.GROUPS;
import static com.tej.JooQDemo.jooq.sample.model.Tables.INDICATORS;

@Repository
public class IndicatorsRepository {
    @Autowired
    DSLContext context;

    public List<IndicatorWithGroup> getAll() {
        return context
                .select()
                .from(INDICATORS)
                .leftJoin(GROUPS)
                .on(GROUPS.ID.eq(INDICATORS.ID_GROUP))
                .where(INDICATORS.ISEXIST.eq((byte)1))
                .fetch()
                .map(
                        r -> new IndicatorWithGroup(
                                r.get(INDICATORS.ID),
                                r.get(INDICATORS.ID_GROUP),
                                r.get(GROUPS.NAME),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME)
                        )
                );
    }

    public List<IndicatorWithGroup> getByGroupId(Integer id_groups) {
        return context
                .select()
                .from(INDICATORS)
                .leftJoin(GROUPS)
                .on(GROUPS.ID.eq(INDICATORS.ID_GROUP))
                .where(INDICATORS.ISEXIST.eq((byte)1))
                .and(INDICATORS.ID_GROUP.eq(id_groups))
                .fetch()
                .map(
                        r -> new IndicatorWithGroup(
                                r.get(INDICATORS.ID),
                                r.get(INDICATORS.ID_GROUP),
                                r.get(GROUPS.NAME),
                                r.get(INDICATORS.CODE),
                                r.get(INDICATORS.NAME)
                        )
                );
    }

    public Integer getByCodeAndGroupId(String code, Integer id_groups) {
        return context
                .select()
                .from(INDICATORS)
                .where(INDICATORS.CODE.eq(code))
                .and(INDICATORS.ID_GROUP.eq(id_groups))
                .fetchAny(INDICATORS.ID);
    }

    public Integer insert(Indicators indicators) {
        IndicatorsRecord result = context
                .insertInto(INDICATORS, INDICATORS.ID_GROUP, INDICATORS.CODE, INDICATORS.NAME)
                .values(
                        indicators.getIdGroup(),
                        indicators.getCode(),
                        indicators.getName()

                )
                .returning(INDICATORS.ID)
                .fetchOne();
        return result.getId();
    }

    public void update(Integer idIndicator) {
        context.update(INDICATORS)
                .set(INDICATORS.ISEXIST, (byte) 1)
                .where(INDICATORS.ID.eq(idIndicator))
                .execute();
    }
}
