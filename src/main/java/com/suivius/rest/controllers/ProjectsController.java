package com.suivius.rest.controllers;

import com.suivius.models.Project;
import com.suivius.rest.dto.*;
import com.suivius.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("suivius/api/v1/projects")
public class ProjectsController {

    @Autowired
    ProjectService  projectService;
    @GetMapping
    public List<ProjectDto> getProjects(){
        return projectService.getAll();
    }

    @GetMapping("user/{userId}")
    public List<ProjectDto> getUserProjects(@PathVariable  Long userId){
        return projectService.getUserProjects(userId);
    }
    @PostMapping("{project_id}/issue")
    public void addIssue(@PathVariable  Long project_id,@RequestBody IssueDto  issueDto){
         projectService.addIssue(project_id,issueDto);
    }

    @PutMapping("{project_id}/issue")
    public void endIssue(@PathVariable  Long project_id){
        projectService.endIssue(project_id);
    }
    @PostMapping("{project_id}/tss")
    public StepUpdateResponseDto setTss(@PathVariable  Long project_id, @RequestBody PostTssDto tssDto){
        Project project = projectService.setTss(project_id, tssDto);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/tss/preValidation")
    public StepUpdateResponseDto preValidationTss(@PathVariable  Long project_id, @RequestBody PostTssDto tssDto){
        Project project = projectService.preValidateTss(project_id,tssDto);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/tss/validation")
    public StepUpdateResponseDto validationTss(@PathVariable  Long project_id){
        Project project = projectService.validateTss(project_id);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/tss/rejection")
    public StepUpdateResponseDto rejectTss(@PathVariable  Long project_id, @RequestBody RejectionDto rejection){
        Project project = projectService.rejectTss(project_id,rejection.motif);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/apd/preValidation")
    public StepUpdateResponseDto preValidationAPD(@PathVariable  Long project_id){
        Project project =  projectService.preValidateAPD(project_id);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/apd/validation")
    public StepUpdateResponseDto validationAPD(@PathVariable  Long project_id){
        Project project =  projectService.validateAPD(project_id);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/apd/rejection")
    public StepUpdateResponseDto rejectAPD(@PathVariable  Long project_id, @RequestBody RejectionDto rejection){
        Project project =  projectService.rejectAPD(project_id,rejection.motif);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/boq")
    public StepUpdateResponseDto setBoq(@PathVariable  Long project_id, @RequestBody PostBoqDto boqDto){
        Project project=  projectService.setBoq(project_id,boqDto);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/study")
    public StepUpdateResponseDto startStudy(@PathVariable  Long project_id){
       Project project= projectService.startStudy(project_id);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
    @PostMapping("{project_id}/tss/edition")
    public StepUpdateResponseDto startTss(@PathVariable  Long project_id){
        Project project= projectService.startTss(project_id);
        StepUpdateResponseDto responseDto = new StepUpdateResponseDto();
        responseDto.projectId =project.getId();
        responseDto.stepStatusId = project.getStepStatus().getId();
        return responseDto;
    }
}
