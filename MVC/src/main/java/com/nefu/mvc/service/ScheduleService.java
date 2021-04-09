package com.nefu.mvc.service;

import com.nefu.mvc.entity.Schedule;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:29:00
 */
public interface ScheduleService {
    int saveSchedule(Schedule s);

     int deleteSchedule(int id);

     int updateSchedule(Schedule s);

     List<Schedule> getSchedules();

    Schedule getScheduleById(int id);
}
