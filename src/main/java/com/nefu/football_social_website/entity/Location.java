package com.nefu.football_social_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :覃玉锦
 * @create :2021-04-08 11:21:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private int id;
    private String geoType;
    //纬度
    private String latitude;
    //经度
    private String longitude;
    private String geoNote;
}
