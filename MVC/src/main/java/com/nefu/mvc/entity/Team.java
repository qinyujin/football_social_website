package com.nefu.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:32:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private int id;
    private int teamNum;
    private String teamName;
    private int nationId;
    private char group;
    //成立时间
    private Date createTime;
    private int teamSize;
    //主教练编号
    private String headCoachId;
    private String honor;
}
