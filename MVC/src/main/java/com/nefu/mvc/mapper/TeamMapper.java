package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Team;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 11:01:00
 */
public interface TeamMapper extends BaseMapper<Team> {
    @Select("select team_num from team where team_num = #{teamNum}")
    Team getTeamByTeamNum(int teamNum);
}
