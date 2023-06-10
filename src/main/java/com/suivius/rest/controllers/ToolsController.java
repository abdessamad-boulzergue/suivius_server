package com.suivius.rest.controllers;

import com.suivius.rest.dto.PostSquadDto;
import com.suivius.rest.dto.PostToolsUsageDto;
import com.suivius.rest.dto.ToolDto;
import com.suivius.rest.dto.ToolUsageDto;
import com.suivius.services.WorkToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("suivius/api/v1/tools")
public class ToolsController {

    @Autowired
    WorkToolService workToolService;

    @GetMapping
    public List<ToolDto> getTools(){
        return  workToolService.getTools();
    }

    @GetMapping("/project/{projectId}")
    public List<ToolUsageDto> getToolsUsage(@PathVariable Long projectId ){
        return  workToolService.getToolsUsage(projectId);
    }

    @PostMapping("/project/{projectId}")
    public void addToolsUsage(@PathVariable Long projectId , @RequestBody PostToolsUsageDto postToolsUsageDto){
        workToolService.addToolsUsage(projectId,postToolsUsageDto);
    }

}
