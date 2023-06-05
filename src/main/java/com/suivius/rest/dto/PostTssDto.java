package com.suivius.rest.dto;

import com.suivius.models.ProjectWorkDetails;

import java.util.List;

public class PostTssDto {
    public Long cableTypeId;
    public Long siteTypeId;
    public Long connectionTypeId;
    public Long equipmentTypeId;

    public List<NewDocumentDto> documents;

    public List<ProjectWorkDetailsDto> workDetails;
}
