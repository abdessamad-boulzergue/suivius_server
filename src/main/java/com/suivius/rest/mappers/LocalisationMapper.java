package com.suivius.rest.mappers;

import com.suivius.models.Localisation;
import com.suivius.rest.dto.LocalisationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalisationMapper {
    LocalisationDto toDto(Localisation localisation);
}
