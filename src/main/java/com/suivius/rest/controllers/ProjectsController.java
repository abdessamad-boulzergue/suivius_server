package com.suivius.rest.controllers;

import com.suivius.rest.dto.PostBoqDto;
import com.suivius.rest.dto.PostTssDto;
import com.suivius.rest.dto.ProjectDto;
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
    @PostMapping("{project_id}/tss")
    public void  setTss(@PathVariable  Long project_id,@RequestBody PostTssDto tssDto){
         projectService.setTss(project_id,tssDto);
    }
    @PostMapping("{project_id}/tss/preValidation")
    public void  preValidationTss(@PathVariable  Long project_id,@RequestBody PostTssDto tssDto){
        projectService.preValidateTss(project_id,tssDto);
    }
    @PostMapping("{project_id}/tss/validation")
    public void  validationTss(@PathVariable  Long project_id){
        projectService.validateTss(project_id);
    }
    @PostMapping("{project_id}/boq")
    public void  setBoq(@PathVariable  Long project_id,@RequestBody PostBoqDto boqDto){
        projectService.setBoq(project_id,boqDto);
    }

}
