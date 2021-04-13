package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Mood;
import com.nefu.mvc.mapper.MoodMapper;
import com.nefu.mvc.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-12 20:18:00
 */
@Service
public class MoodServiceImpl implements MoodService {
    @Autowired
    private MoodMapper moodMapper;

    @Override
    public int saveMood(Mood m) {
        return moodMapper.insert(m);
    }

    @Override
    public int deleteMood(int id) {
        return moodMapper.deleteById(id);
    }

    @Override
    public int updateMood(Mood m) {
        return moodMapper.updateById(m);
    }

    @Override
    public List<Mood> getMoods() {
        return moodMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Mood getMoodById(int id) {
        return moodMapper.selectById(id);
    }
}
