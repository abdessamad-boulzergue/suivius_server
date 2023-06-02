package com.suivius.repo;


import com.suivius.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    public List<Permission> findByUserId(Long userId);
}
