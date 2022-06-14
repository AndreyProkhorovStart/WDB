package com.train.services;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Groups;
import com.train.repositories.GroupsRepository;
import org.jooq.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupsService {
    @Autowired
    private GroupsRepository groupsRepository;

    public List<Groups> getAll() {
        return groupsRepository.getAll();
    }

    public Groups read(Integer id) {
        Groups groups = groupsRepository.getById(id);
        if(groups == null) throw new NoDataFoundException();
        return groups;
    }

    public Integer create(Groups groups) {
        return groupsRepository.insert(groups);
    }
    public void update(Integer idIndicator) {groupsRepository.update(idIndicator); }
}
