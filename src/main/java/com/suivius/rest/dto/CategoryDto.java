package com.suivius.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CategoryDto {
    public Long id;
    public String title;
}
