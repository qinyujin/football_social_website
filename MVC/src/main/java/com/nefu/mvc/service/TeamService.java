package com.nefu.mvc.service;

import com.nefu.mvc.entity.Team;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:32:00
 */
public interface TeamService {
    int saveTeam(Team t);

    int deleteTeam(int id);

    int updateTeam(Team t);

    List<Team> getTeams();

    Team getTeamById(int id);
}
