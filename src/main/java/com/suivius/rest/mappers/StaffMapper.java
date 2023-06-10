package com.suivius.rest.mappers;

import com.suivius.models.StaffMember;
import com.suivius.rest.dto.StaffDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffDto toDTO(StaffMember staffMember);
}
