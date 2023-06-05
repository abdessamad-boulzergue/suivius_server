package com.suivius.repo;


import com.suivius.models.SiteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteTypeRepository extends JpaRepository<SiteType, Long> {
}
