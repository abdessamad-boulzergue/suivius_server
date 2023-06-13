package com.suivius.rest.mappers;

import com.suivius.models.Authorization;
import com.suivius.rest.dto.AuthorizationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorizationMapper {

    AuthorizationDto toDTO(Authorization authorization);

}
