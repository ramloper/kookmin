package org.kookmin.demo.repository;

import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.domain.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    @Query("select e from Education e join fetch e.rental where e.status!=:educationStatus")
    List<Education> findAllByRental(EducationStatus educationStatus);

    @Query("select e from Education e where e.status!=:status")
    List<Education> findAllEducation(EducationStatus status);

    @Query(value = "select e from Education e where e.status = :educationStatus",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByEducationPage(Pageable pageable, EducationStatus educationStatus);

    @Query(value = "select e from Education e where e.bookName like %:searchType%",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByBookName(Pageable pageable,@Param("searchType") String searchType);
    @Query(value = "select e from Education e where e.publisher like %:searchType%",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByPublisher(Pageable pageable,@Param("searchType") String searchType);
    @Query(value = "select e from Education e where e.writer like %:searchType%",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByWriter(Pageable pageable,@Param("searchType") String searchType);
    @Query(value = "select e from Education e where e.translator like %:searchType%",
            countQuery = "select count(e) from Education e")
    Page<Education> findAllByTranslator(Pageable pageable,@Param("searchType") String searchType);

    @Query("update Education e set e.bookName = :bookName, e.publisher = :publisher, e.writer = :writer, e.translator = :translator where e.id = :id")
    @Modifying
    void updateEducationByBookName(@Param("id")Integer id, @Param("bookName")String bookName, @Param("publisher")String publisher,
                                   @Param("writer")String writer, @Param("translator")String translator);
}
