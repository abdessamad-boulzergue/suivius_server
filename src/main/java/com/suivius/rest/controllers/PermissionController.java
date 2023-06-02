package com.suivius.rest.controllers;

import com.suivius.rest.dto.PermissionDto;
import com.suivius.rest.dto.ProjectDto;
import com.suivius.services.PermissionService;
import com.suivius.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("suivius/api/v1/permissions")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping
    public List<PermissionDto> getPermissions(){
        return permissionService.getAll();
    }
    @GetMapping("user/{user_id}")
    public List<PermissionDto> getUserPermissions(@PathVariable("user_id") Long userId){
        return permissionService.getUserPermissions(userId);
    }
}
