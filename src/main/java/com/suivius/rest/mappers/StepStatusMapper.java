package com.suivius.rest.mappers;

import com.suivius.models.ProjectCategory;
import com.suivius.models.Step;
import com.suivius.models.StepStatus;
import com.suivius.rest.dto.CategoryDto;
import com.suivius.rest.dto.StepStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StepStatusMapper {

    StepStatusDto toDTO(StepStatus status);

    @Named("stepToId")
    public static Long stepToId(Step step) {
        if(step==null)
            return null;
        return step.getId();
    }
}
