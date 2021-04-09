package com.nefu.mvc.service;

import com.nefu.mvc.entity.Style;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:30:00
 */
public interface StyleService {
    int saveStyle(Style s);

    int deleteStyle(int id);

    int updateStyle(Style s);

    List<Style> getStyles();

    Style getStyleById(int id);
}
