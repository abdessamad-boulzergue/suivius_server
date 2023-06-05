package com.suivius.rest.mappers;

import com.suivius.models.Article;
import com.suivius.models.BOQ;
import com.suivius.rest.dto.BoqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface BoqMapper {

    @Mapping(source = "article", target = "articleId", qualifiedByName = "toId")
    @Mapping(source = "article", target = "title", qualifiedByName = "toString")
    @Mapping(source = "article", target = "unite", qualifiedByName = "getUnit")
    BoqDto toDtO(BOQ boq);

    BOQ toEntity(BoqDto boqDto);

    Set<BoqDto> toDtoSet(Set<BOQ> boqSet);

    Set<BOQ> toEntitySet(Set<BoqDto> boqDtoSet);

    @Named("toId")
    public static Long toId(Article obj) {
        return (obj==null) ? null : obj.getId();
    }
    @Named("toString")
    public static String toString(Article obj) {
        return (obj==null) ? null : obj.getTitle();
    }
    @Named("getUnit")
    public static String getUnit(Article obj) {
        return (obj==null) ? null : obj.getUnit().getTitle();

    }
}
