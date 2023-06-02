package com.suivius.rest.mappers;

import com.suivius.models.Permission;
import com.suivius.models.Project;
import com.suivius.models.Step;
import com.suivius.models.User;
import com.suivius.rest.dto.PermissionDto;
import com.suivius.rest.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PermissionMapper {


    @Mapping(source = "step", target = "idStep", qualifiedByName = "ToId")
    @Mapping(source = "project", target = "idProject", qualifiedByName = "ToId")
    @Mapping(source = "user", target = "idUser", qualifiedByName = "ToId")
    PermissionDto toDTO(Permission permission);

    @Named("ToId")
    public static Long ToId(Object obj) {
        if(obj==null)  return null;
        if(obj instanceof User)  return ((User)obj).getId();
        if(obj instanceof Step)  return ((Step)obj).getId();
        if(obj instanceof Project)  return ((Project)obj).getId();
        return null;
    }
}
