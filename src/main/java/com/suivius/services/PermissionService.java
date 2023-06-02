package com.suivius.services;

import com.suivius.models.Permission;
import com.suivius.models.Project;
import com.suivius.repo.PermissionRepository;
import com.suivius.repo.ProjectRepository;
import com.suivius.rest.dto.PermissionDto;
import com.suivius.rest.dto.ProjectDto;
import com.suivius.rest.mappers.PermissionMapper;
import com.suivius.rest.mappers.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionService(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    public List<PermissionDto> getAll(){
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(p-> permissionMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<PermissionDto> getUserPermissions(Long userId) {
        List<Permission> permissions = permissionRepository.findByUserId(userId);
        return permissions.stream().map(p-> permissionMapper.toDTO(p))
                .collect(Collectors.toList());
    }
}
