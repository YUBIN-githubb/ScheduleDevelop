package com.example.scheduledevelop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleRequestDto {

    private String title;

    private String contents;
}
