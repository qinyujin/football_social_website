package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Player;
import com.nefu.mvc.mapper.PlayerMapper;
import com.nefu.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 10:43:00
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public int savePlayer(Player p) {
        Player player = playerMapper.getPlayerByNum(p.getPlayerNum());
        if(player!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加运动员");
        return playerMapper.insert(p);
    }

    @Override
    public int deletePlayer(int id) {
        return playerMapper.deleteById(id);
    }

    @Override
    public int updatePlayer(Player p) {
        Player player = getPlayerById(p.getId());
        if(player== null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该球员不存在");
        p.setPlayerNum(p.getNum()!=0?p.getNum():player.getNum());
        p.setNationId(p.getNationId()!=0?p.getNationId():player.getNationId());
        p.setTeamId(p.getTeamId()!=0?p.getTeamId():player.getTeamId());
        p.setHeight(p.getHeight()!=0?p.getHeight():player.getHeight());
        p.setWeight(p.getWeight()!=0?p.getWeight():player.getWeight());
        p.setAge(p.getAge()!=0?p.getAge():player.getAge());
        p.setNum(p.getNum()!=0?p.getNum():player.getNum());
        return playerMapper.updateById(p);
    }

    @Override
    public List<Player> getPlayers() {
        return playerMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Player getPlayerById(int id) {
        return playerMapper.selectById(id);
    }
}
