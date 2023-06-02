package com.suivius.rest.mappers;

import com.suivius.models.Project;
import com.suivius.rest.dto.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDto toDTO(Project project);


}
