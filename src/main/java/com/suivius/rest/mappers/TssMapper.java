package com.suivius.rest.mappers;

import com.suivius.models.*;
import com.suivius.rest.dto.TssDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TssMapper {

    @Mapping(source = "cableType", target = "cableTypeId", qualifiedByName = "toId")
    @Mapping(source = "connectionType", target = "connectionTypeId", qualifiedByName = "toId")
    @Mapping(source = "equipmentType", target = "equipmentTypeId", qualifiedByName = "toId")
    @Mapping(source = "siteType", target = "siteTypeId", qualifiedByName = "toId")
    TssDto toDTO(Tss tss);

    @Named("toId")
    public static Long toId(Object obj) {
        if(obj==null)  return null;
        if(obj instanceof CableType)  return ((CableType)obj).getId();
        if(obj instanceof ConnectionType)  return ((ConnectionType)obj).getId();
        if(obj instanceof EquipmentType)  return ((EquipmentType)obj).getId();
        if(obj instanceof SiteType)  return ((SiteType)obj).getId();
        return null;
    }
}
