package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:35:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    private int id;
    private String videoTitle;
    private String videoLink;
    //视频封面链接
    private String videoThumbnail;
    //视频时长，单位是秒
    private int videoDuration;
    private Date videoCreated;
    private String videoDescription;
    private int videoViewTimes;
    //0表示预约 1表示直播 2表示录播
    private int videoState;
    private String videoAuthor;
    //信号源
    private String signalLink;
    //所属赛程id
    private int scheduleId;
}
