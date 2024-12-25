package com.lcwd.fitnesstracker.Service;

import com.lcwd.fitnesstracker.dtos.WorkoutPlanDTO;

import java.util.List;

public interface WorkoutPlanService {

    public List<WorkoutPlanDTO> getAllWorkoutPlans();
    public WorkoutPlanDTO getWorkoutPlanById(Long id);
    public WorkoutPlanDTO createWorkoutPlan(WorkoutPlanDTO dto);
    public WorkoutPlanDTO updateWorkoutPlan(Long id, WorkoutPlanDTO dto);
    public void deleteWorkoutPlan(Long id);

}
