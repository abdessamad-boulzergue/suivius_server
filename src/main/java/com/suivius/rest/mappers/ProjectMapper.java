package com.suivius.rest.mappers;

import com.suivius.models.Project;
import com.suivius.models.Tss;
import com.suivius.rest.dto.PostTssDto;
import com.suivius.rest.dto.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TssMapper.class,IssueMapper.class,BoqMapper.class,WorkDetailsMapper.class,
        LocalisationMapper.class,AuthorizationMapper.class})
public interface ProjectMapper {

    ProjectDto toDTO(Project project);


    Tss dtoToTss(PostTssDto tssDto);
}
