package com.suivius.repo;


import com.suivius.models.ConnectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionTypeRepository extends JpaRepository<ConnectionType, Long> {

}
