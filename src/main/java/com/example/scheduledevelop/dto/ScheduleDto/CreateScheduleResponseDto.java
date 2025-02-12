package com.example.scheduledevelop.dto.ScheduleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleResponseDto {

    private Long id;

    private String title;

    private String contents;
}
