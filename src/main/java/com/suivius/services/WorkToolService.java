package com.suivius.services;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.models.*;
import com.suivius.repo.*;
import com.suivius.rest.dto.*;
import com.suivius.rest.mappers.ToolMapper;
import com.suivius.rest.mappers.ToolUsageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkToolService {

    private final WorkToolRepository workToolRepository;
    private final ToolUsageRepository toolUsageRepository;
    private final ToolMapper toolMapper;
    private final ToolUsageMapper toolUsageMapper;

    private final ProjectRepository projectRepository;

    public WorkToolService(WorkToolRepository workToolRepository, ToolUsageRepository toolUsageRepository,
                           ToolMapper toolMapper, ToolUsageMapper toolUsageMapper,
                           ProjectRepository projectRepository){
        this.workToolRepository =workToolRepository;
        this.toolUsageRepository =toolUsageRepository;
        this.toolMapper=toolMapper;
        this.toolUsageMapper = toolUsageMapper;
        this.projectRepository = projectRepository;
    }


    public List<ToolDto> getTools() {
        List<WorkTool> tools = this.workToolRepository.findAll();
        return tools.stream().map(tool -> this.toolMapper.toDTO(tool))
                .collect(Collectors.toList());

    }

    public List<ToolUsageDto> getToolsUsage(Long projectId) {
        List<ToolsUsage> tools = this.toolUsageRepository.findByProjectId(projectId);
        return tools.stream().map(tool -> this.toolUsageMapper.toDTO(tool))
                .collect(Collectors.toList());
    }

    public void addToolsUsage(Long projectId, PostToolsUsageDto postToolsUsageDto) {
        if(postToolsUsageDto!=null && postToolsUsageDto.usages!=null){
            postToolsUsageDto.usages.forEach(toolUsageDto -> {

                ToolsUsage usage =this.toolUsageRepository.findByProjectIdAndToolId(projectId,toolUsageDto.toolId);
                if(usage==null) usage = new ToolsUsage();

                WorkTool workTool = workToolRepository.findById(toolUsageDto.toolId)
                        .orElseThrow(()-> new ResourceNotFoundException("tool not found with id "+ toolUsageDto.toolId));
                Project project = projectRepository.findById(projectId)
                        .orElseThrow(()-> new ResourceNotFoundException("project not found with id "+ projectId));
                usage.setTool(workTool);
                usage.setProject(project);
                usage.setTimeUsage(toolUsageDto.timeUsage);

                toolUsageRepository.save(usage);
            });
        }
    }
}
