package com.suivius.rest.mappers;

import com.suivius.models.WorkTool;
import com.suivius.rest.dto.ToolDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToolMapper {
    ToolDto toDTO(WorkTool tool);
}
