package com.suivius.services;

import com.suivius.rest.mappers.ProjectMapper;
import com.suivius.models.Project;
import com.suivius.repo.ProjectRepository;
import com.suivius.rest.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDto> getAll(){
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(p-> projectMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<ProjectDto> getUserProjects(Long userId) {
        List<Project> projects = projectRepository.findAffectedToUser(userId);
        return projects.stream().map(p-> projectMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public Project get(Long projectId) {
        Optional<Project> optProject = projectRepository.findById(projectId);
        if(optProject.isPresent())
            return  optProject.get() ;
        else
            return null;
    }
}
