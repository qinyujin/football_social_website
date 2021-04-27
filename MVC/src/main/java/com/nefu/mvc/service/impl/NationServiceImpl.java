package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Nation;
import com.nefu.mvc.mapper.NationMapper;
import com.nefu.mvc.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 11:19:00
 */
@Service
public class NationServiceImpl implements NationService {
    @Autowired
    private NationMapper nationMapper;

    @Override
    public int saveNation(Nation n) {
        Nation nation = nationMapper.getNationByName(n.getName());
        if(nation!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加国家");
        return nationMapper.insert(n);
    }

    @Override
    public int deleteNation(int id) {
        return nationMapper.deleteById(id);
    }

    @Override
    public int updateNation(Nation n) {
        return nationMapper.updateById(n);
    }

    @Override
    public List<Nation> getNations() {
        return nationMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Nation getNationById(int id) {
        return nationMapper.selectById(id);
    }
}
