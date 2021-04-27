package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.BasicInfo;
import com.nefu.mvc.mapper.BasicInfoMapper;
import com.nefu.mvc.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-19 14:02:00
 */
@Service
public class BasicInfoServiceImpl implements BasicInfoService {
    @Autowired
    private BasicInfoMapper basicInfoMapper;

    @Override
    public int saveBasicInfo(BasicInfo bi) {
        //通过cid和sid来完成校验
        List<Integer> cids = basicInfoMapper.getCidsBySid(bi.getSpecificId());
        if(cids.contains(bi.getCategoryId()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请勿重复添加基础信息关联");
        return basicInfoMapper.insert(bi);
    }

    @Override
    public int deleteBasicInfo(int id) {
        return basicInfoMapper.deleteById(id);
    }

    @Override
    public int updateBasicInfo(BasicInfo bi) {
        BasicInfo basicInfo = getBasicInfoById(bi.getId());
        if(basicInfo==null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该基础信息不存在");
        bi.setCategoryId(bi.getCategoryId()!=0?bi.getCategoryId():basicInfo.getCategoryId());
        bi.setSpecificId(bi.getSpecificId()!=0?bi.getSpecificId():basicInfo.getSpecificId());
        return basicInfoMapper.updateById(bi);
    }

    @Override
    public List<BasicInfo> getBasicInfos() {
        return basicInfoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public BasicInfo getBasicInfoById(int id) {
        return basicInfoMapper.selectById(id);
    }

    @Override
    public List<Integer> getCidsBySid(int sid) {
        return basicInfoMapper.getCidsBySid(sid);
    }
}
