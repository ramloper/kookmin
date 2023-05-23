package org.kookmin.demo.repository;

import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.service.RentalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("select r from Rental r where r.status = :status")
    List<Rental> findAllByStatus(@Param("status")RentalStatus status);

    @Query("select r from Rental r where r.education.id = :id")
    Rental findByEducationId(@Param("id")Integer id);

    @Query("select r from Rental r join fetch r.member m where m.username = :username")
    List<Rental> findAllById(@Param("username") String username);
}
