package com.suivius.repo;


import com.suivius.models.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Long> {

}
