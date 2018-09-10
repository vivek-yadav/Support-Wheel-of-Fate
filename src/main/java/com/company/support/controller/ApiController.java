package com.company.support.controller;

import com.company.support.model.Schedule;
import com.company.support.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/schedule/generate", method = RequestMethod.POST)
    public Schedule generateSchedule(@RequestBody Schedule schedule) throws Exception {
        return scheduleService.generateSchedule(schedule);
    }

}
