package com.suivius.repo;


import com.suivius.models.WorkTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkToolRepository extends JpaRepository<WorkTool, Long> {

}
