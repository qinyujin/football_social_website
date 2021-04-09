package com.nefu.mvc.service;

import com.nefu.mvc.entity.Location;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:22:00
 */
public interface LocationService {
    int saveLocation(Location l);

    int deleteLocation(int id);

    int updateLocation(Location l);

    List<Location> getLocations();

    Location getLocationById(int id);
}
