package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Schedule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:59:00
 */
public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Select("select home_team_name from schedule where visitor_team_name = #{visitorName}")
    List<String> getHomeTeamByVisitor(String visitorName);
}
