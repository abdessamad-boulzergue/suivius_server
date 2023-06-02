package com.suivius.rest.mappers;

import com.suivius.models.Step;
import com.suivius.rest.dto.StepDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StepMapper {

    StepDto toDTO(Step step);

}
