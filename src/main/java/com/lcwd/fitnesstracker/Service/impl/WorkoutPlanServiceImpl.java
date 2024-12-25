package com.lcwd.fitnesstracker.Service.impl;

import com.lcwd.fitnesstracker.Service.WorkoutPlanService;
import com.lcwd.fitnesstracker.dtos.WorkoutPlanDTO;
import com.lcwd.fitnesstracker.entities.User;
import com.lcwd.fitnesstracker.entities.WorkoutPlan;
import com.lcwd.fitnesstracker.exceptions.ResourceNotFoundException;
import com.lcwd.fitnesstracker.repository.UserRepository;
import com.lcwd.fitnesstracker.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {


    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private UserRepository userRepository;

    private WorkoutPlanDTO convertToDTO(WorkoutPlan entity) {
        WorkoutPlanDTO dto = new WorkoutPlanDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDurationInMinutes(entity.getDurationInMinutes());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    private WorkoutPlan convertToEntity(WorkoutPlanDTO dto) {
        WorkoutPlan entity = new WorkoutPlan();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDurationInMinutes(dto.getDurationInMinutes());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
        entity.setUser(user);
        return entity;
    }

    @Override
    public List<WorkoutPlanDTO> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutPlanDTO getWorkoutPlanById(Long id) {
        WorkoutPlan entity = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout plan not found with id: " + id));
        return convertToDTO(entity);
    }

    @Override
    public WorkoutPlanDTO createWorkoutPlan(WorkoutPlanDTO dto) {
        WorkoutPlan entity = convertToEntity(dto);
        WorkoutPlan savedEntity = workoutPlanRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    @Override
    public WorkoutPlanDTO updateWorkoutPlan(Long id, WorkoutPlanDTO dto) {
        WorkoutPlan entity = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout plan not found with id: " + id));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDurationInMinutes(dto.getDurationInMinutes());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));
        entity.setUser(user);
        WorkoutPlan updatedEntity = workoutPlanRepository.save(entity);
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteWorkoutPlan(Long id) {
        WorkoutPlan entity = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workout plan not found with id: " + id));
        workoutPlanRepository.delete(entity);

    }
}
