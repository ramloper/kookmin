package org.kookmin.demo.repository;

import org.kookmin.demo.domain.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Integer> {

}
