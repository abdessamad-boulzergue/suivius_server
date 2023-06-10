package com.suivius.repo;


import com.suivius.models.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
    List<Squad> findByProjectId(Long id);
    Squad findByProjectIdAndMemberId(Long projectId,Long memberId);
}
