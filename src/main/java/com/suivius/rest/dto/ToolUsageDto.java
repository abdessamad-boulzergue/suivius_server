package com.suivius.rest.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ToolUsageDto {
    public Long toolId;
    public String title;
    public LocalTime timeUsage;

    public LocalDate date;
}
