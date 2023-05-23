package org.kookmin.demo.repository;

import org.kookmin.demo.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    @Query("select e from Education e join fetch e.rental")
    List<Education> findAllByRental();
}
