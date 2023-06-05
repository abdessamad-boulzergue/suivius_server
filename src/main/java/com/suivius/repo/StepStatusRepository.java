package com.suivius.repo;


import com.suivius.models.StepStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepStatusRepository extends JpaRepository<StepStatus, Long> {
}
