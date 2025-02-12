package com.example.scheduledevelop.dto.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentRequestDto {

    private Long schedule_id;

    private String comment;

    private String username;
}
