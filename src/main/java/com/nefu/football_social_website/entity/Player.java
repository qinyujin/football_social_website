package com.nefu.football_social_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:25:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int id;
    //球员编号
    private int playerId;
    private String playerName;
    private int nationId;
    private int teamId;
    private double height;
    private double weight;
    private int age;
    //球衣号码
    private int num;
    //球员位置
    private String position;
    private String history;
}
