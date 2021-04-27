package com.nefu.mvc.service.impl;

import com.nefu.mvc.entity.TagMood;
import com.nefu.mvc.mapper.TagMoodMapper;
import com.nefu.mvc.service.TagMoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 17:36:00
 */
@Service
public class TagMoodServiceImpl implements TagMoodService {
    @Autowired
    private TagMoodMapper tagMoodMapper;

    @Override
    public int saveTagMood(TagMood tm) {
        List<Integer> mids = getMoodByTag(tm.getTagId());
        if(mids.contains(tm.getMoodId()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该动态已有该话题，请勿重复添加");
        return tagMoodMapper.insert(tm);
    }

    @Override
    public int deleteTagMood(int id) {
        return tagMoodMapper.deleteById(id);
    }

    @Override
    public int updateTagMood(TagMood tm) {
        return tagMoodMapper.updateById(tm);
    }

    @Override
    public List<Integer> getTagByMood(int mid) {
        return tagMoodMapper.getTagByMood(mid);
    }

    @Override
    public List<Integer> getMoodByTag(int tid) {
        return tagMoodMapper.getMoodByTag(tid);
    }
}
