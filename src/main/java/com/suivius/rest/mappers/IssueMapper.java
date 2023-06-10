package com.suivius.rest.mappers;

import com.suivius.models.StepStatus;
import com.suivius.repo.Issue;
import com.suivius.rest.dto.IssueDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface IssueMapper {

    @Mapping(source = "stepStatus", target = "stepStatusId", qualifiedByName = "toId")
    @Mapping(source = "closed", target = "isClosed")
    IssueDto toDtO(Issue issue);

    Issue toEntity(IssueDto issueDto);

    Set<IssueDto> toDtoSet(Set<Issue> issueSet);

    Set<Issue> toEntitySet(Set<IssueDto> issueDtoSet);

    @Named("toId")
    public static Long toId(StepStatus obj) {
        return (obj==null) ? null : obj.getId();
    }
}