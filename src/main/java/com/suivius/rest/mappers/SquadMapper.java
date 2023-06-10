package com.suivius.rest.mappers;

import com.suivius.models.Squad;
import com.suivius.models.StaffMember;
import com.suivius.rest.dto.SquadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SquadMapper {

    @Mapping(source = "member", target = "memberId", qualifiedByName = "toId")
    @Mapping(source = "member", target = "name", qualifiedByName = "toName")
    SquadDto toDTO(Squad squad);

    @Named("toId")
    public static Long toId(StaffMember obj) {
        return (obj==null) ? null : obj.getId();
    }
    @Named("toName")
    public static String toName(StaffMember obj) {
        return (obj==null) ? null : obj.getName();
    }
}
