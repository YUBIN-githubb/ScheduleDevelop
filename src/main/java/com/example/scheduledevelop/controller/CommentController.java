package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.CreateCommentRequestDto;
import com.example.scheduledevelop.dto.CommentResponseDto;
import com.example.scheduledevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CreateCommentRequestDto dto) {
        CommentResponseDto comment = commentService.createComment(dto.getSchedule_id(), dto.getComment(), dto.getUsername());
        return ResponseEntity.ok(comment);
    }

    //댓글 조회(페이지네이션)
    @GetMapping
    public ResponseEntity<Page<CommentResponseDto>> getAllComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "updatedAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<CommentResponseDto> findComment = commentService.findAll(pageable);

        return ResponseEntity.ok(findComment);
    }

    //댓글 수정

    //댓글 삭제
}
