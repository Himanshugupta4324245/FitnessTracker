package com.lcwd.fitnesstracker.Controller;

import com.lcwd.fitnesstracker.Service.WorkoutPlanService;
import com.lcwd.fitnesstracker.dtos.WorkoutPlanDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {

    @Autowired
    private WorkoutPlanService workoutPlanService;

    @GetMapping
    public List<WorkoutPlanDTO> getAllWorkoutPlans() {
        return workoutPlanService.getAllWorkoutPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanDTO> getWorkoutPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlanById(id));
    }

    @PostMapping
    public WorkoutPlanDTO createWorkoutPlan(@Valid @RequestBody WorkoutPlanDTO dto) {
        return workoutPlanService.createWorkoutPlan(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlanDTO> updateWorkoutPlan(@PathVariable Long id, @Valid @RequestBody WorkoutPlanDTO dto) {
        return ResponseEntity.ok(workoutPlanService.updateWorkoutPlan(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }
}