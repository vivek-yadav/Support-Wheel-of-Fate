package com.company.support.service;

import com.company.support.model.Schedule;

import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    public Schedule generateSchedule(Schedule schedule);
}