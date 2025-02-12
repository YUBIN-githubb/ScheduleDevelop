package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.ScheduleDto.CreateScheduleResponseDto;
import com.example.scheduledevelop.dto.ScheduleDto.ScheduleResponseDto;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.ScheduleRepository;
import com.example.scheduledevelop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    //일정 생성
    public CreateScheduleResponseDto createSchedule (String title, String contents, String username) {
        User findUser = userRepository.findByUsernameOrElseThrow(username);
        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponseDto(saveSchedule.getId(), saveSchedule.getTitle(), saveSchedule.getContents());
    }

    //일정 전체 조회 (페이지네이션)
    public Page<ScheduleResponseDto> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable).map(Schedule::toDto);
    }

    //일정 단건 조회
    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(
                findSchedule.getId(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getCreatedAt(),
                findSchedule.getUpdatedAt(),
                findSchedule.getUser().getUsername());
    }

    //일정 업데이트
    @Transactional
    public ScheduleResponseDto updateSchedule (Long id, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.updateSchedule(title, contents);
        return new ScheduleResponseDto(
                findSchedule.getId(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getCreatedAt(),
                findSchedule.getUpdatedAt(),
                findSchedule.getUser().getUsername());
    }

    //일정 삭제
    public void deleteSchedule (Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
