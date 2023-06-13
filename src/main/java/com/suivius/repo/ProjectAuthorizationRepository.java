package com.suivius.repo;


import com.suivius.models.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAuthorizationRepository extends JpaRepository<Authorization, Long> {

}
