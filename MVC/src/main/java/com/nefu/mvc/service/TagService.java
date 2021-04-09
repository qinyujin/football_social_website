package com.nefu.mvc.service;

import com.nefu.mvc.entity.Tag;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:30:00
 */
public interface TagService {
    int saveTag(Tag t);

    int deleteTag(int id);

    int updateTag(Tag t);

    List<Tag> getTags();

    Tag getTagById(int id);
}
