package com.lcwd.fitnesstracker.Controller;

import com.lcwd.fitnesstracker.Service.ActivityLogService;
import com.lcwd.fitnesstracker.dtos.ActivityLogDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    public List<ActivityLogDTO> getAllActivityLogs() {
        return activityLogService.getAllActivityLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityLogDTO> getActivityLogById(@PathVariable Long id) {
        return ResponseEntity.ok(activityLogService.getActivityLogById(id));
    }

    @PostMapping
    public ActivityLogDTO createActivityLog(@Valid @RequestBody ActivityLogDTO dto) {
        return activityLogService.createActivityLog(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityLogDTO> updateActivityLog(@PathVariable Long id, @Valid @RequestBody ActivityLogDTO dto) {
        return ResponseEntity.ok(activityLogService.updateActivityLog(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivityLog(@PathVariable Long id) {
        activityLogService.deleteActivityLog(id);
        return ResponseEntity.noContent().build();
    }
}
