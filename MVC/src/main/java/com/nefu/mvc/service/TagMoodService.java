package com.nefu.mvc.service;

import com.nefu.mvc.entity.TagMood;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:31:00
 */
public interface TagMoodService {
    int saveTagMood(TagMood tm);

    int deleteTagMood(int id);

    int updateTagMood(TagMood tm);

    List<Integer> getTagByMood(int mid);

    List<Integer> getMoodByTag(int tid);
}
