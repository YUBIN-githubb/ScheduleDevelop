package com.example.scheduledevelop.dto.ScheduleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String username;


}
