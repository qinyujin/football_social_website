package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Style;
import com.nefu.mvc.mapper.StyleMapper;
import com.nefu.mvc.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 13:50:00
 */
@Service
public class StyleServiceImpl implements StyleService {
    @Autowired
    private StyleMapper styleMapper;

    @Override
    public int saveStyle(Style s) {
        Style style = styleMapper.getStyleByName(s.getName());
        if(style!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加球队风格");
        return styleMapper.insert(s);
    }

    @Override
    public int deleteStyle(int id) {
        return styleMapper.deleteById(id);
    }

    @Override
    public int updateStyle(Style s) {
        return styleMapper.updateById(s);
    }

    @Override
    public List<Style> getStyles() {
        return styleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Style getStyleById(int id) {
        return styleMapper.selectById(id);
    }
}
