package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Location;
import com.nefu.mvc.mapper.LocationMapper;
import com.nefu.mvc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-15 09:35:00
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationMapper locationMapper;

    @Override
    public int saveLocation(Location l) {
        //不处理重复的插入
        Location location = locationMapper.getLocationByLatitudeAndLongitude(l.getLatitude(), l.getLongitude());
        if(location!=null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加地理位置");
        return locationMapper.insert(l);
    }

    @Override
    public int deleteLocation(int id) {
        return locationMapper.deleteById(id);
    }

    @Override
    public int updateLocation(Location l) {
        return locationMapper.updateById(l);
    }

    @Override
    public List<Location> getLocations() {
        return locationMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Location getLocationById(int id) {
        return locationMapper.selectById(id);
    }
}
