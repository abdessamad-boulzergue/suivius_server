package com.suivius.repo;


import com.suivius.models.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
    Set<Squad> findByProjectId(Long id);
    Squad findByProjectIdAndMemberId(Long projectId,Long memberId);
}
