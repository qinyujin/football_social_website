package com.nefu.mvc.service;

import com.nefu.mvc.entity.File;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:19:00
 */
public interface FileService {
    int saveFile(File f);

    int deleteFile(int id);

    int updateFile(File f);

    List<File> getFiles();

    File getFileById(int id);
}
