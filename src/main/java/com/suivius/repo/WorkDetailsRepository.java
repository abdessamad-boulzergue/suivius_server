package com.suivius.repo;

import com.suivius.models.ProjectWorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDetailsRepository  extends JpaRepository<ProjectWorkDetails,Long> {
}
