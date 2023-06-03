package com.suivius.rest.mappers;

import com.suivius.models.*;
import com.suivius.rest.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "unit", target = "unit", qualifiedByName = "toString")
    ArticleDto toDTO(Article article);

    @Named("toString")
    public static String toString(Object obj) {
        if(obj==null)  return null;
        if(obj instanceof Unit)  return ((Unit)obj).getTitle();
        return null;
    }
}
