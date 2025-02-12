package com.example.scheduledevelop.entity;

import com.example.scheduledevelop.dto.ScheduleResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Table(name = "schedule")
@Getter
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {}

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getUser().getUsername());
    }

    public void updateSchedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
