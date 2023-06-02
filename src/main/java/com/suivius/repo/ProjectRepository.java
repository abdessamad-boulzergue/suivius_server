package com.suivius.repo;


import com.suivius.models.Project;
import com.suivius.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT a.project from Affectation a WHERE a.user.id= :userId")
    List<Project> findAffectedToUser(Long userId);
}
