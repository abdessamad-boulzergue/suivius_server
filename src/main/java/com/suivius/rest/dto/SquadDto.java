package com.suivius.rest.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SquadDto {
    public Long memberId;
    public String name;
    public LocalTime normalHours;
    public LocalTime additionalHours;
    public LocalDate date;
}
