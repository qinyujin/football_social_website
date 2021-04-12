package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Video;
import com.nefu.mvc.mapper.VideoMapper;
import com.nefu.mvc.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-12 09:56:00
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public int saveVideo(Video v) {
        //视频没法做重复插校验，因为视频没有特别唯一的标识，同一个视频也可以有多个链接进入
        return videoMapper.insert(v);
    }

    @Override
    public int deleteVideo(int id) {
        return videoMapper.deleteById(id);
    }

    @Override
    public int updateVideo(Video v) {
        return videoMapper.updateById(v);
    }

    @Override
    public List<Video> getVideos() {
        return videoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Video getVideoById(int id) {
        return videoMapper.selectById(id);
    }
}
