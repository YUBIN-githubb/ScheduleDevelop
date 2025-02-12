package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.CreateScheduleRequestDto;
import com.example.scheduledevelop.dto.CreateScheduleResponseDto;
import com.example.scheduledevelop.dto.ScheduleResponseDto;
import com.example.scheduledevelop.dto.UpdateScheduleRequestDto;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponseDto> createSchedule(@RequestBody CreateScheduleRequestDto dto) {
        CreateScheduleResponseDto schedule = scheduleService.createSchedule(dto.getTitle(), dto.getContents(), dto.getUsername());
        return ResponseEntity.ok(schedule);
    }

    //일정 전체 조회
    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "updatedAt") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<ScheduleResponseDto> schedulePage = scheduleService.findAll(pageable);

        return ResponseEntity.ok(schedulePage);
    }

    //일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto findSchedule = scheduleService.findById(id);
        return ResponseEntity.ok(findSchedule);
    }

    //일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto dto) {
        ScheduleResponseDto schedule = scheduleService.updateSchedule(id, dto.getTitle(), dto.getContents());
        return ResponseEntity.ok(schedule);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule (@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
