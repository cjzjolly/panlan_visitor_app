package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.RegInfoMapper;
import com.tencent.wxcloudrun.model.RegInfoItem;
import com.tencent.wxcloudrun.service.RegInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegInfoServiceImpl implements RegInfoService {

  final RegInfoMapper regInfoMapper;

  public RegInfoServiceImpl(@Autowired RegInfoMapper regInfoMapper) {
    this.regInfoMapper = regInfoMapper;
  }

  @Override
  public void insertRegInfo(RegInfoItem regInfoItem) {
    regInfoMapper.insertRegInfo(regInfoItem);
  }
}
