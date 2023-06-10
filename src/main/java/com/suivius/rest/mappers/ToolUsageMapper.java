package com.suivius.rest.mappers;

import com.suivius.models.Squad;
import com.suivius.models.StaffMember;
import com.suivius.models.ToolsUsage;
import com.suivius.models.WorkTool;
import com.suivius.rest.dto.SquadDto;
import com.suivius.rest.dto.ToolUsageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ToolUsageMapper {

    @Mapping(source = "tool", target = "toolId", qualifiedByName = "toId")
    @Mapping(source = "tool", target = "title", qualifiedByName = "getTitle")
    ToolUsageDto toDTO(ToolsUsage squad);

    @Named("toId")
    public static Long toId(WorkTool obj) {
        return (obj==null) ? null : obj.getId();
    }
    @Named("getTitle")
    public static String getTitle(WorkTool obj) {
        return (obj==null) ? null : obj.getTitle();
    }
}
