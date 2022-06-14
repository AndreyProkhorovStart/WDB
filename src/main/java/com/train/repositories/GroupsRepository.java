package com.train.repositories;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Groups;
import com.tej.JooQDemo.jooq.sample.model.tables.records.GroupsRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.tej.JooQDemo.jooq.sample.model.Tables.GROUPS;
import static com.tej.JooQDemo.jooq.sample.model.Tables.INDICATORS;

@Repository
public class GroupsRepository {
    @Autowired
    DSLContext context;

    public List<Groups> getAll() {
        return context.select()
                .from(GROUPS)
                .where(GROUPS.ISEXIST.eq((byte)1))
                .fetch()
                .map(
                        r -> new Groups(
                                r.get(GROUPS.ID),
                                r.get(GROUPS.NAME),
                                r.get(GROUPS.ISEXIST)
                        )
                );
    }

    public Groups getById(Integer id) {
        var result = context
                .select()
                .from(GROUPS)
                .where(GROUPS.ID.eq(id))
                .fetchOne();
        if(result == null) return null;
        return result.map(
                r -> new Groups(
                        r.get(GROUPS.ID),
                        r.get(GROUPS.NAME),
                        r.get(GROUPS.ISEXIST)

                )
        );
    }

    public Integer insert(Groups groups) {
        GroupsRecord result = context
                .insertInto(GROUPS, GROUPS.NAME)
                .values(
                        groups.getName()
                )
                .returning(GROUPS.ID)
                .fetchOne();
        return result.getId();
    }

    public void update(Integer idIndicator) {
        context.update(GROUPS)
                .set(GROUPS.ISEXIST, (byte) 1)
                .where(GROUPS.ID.eq(idIndicator))
                .execute();
    }
}
