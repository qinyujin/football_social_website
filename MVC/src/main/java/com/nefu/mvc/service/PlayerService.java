package com.nefu.mvc.service;

import com.nefu.mvc.entity.Player;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:26:00
 */
public interface PlayerService {
    int savePlayer(Player p);

    int deletePlayer(int id);

    int updatePlayer(Player p);

    List<Player> getPlayers();

    Player getPlayerById(int id);
}
