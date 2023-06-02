package com.suivius.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class StepStatusDto {

    public Long id;

    @JsonProperty(value="id_step")
    public Long idStep;

    public String title;
}
