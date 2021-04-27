package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.MyFile;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:57:00
 */
public interface FileMapper extends BaseMapper<MyFile> {
    @Select("select id from my_file where file_path = #{path}")
    MyFile getFileByPath(String path);
}
