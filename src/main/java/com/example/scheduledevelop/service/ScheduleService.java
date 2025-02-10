package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.CreateScheduleResponseDto;
import com.example.scheduledevelop.dto.ScheduleResponseDto;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.ScheduleRepository;
import com.example.scheduledevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CreateScheduleResponseDto createSchedule (String title, String contents, String username) {
        User findUser = userRepository.findByUsernameOrElseThrow(username);
        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponseDto(saveSchedule.getId(), saveSchedule.getTitle(), saveSchedule.getContents());

    }

    public List<ScheduleResponseDto> findAll() {
        List<ScheduleResponseDto> scheduleList = scheduleRepository.findAll().stream().map(Schedule::toDto).toList();
        return scheduleList;
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

}
