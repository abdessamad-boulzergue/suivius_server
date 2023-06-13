package com.suivius.services;

import com.suivius.rest.mappers.ProjectMapper;
import org.springframework.stereotype.Component;

@Component
public class MappersStore {

    public final ProjectMapper projectMapper;

    public MappersStore(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }
}
