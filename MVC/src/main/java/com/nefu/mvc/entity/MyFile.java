package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:18:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFile {
    private int id;
    private int chatRoomId;
    private int userId;
    private Date createTime;
    private String filePath;
    private String fileName;
    //是否属于用户上传的文件
    public boolean isUpload;
}
