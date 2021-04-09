package com.nefu.mvc.service;

import com.nefu.mvc.entity.BasicInfo;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:13:00
 */
public interface BasicInfoService {
    int saveBasicInfo(BasicInfo bi);

    int deleteBasicInfo(int id);

    int updateBasicInfo(BasicInfo bi);

    List<BasicInfo> getBasicInfos();

    BasicInfo getBasicInfoById(int id);
}
