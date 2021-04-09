package com.nefu.mvc.service;

import com.nefu.mvc.entity.Nation;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:24:00
 */
public interface NationService {
    int saveNation(Nation n);

    int deleteNation(int id);

    int updateNation(Nation n);

    List<Nation> getNations();

    Nation getNationById(int id);
}
