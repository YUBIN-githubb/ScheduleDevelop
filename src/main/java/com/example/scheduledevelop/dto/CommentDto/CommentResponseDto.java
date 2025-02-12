package com.example.scheduledevelop.dto.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private String comment;

    private String username;

    private LocalDateTime updatedAt;
}
