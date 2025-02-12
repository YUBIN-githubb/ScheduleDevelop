package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));
    }

    Page<Schedule> findAll(Pageable pageable);
}
