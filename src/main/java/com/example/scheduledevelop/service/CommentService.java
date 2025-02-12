package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.CommentDto.CommentResponseDto;
import com.example.scheduledevelop.entity.Comment;
import com.example.scheduledevelop.repository.CommentRepository;
import com.example.scheduledevelop.repository.ScheduleRepository;
import com.example.scheduledevelop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    //댓글 생성
    @Transactional
    public CommentResponseDto createComment (Long schedule_id, String comment, String username) {
        Comment createdComment = new Comment(comment);
        createdComment.setUser(userRepository.findByUsernameOrElseThrow(username));
        createdComment.setSchedule(scheduleRepository.findById(schedule_id).get());

        commentRepository.save(createdComment);

        return new CommentResponseDto(
                createdComment.getComment(),
                createdComment.getUser().getUsername(),
                createdComment.getUpdatedAt());
    }

    //댓글 조회 (페이지네이션)
    public Page<CommentResponseDto> findAll (Pageable pageable) {
        return commentRepository.findAll(pageable).map(Comment::toDto);
    }
}
