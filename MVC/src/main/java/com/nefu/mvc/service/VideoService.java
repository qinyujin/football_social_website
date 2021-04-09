package com.nefu.mvc.service;

import com.nefu.mvc.entity.Video;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:35:00
 */
public interface VideoService {
    int saveVideo(Video v);

    int deleteVideo(int id);

    int updateVideo(Video v);

    List<Video> getVideos();

    Video getVideoById(int id);
}
