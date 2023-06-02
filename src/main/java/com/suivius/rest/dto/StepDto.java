package com.suivius.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class StepDto {

    public Long id;
    public String title;
}
