package com.suivius.rest.controllers;

import com.suivius.rest.dto.PostSquadDto;
import com.suivius.rest.dto.SquadDto;
import com.suivius.rest.dto.StaffDto;
import com.suivius.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("suivius/api/v1/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping
    public List<StaffDto> getStaff(){
        return  staffService.getStaff();
    }

    @GetMapping("/project/{projectId}")
    public Set<SquadDto> getSquad(@PathVariable Long projectId ){
        return  staffService.getSquad(projectId);
    }

    @PostMapping("/project/{projectId}/work")
    public void addToSquad(@PathVariable Long projectId , @RequestBody PostSquadDto postSquadDto){
          staffService.addToSquad(projectId,postSquadDto);
    }

}
