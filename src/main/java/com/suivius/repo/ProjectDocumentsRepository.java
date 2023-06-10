package com.suivius.repo;


import com.suivius.models.ProjectDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDocumentsRepository extends JpaRepository<ProjectDocuments, Long> {

    List<ProjectDocuments> findByProjectId(Long projectId);
}
