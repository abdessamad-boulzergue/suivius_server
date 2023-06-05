package com.suivius.repo;


import com.suivius.models.Tss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TssRepository extends JpaRepository<Tss, Long> {

}
