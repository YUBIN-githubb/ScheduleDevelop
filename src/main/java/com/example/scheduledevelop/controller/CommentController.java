package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.CommentRequestDto;
import com.example.scheduledevelop.dto.CommentResponseDto;
import com.example.scheduledevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto dto) {
        CommentResponseDto comment = commentService.createComment(dto.getSchedule_id(), dto.getComment(), dto.getUsername());
        return ResponseEntity.ok(comment);
    }

    //댓글 조회(페이지네이션)

    //댓글 수정

    //댓글 삭제
}
