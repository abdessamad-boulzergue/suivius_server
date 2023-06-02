package com.suivius.rest.controllers;

import com.suivius.rest.dto.ProjectDto;
import com.suivius.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
