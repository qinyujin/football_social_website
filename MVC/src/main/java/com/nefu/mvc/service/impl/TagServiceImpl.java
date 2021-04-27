package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Tag;
import com.nefu.mvc.mapper.TagMapper;
import com.nefu.mvc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 17:19:00
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public int saveTag(Tag t) {
        Tag tag = tagMapper.getTagByName(t.getName());
        if(tag!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加相同话题");
        return tagMapper.insert(t);
    }

    @Override
    public int deleteTag(int id) {
        return tagMapper.deleteById(id);
    }

    @Override
    public int updateTag(Tag t) {
        return tagMapper.updateById(t);
    }

    @Override
    public List<Tag> getTags() {
        return tagMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Tag getTagById(int id) {
        return tagMapper.selectById(id);
    }
}
