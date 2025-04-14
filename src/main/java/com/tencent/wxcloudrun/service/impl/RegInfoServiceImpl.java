package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.RegInfoMapper;
import com.tencent.wxcloudrun.model.RegInfoItem;
import com.tencent.wxcloudrun.service.RegInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RegInfoServiceImpl implements RegInfoService {

  final RegInfoMapper regInfoMapper;

  public RegInfoServiceImpl(@Autowired RegInfoMapper regInfoMapper) {
    this.regInfoMapper = regInfoMapper;
  }

//  @Override
//  public Optional<RegInfoItem> getRegInfoItems(Integer pageNum) {
//    return Optional.ofNullable(regInfoMapper.getRegInfoItems(pageNum));
//  }

  @Override
  public Optional<List<RegInfoItem>> getRegInfoItems(Map<String, Object> params) {
    return Optional.ofNullable(regInfoMapper.getRegInfoItems(params));
  }

  @Override
  public void insertRegInfo(RegInfoItem regInfoItem) {
    regInfoMapper.insertRegInfo(regInfoItem);
  }
}
