package com.suivius.repo;


import com.suivius.models.Article;
import com.suivius.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    public Unit findByTitle(String title);
}
