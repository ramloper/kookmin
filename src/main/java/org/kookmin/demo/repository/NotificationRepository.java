package org.kookmin.demo.repository;

import org.kookmin.demo.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.aBoolean = :toMain")
    public List<Notification> findAllByToMain(Boolean toMain);
}
