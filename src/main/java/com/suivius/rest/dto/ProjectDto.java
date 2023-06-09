package com.suivius.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suivius.models.Step;
import com.suivius.repo.Issue;
import lombok.Data;

import java.util.Set;

@JsonSerialize
public class ProjectDto {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String title;

    @JsonProperty
    private StepDto step;
    @JsonProperty
    private CategoryDto category;

    @JsonProperty
    private LocalisationDto localisation;
    @JsonProperty
    private StepStatusDto  stepStatus;

    @JsonProperty
    private TssDto  tss;
    @JsonProperty
    private AuthorizationDto authorization;
    @JsonProperty
    private Set<BoqDto> boq;

    @JsonProperty
    private Set<IssueDto> issues;

    @JsonProperty
    private Set<WorkDetailsDto> workDetails;

    @JsonProperty
    private Set<SquadDto> squad;

    @JsonProperty
    private Set<ToolUsageDto> toolsUsage;
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    public StepDto getStep() {
        return step;
    }

    public void setStep(StepDto step) {
        this.step = step;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StepStatusDto getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(StepStatusDto status) {
        this.stepStatus = status;
    }

    public TssDto getTss() {
        return tss;
    }

    public void setTss(TssDto tss) {
        this.tss = tss;
    }

    public Set<BoqDto> getBoq() {
        return boq;
    }

    public void setBoq(Set<BoqDto> boq) {
        this.boq = boq;
    }

    public Set<WorkDetailsDto> getWorkDetails() {
        return workDetails;
    }

    public void setWorkDetails(Set<WorkDetailsDto> workDetails) {
        this.workDetails = workDetails;
    }

    public LocalisationDto getLocalisation() {
        return localisation;
    }

    public void setLocalisation(LocalisationDto localisation) {
        this.localisation = localisation;
    }

    public Set<IssueDto> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueDto> issues) {
        this.issues = issues;
    }

    public AuthorizationDto getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthorizationDto authorization) {
        this.authorization = authorization;
    }

    public Set<SquadDto> getSquad() {
        return squad;
    }

    public void setSquad(Set<SquadDto> squad) {
        this.squad = squad;
    }

    public Set<ToolUsageDto> getToolsUsage() {
        return toolsUsage;
    }

    public void setToolsUsage(Set<ToolUsageDto> toolsUsage) {
        this.toolsUsage = toolsUsage;
    }
}
