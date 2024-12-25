package com.lcwd.fitnesstracker.Service.impl;

import com.lcwd.fitnesstracker.Service.ActivityLogService;
import com.lcwd.fitnesstracker.dtos.ActivityLogDTO;
import com.lcwd.fitnesstracker.entities.ActivityLog;
import com.lcwd.fitnesstracker.entities.User;
import com.lcwd.fitnesstracker.exceptions.ResourceNotFoundException;
import com.lcwd.fitnesstracker.repository.ActivityLogRepository;
import com.lcwd.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ActivityLogServiceImpl  implements ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private UserRepository userRepository;

    private ActivityLogDTO convertToDTO(ActivityLog entity) {
        ActivityLogDTO dto = new ActivityLogDTO();
        dto.setId(entity.getId());
        dto.setActivityName(entity.getActivityName());
        dto.setDate(entity.getDate());
        dto.setDurationInMinutes(entity.getDurationInMinutes());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    private ActivityLog convertToEntity(ActivityLogDTO dto) {
        ActivityLog entity = new ActivityLog();
        entity.setId(dto.getId());
        entity.setActivityName(dto.getActivityName());
        entity.setDate(dto.getDate());
        entity.setDurationInMinutes(dto.getDurationInMinutes());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
        entity.setUser(user);
        return entity;
    }
    @Override
    public List<ActivityLogDTO> getAllActivityLogs() {
        return activityLogRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityLogDTO getActivityLogById(Long id) {
        ActivityLog entity = activityLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity log not found with id: " + id));
        return convertToDTO(entity);
    }

    @Override
    public ActivityLogDTO createActivityLog(ActivityLogDTO dto) {
        ActivityLog entity = convertToEntity(dto);
        ActivityLog savedEntity = activityLogRepository.save(entity);
        return convertToDTO(savedEntity);

    }

    @Override
    public ActivityLogDTO updateActivityLog(Long id, ActivityLogDTO dto) {
        ActivityLog entity = activityLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity log not found with id: " + id));
        entity.setActivityName(dto.getActivityName());
        entity.setDate(dto.getDate());
        entity.setDurationInMinutes(dto.getDurationInMinutes());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
        entity.setUser(user);
        ActivityLog updatedEntity = activityLogRepository.save(entity);
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteActivityLog(Long id) {
        ActivityLog entity = activityLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity log not found with id: " + id));
        activityLogRepository.delete(entity);
    }
}
