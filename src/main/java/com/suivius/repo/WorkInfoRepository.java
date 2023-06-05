package com.suivius.repo;

import com.suivius.models.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkInfoRepository extends JpaRepository<WorkInfo,Long> {
}
