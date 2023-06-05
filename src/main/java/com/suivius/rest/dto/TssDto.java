package com.suivius.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TssDto {
    public Long connectionTypeId;

    public Long cableTypeId;

    public Long equipmentTypeId;

    public Long siteTypeId;
}
