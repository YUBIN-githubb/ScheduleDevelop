package com.example.scheduledevelop.dto.ScheduleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {

    private String title;

    private String contents;

    private String username;
}
