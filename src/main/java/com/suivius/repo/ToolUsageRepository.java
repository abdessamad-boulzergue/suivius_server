package com.suivius.repo;


import com.suivius.models.Squad;
import com.suivius.models.ToolsUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolUsageRepository extends JpaRepository<ToolsUsage, Long> {
    List<ToolsUsage> findByProjectId(Long id);
    ToolsUsage findByProjectIdAndToolId(Long projectId,Long memberId);
}
