package com.suivius.rest.mappers;

import com.suivius.models.Article;
import com.suivius.models.BOQ;
import com.suivius.models.ProjectWorkDetails;
import com.suivius.models.WorkInfo;
import com.suivius.rest.dto.BoqDto;
import com.suivius.rest.dto.WorkDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkDetailsMapper {

    @Mapping(source = "info", target = "infoId", qualifiedByName = "toId")
    @Mapping(source = "info", target = "title", qualifiedByName = "toString")
    WorkDetailsDto toDtO(ProjectWorkDetails workDetails);

    ProjectWorkDetails toEntity(WorkDetailsDto workDetailsDto);

    Set<WorkDetailsDto> toDtoSet(Set<ProjectWorkDetails> workDetailsSet);

    Set<ProjectWorkDetails> toEntitySet(Set<WorkDetailsDto> workDetailsDtoSet);

    @Named("toId")
    public static Long toId(WorkInfo obj) {
        return (obj==null) ? null : obj.getId();
    }
    @Named("toString")
    public static String toString(WorkInfo obj) {
        return (obj==null) ? null : obj.getTitle();
    }

}
