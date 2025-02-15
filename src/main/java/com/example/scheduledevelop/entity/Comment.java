package com.example.scheduledevelop.entity;

import com.example.scheduledevelop.dto.CommentDto.CommentResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Entity
@Table(name = "comment")
@Getter
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String comment;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {}

    public Comment(String comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getComment(),
                comment.getUser().getUsername(),
                comment.getUpdatedAt()
        );
    }
}
