package com.nefu.mvc.service;

import com.nefu.mvc.entity.MyFile;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:19:00
 */
public interface FileService {
    int saveFile(MyFile f);

    int deleteFile(int id);

    int updateFile(MyFile f);

    List<MyFile> getFiles();

    MyFile getFileById(int id);
}
