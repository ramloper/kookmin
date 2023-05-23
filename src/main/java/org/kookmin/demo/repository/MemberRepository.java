package org.kookmin.demo.repository;

import org.kookmin.demo.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.username = :username")
    Optional<Member> getWithRoles(@Param("username") String studentId);

    @Query("select m from Member m join fetch m.Rental r join fetch r.education")
    List<Member> findAllByRental();
}
