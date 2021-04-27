package com.nefu.mvc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:28:00
 * 赛程
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private int id;
    //当天时间，例如17:40
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime matchTime;
    //日期，例如2021/4/8
    private LocalDate matchDate;
    //联赛名称，例如西甲
    private String leagueName;
    //主队名
    private String homeTeamName;
    //客队名
    private String visitorTeamName;
    //主队得分
    private int currentHomeTeamScore;
    //客队得分
    private int currentVisitorTeamScore;
}
