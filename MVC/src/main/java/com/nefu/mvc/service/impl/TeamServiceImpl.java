package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Team;
import com.nefu.mvc.mapper.TeamMapper;
import com.nefu.mvc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 10:38:00
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public int saveTeam(Team t) {
        Team team = teamMapper.getTeamByTeamNum(t.getTeamNum());
        if(team!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加队伍");
        return teamMapper.insert(t);
    }

    @Override
    public int deleteTeam(int id) {
        return teamMapper.deleteById(id);
    }

    @Override
    public int updateTeam(Team t) {
        Team oldTeam = getTeamById(t.getId());
        if(oldTeam==null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该队伍不存在");
        t.setTeamNum(t.getTeamNum()==0?oldTeam.getTeamNum():t.getTeamNum());
        t.setTeamName(t.getTeamName()==null ? oldTeam.getTeamName():t.getTeamName());
        t.setNationId(t.getNationId()==0?oldTeam.getNationId():t.getNationId());
        t.setMatchGroup(t.getMatchGroup()=='\0'?oldTeam.getMatchGroup() : t.getMatchGroup());
        t.setTeamSize(t.getTeamSize()==0?oldTeam.getTeamSize():t.getTeamSize());
        t.setHeadCoachId(t.getHeadCoachId()== 0?oldTeam.getHeadCoachId():t.getHeadCoachId());
        t.setHonor(t.getHonor()==null ? oldTeam.getHonor():t.getHonor());
        return teamMapper.updateById(t);
    }

    @Override
    public List<Team> getTeams() {
        return teamMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Team getTeamById(int id) {
        return teamMapper.selectById(id);
    }
}
