package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.RegInfoItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegInfoMapper {

  void insertRegInfo(RegInfoItem regInfoItem);
}
