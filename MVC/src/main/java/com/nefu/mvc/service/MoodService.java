package com.nefu.mvc.service;

import com.nefu.mvc.entity.Mood;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:23:00
 */
public interface MoodService {
    int saveMood(Mood m);

    int deleteMood(int id);

    int updateMood(Mood m);

    List<Mood> getMoods();

    Mood getMoodById(int id);
}
