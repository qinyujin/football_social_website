package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Location;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:58:00
 */
public interface LocationMapper extends BaseMapper<Location> {
    @Select("select id,latitude,longitude from location where latitude = #{latitude} and longitude" +
            "= #{longitude}")
    Location getLocationByLatitudeAndLongitude(String latitude,String longitude);
}
