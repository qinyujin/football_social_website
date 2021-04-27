package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Schedule;
import com.nefu.mvc.mapper.ScheduleMapper;
import com.nefu.mvc.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-15 14:36:00
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public int saveSchedule(Schedule s) {
        //通过主客队名称来进行校验
        List<String> homeNames = scheduleMapper.getHomeTeamByVisitor(s.getVisitorTeamName());
        if(homeNames.contains(s.getHomeTeamName()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加赛程");
        return scheduleMapper.insert(s);
    }

    @Override
    public int deleteSchedule(int id) {
        return scheduleMapper.deleteById(id);
    }

    @Override
    public int updateSchedule(Schedule s) {
        return scheduleMapper.updateById(s);
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Schedule getScheduleById(int id) {
        return scheduleMapper.selectById(id);
    }
}
