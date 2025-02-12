package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.CommentResponseDto;
import com.example.scheduledevelop.entity.Comment;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.repository.CommentRepository;
import com.example.scheduledevelop.repository.ScheduleRepository;
import com.example.scheduledevelop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

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
}
