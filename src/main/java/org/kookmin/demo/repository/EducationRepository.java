package org.kookmin.demo.repository;

import org.kookmin.demo.domain.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    @Query("select e from Education e join fetch e.rental")
    List<Education> findAllByRental();

    @Query(value = "select e from Education e",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByEducationPage(Pageable pageable);

    @Query(value = "select e from Education e where e.bookName = :searchType",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByBookName(Pageable pageable,@Param("searchType") String searchType);
    @Query(value = "select e from Education e where e.publisher = :searchType",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByPublisher(Pageable pageable,@Param("searchType") String searchType);
    @Query(value = "select e from Education e where e.writer = :searchType",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByWriter(Pageable pageable,@Param("searchType") String searchType);
    @Query(value = "select e from Education e where e.translator = :searchType",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByTranslator(Pageable pageable,@Param("searchType") String searchType);
}
