package com.lcwd.fitnesstracker.Service;

import com.lcwd.fitnesstracker.dtos.ActivityLogDTO;

import java.util.List;

public interface ActivityLogService {


    public List<ActivityLogDTO> getAllActivityLogs();

    public ActivityLogDTO getActivityLogById(Long id);
    public ActivityLogDTO createActivityLog(ActivityLogDTO dto);
    public ActivityLogDTO updateActivityLog(Long id, ActivityLogDTO dto);
    public void deleteActivityLog(Long id);

}
