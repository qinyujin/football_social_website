package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.MyFile;
import com.nefu.mvc.mapper.FileMapper;
import com.nefu.mvc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-21 15:02:00
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public int saveFile(MyFile f) {
        //路径唯一
        MyFile file = fileMapper.getFileByPath(f.getFilePath());
        if(file!=null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加文件");
        return fileMapper.insert(f);
    }

    @Override
    public int deleteFile(int id) {
        return fileMapper.deleteById(id);
    }

    @Override
    public int updateFile(MyFile f) {
        return fileMapper.updateById(f);
    }

    @Override
    public List<MyFile> getFiles() {
        return fileMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public MyFile getFileById(int id) {
        return fileMapper.selectById(id);
    }
}
