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
public class File {
    private int id;
    private int chatRoomId;
    private int userId;
    private Date createTime;
    private String filePath;
    private String fileName;
    private int managerId;
}
