package com.suivius.repo;


import com.suivius.models.BOQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BOQRepository extends JpaRepository<BOQ, Long> {

    public BOQ findByArticleIdAndProjectId(Long articleId, Long project_id);
}
