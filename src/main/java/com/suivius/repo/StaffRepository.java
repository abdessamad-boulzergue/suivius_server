package com.suivius.repo;


import com.suivius.models.Article;
import com.suivius.models.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffMember, Long> {

}
