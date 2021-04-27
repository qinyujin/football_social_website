package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private int id;
    //位置id
    private int geoId;
    //活动时间段
    private Date startTime;
    private Date endTime;
    //缴纳约球缴纳费用，可选
    private double deposit;
    //发起人id
    private int sponsorId;
    private int limitCount;
    private String notice;
}
