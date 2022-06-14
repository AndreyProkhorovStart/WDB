package com.train.dto.converters;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Groups;
import com.train.dto.GroupsDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroupsConverter {
    public GroupsDto toDto(Groups groups) {
        return new GroupsDto(
                groups.getName()
        );
    }

    public Groups toModel(GroupsDto groupsDto) {
        var groups = new Groups();
        groups.setName(groupsDto.getName());
        return groups;
    }
}
