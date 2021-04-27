package com.nefu.mvc.entity;

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
    //自然地理位置
    public static final String PHYSICAL_GEOGRAPHY = "自然地理位置";
    //经济地理位置
    public static final String ECONOMIC_GEOGRAPHY = "经济地理位置";
    //政治地理位置
    public static final String POLITICAL_GEOGRAPHY = "政治地理位置";

    private int id;
    private String geoType;
    //纬度
    private String latitude;
    //经度
    private String longitude;
    private String geoNote;
}
