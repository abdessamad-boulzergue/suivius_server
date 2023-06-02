package com.suivius.rest.mappers;

import com.suivius.models.ProjectCategory;
import com.suivius.models.Step;
import com.suivius.rest.dto.CategoryDto;
import com.suivius.rest.dto.StepDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDTO(ProjectCategory category);

}
